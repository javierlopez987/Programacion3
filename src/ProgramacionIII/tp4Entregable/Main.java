package ProgramacionIII.tp4Entregable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.omg.CORBA.portable.IndirectionException;

public class Main {
	private static int inscriptos;
	private static int disponibilidadMax;
	private static int confirmados;
	private static int valorBono;
	private static Integer costoTotal;
	private static int valoracionesMax;
	private static int cantDias;
	private static double[][] frecuenciaDias;
	private static Map<Integer, Double> indiceFamilias;
	
	public static void main(String... args) {
		
		/*
		 * Dataset
		 */
		CSVReader reader = new CSVReader("./data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		
		/*
		 * Parámetros iniciales
		 */
		inscriptos = familias.size();
		disponibilidadMax = 340;
		valorBono = 5;
		costoTotal = 0;
		valoracionesMax = 8;
		cantDias = 100;
		frecuenciaDias = new double [cantDias][valoracionesMax];
		indiceFamilias = new HashMap<Integer, Double>();
		
		
		Map<Integer, List<Familia>> resultado = greedy(familias);
		resultado = mejora(resultado, 1);
		calcularBonoTotal(resultado);
		
		System.out.println(resultado);
		System.out.println("Costo total en bonos: " + costoTotal);
		imprimirFamilias(resultado);
		
	}
	
	/*
	 * Funcion greddy
	 */
	public static Map<Integer, List<Familia>> greedy(List<Familia> C) {
		Map<Integer, List<Familia>> S = new HashMap<>(); // Solución inicial vacía
		Familia f;
		int x;
		
		indiceFamilias = calcularFrecuencia(C);
		
		
		/*
		 *  Ordenamiento previo a la selección greedy
		 */
		Comparator<Familia> compMiembros = new ComparadorMiembros();
		Comparator<Familia> compFrecDia = new ComparadorIndiceDias(frecuenciaDias);
		Comparator<Familia> compFrecFam = new ComparadorIndice(indiceFamilias);
		
		//Collections.sort(C, compMiembros);
		//Collections.sort(C, compFrecDia.reversed());
		Collections.sort(C, compFrecFam.reversed());
		
		while(!C.isEmpty() && !solucion()) {
			
			x = seleccionar(C, S); 
			f = C.remove(0);
			
			if(factible(x, f, S))
				agregar(x, f, S);
		}
		
		/*
		 * System.out.println("\nAgenda según cantidad de familias");
		 * imprimirFamilias(S);
		 * System.out.println("\nAgenda según cantidad de personas");
		 * imprimirPersonas(S); System.out.println();
		 */
		
		if(solucion()) {
			return S;
		} else {
			return null;
		}
		
	}
	
	private static Map<Integer, Double> calcularFrecuencia(List<Familia> c) {
		Map<Integer, Double> sumaFamilias = new HashMap<Integer, Double>();
		Map<Integer, Double> frecuenciaFamilia = new HashMap<Integer, Double>();
		double aux = 0;
		double sumaFamilia = 0;
		
		Iterator<Familia> it = c.iterator();
		
		while(it.hasNext()) {
			Familia f = it.next();
			for(int i = 0; i < valoracionesMax; i++) {
				aux = frecuenciaDias[f.preferenciaEn(i) - 1][i];
				aux = (aux * inscriptos + 1) / inscriptos;
				frecuenciaDias[f.preferenciaEn(i) - 1][i] = aux;
			}
			if(sumaFamilias.containsKey(f.miembros())) {
				sumaFamilia = sumaFamilias.get(f.miembros());
				sumaFamilia++;
				sumaFamilias.put(f.miembros(), sumaFamilia);
			} else {
				sumaFamilia = 1;
				sumaFamilias.put(f.miembros(), sumaFamilia);
			}
			
			frecuenciaFamilia.put(f.miembros(), sumaFamilias.get(f.miembros())/inscriptos); 
			
		}
		
		return frecuenciaFamilia;
		
	}

	/*
	 * Imprime cantidad de familias por día
	 */
	private static void imprimirFamilias(Map<Integer, List<Familia>> S) {
		Iterator<Entry<Integer, List<Familia>>> it = S.entrySet().iterator();
		int totalFamilias = 0;
		
		while(it.hasNext()) {
			Entry<Integer, List<Familia>> e = it.next();
			int familiasPorDia = e.getValue().size();
			
			System.out.print(e.getKey() + "; " + familiasPorDia + " - ");
			totalFamilias += familiasPorDia;
		}
		
		System.out.println("\nTotal familias: " + totalFamilias);
	}
	
	private static void imprimirPersonas(Map<Integer, List<Familia>> S) {
		Iterator<Entry<Integer, List<Familia>>> it = S.entrySet().iterator();
		int totalPersonas = 0;
		
		while(it.hasNext()) {
			Entry<Integer, List<Familia>> e = it.next();
			int sumaPersonas = 0;
			Iterator<Familia> itFam = e.getValue().iterator();
			
			while(itFam.hasNext()) {
				sumaPersonas += itFam.next().miembros();
			}
			totalPersonas += sumaPersonas;
			System.out.print(e.getKey() + "; " + sumaPersonas + " - ");
		}
		
		System.out.println("\nTotal personas: " + totalPersonas);
	}
	
	private static void imprimirPrefencias(Map<Integer, List<Familia>> S) {
		Iterator<Entry<Integer, List<Familia>>> it = S.entrySet().iterator();
		int indicePreferencia = 0;
		
		while(it.hasNext()) {
			Entry<Integer, List<Familia>> e = it.next();
			Iterator<Familia> itFam = e.getValue().iterator();
			
			while(itFam.hasNext()) {
				Familia f = itFam.next();
				indicePreferencia = f.indiceDePreferencia(e.getKey());
				System.out.print(e.getKey() + "; " + f.getId() + "; " + f.miembros() + "; " + indicePreferencia + " - ");
			}
		}
	}
	
	private static int determinarValorBono(int valoracion, int grupoFamiliar) {
		int totalBono = 0;
		
		totalBono = calcularBono(valoracion, grupoFamiliar);
		costoTotal += totalBono;
		
		return totalBono;
	}
	
	private static int calcularBono(int valoracion, int grupoFamiliar) {
		int totalBono = 0;
		
		if(valoracion != 0) {
			totalBono = (2 * grupoFamiliar + valoracion + valorBono) * valorBono;
		}
		
		return totalBono;
	}

	/*
	 * Es factible si hay lugar disponible ese dia
	 */
	private static boolean factible(Integer x, Familia f, Map<Integer, List<Familia>> S) {
		List<Familia> tmp = S.get(x);
		int ocupacion = 0;
		
		if(tmp == null) {
			tmp = new LinkedList<Familia>();
		}
		
		//Mejorable con Clase Asignacion
		Iterator<Familia> it = tmp.iterator();
		while(it.hasNext()) {
			ocupacion += it.next().miembros();
		}
		
		return ocupacion + f.miembros() <= disponibilidadMax;
	}

	/*
	 * Agrega a solucion
	 */
	private static void agregar(Integer dia, Familia f, Map<Integer, List<Familia>> S) {
		List<Familia> tmp = S.get(dia);
		
		if(tmp == null) {
			tmp = new LinkedList<Familia>();
		}
		tmp.add(f);
		
		S.put(dia, tmp); //Se agrega a conjunto solución
		//determinarValorBono(f.indiceDePreferencia(dia), f.miembros()); // incorpora al costo total
		
		confirmados++;
	}

	/*
	 * Devuelve número de día elegido
	 * en funcion de la cantidad de integrantes del grupo familiar
	 */
	private static int seleccionar(List<Familia> candidatos, Map<Integer, List<Familia>> S) {
		Familia f = candidatos.get(0);
		int valoracionesConsideradas = valoracionesMax;
		int i = valoracionesMax - valoracionesConsideradas;
		boolean confirmado = false;
		
		// Criterio Greedy
		while(i < valoracionesMax && !confirmado) {
			if(factible(f.preferenciaEn(i), f, S)) {
				confirmado = true;
			} else {
				i++;
			}
		}
		
		if(!confirmado) {
			i--;
		}
		
		return f.preferenciaEn(i);
	}

	/*
	 * Establece como solucion si todos los inscriptos fueron asignados a un dia
	 */
	private static boolean solucion() {
		return confirmados == inscriptos;
	}
	
	private static Map<Integer, List<Familia>> mejora(Map<Integer, List<Familia>> candidatos, int maxSearch) {
		Map<Integer, List<Familia>> C = candidatos;
		Map<Integer, List<Familia>> asignadasPreferencia = getAsignadasSegunPrefencia(C);
		
		List<Integer> indexPref = new LinkedList<Integer>(asignadasPreferencia.keySet());
		Collections.sort(indexPref);
		Collections.reverse(indexPref);
		
		for(int i = 0; i < indexPref.size() && i < maxSearch; i++) {
			Integer index = indexPref.get(i);
			Iterator<Familia> itFam = asignadasPreferencia.get(index).iterator();
			while(itFam.hasNext()) {
				Familia f = itFam.next(); // Familia a permutar
				Integer diaAnt = f.preferenciaEn(index);
				Integer diaPref = f.diaPreferido();
				C = permutarFamilias(f, diaAnt, diaPref, C);
			}
		}
		
		return C;
	}
	
	private static Map<Integer, List<Familia>> getAsignadasSegunPrefencia(Map<Integer, List<Familia>> S) {
		Map<Integer, List<Familia>> result =  new HashMap<Integer, List<Familia>>();
		Iterator<Entry<Integer, List<Familia>>> it = S.entrySet().iterator();
		int indicePreferencia = 0;
		
		while(it.hasNext()) {
			Entry<Integer, List<Familia>> e = it.next();
			Iterator<Familia> itFam = e.getValue().iterator();
			
			while(itFam.hasNext()) {
				Familia f = itFam.next();
				indicePreferencia = f.indiceDePreferencia(e.getKey());
				
				List<Familia> listFam;
				if(!result.containsKey(indicePreferencia)) {
					listFam = new LinkedList<Familia>();
				} else {
					listFam = result.get(indicePreferencia);
				}
				listFam.add(f);
				result.put(indicePreferencia, listFam);
			}
		}
		
		return result;
	}
	
	private static Map<Integer, List<Familia>> permutarFamilias(Familia f1, Integer diaAnt, Integer dia, Map<Integer, List<Familia>> candidatos) {
		Map<Integer, List<Familia>> C = candidatos;
		List<Familia> listDiaEntrante = C.get(dia);
		List<Familia> listDiaSaliente;
		List<Familia> listDiaAnterior;
		Familia f2 = getFamiliaAPermutar(dia, C);
		int i = f2.indiceDePreferencia(dia);
		int diaPerm = 0;
		boolean factible = false;
		boolean espacioSuficiente = false;
		int j = 0;
		
		while(!espacioSuficiente) {
			while(!factible) {
				i++;
				diaPerm = f2.preferenciaEn(i);
				if(factible(diaPerm, f2, C)) {
					listDiaEntrante.remove(f2);
					listDiaSaliente = C.get(diaPerm);
					listDiaSaliente.add(f2);
					C.put(diaPerm, listDiaSaliente);
					factible = true;
				}
			}
			
			if(factible(dia, f1, C)) {
				listDiaEntrante.add(f1);
				listDiaAnterior = C.get(diaAnt);
				listDiaAnterior.remove(f1);
				C.put(dia, listDiaEntrante);
				C.put(diaAnt, listDiaAnterior);
				espacioSuficiente = true;
			}
			
			if(j > 500) {
				espacioSuficiente = true;
			}
			
			j++;
		}
			
		return C;
	}
	
	private static Familia getFamiliaAPermutar(Integer dia, Map<Integer, List<Familia>> C) {
		Familia f2 = null;
		double minFrec = 1;
		int minGF = 100;
		List<Familia> listFam = C.get(dia);
		Iterator<Familia> itFam = listFam.iterator();
		
		while(itFam.hasNext()) {
			Familia f = itFam.next();
			double frecDia = f.frecuenciaAcum(frecuenciaDias);
			int GF = f.miembros();
			if(GF < minGF) {
				minGF = GF;
				f2 = f;
				if(frecDia < minFrec) {
					minFrec = frecDia;
				}
			}
		}
		
		return f2;
	}
	
	private static void calcularBonoTotal(Map<Integer, List<Familia>> S) {
		costoTotal = 0;
		
		Iterator<Entry<Integer, List<Familia>>> it = S.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Integer, List<Familia>> e = it.next();
			Integer dia = e.getKey();
			Iterator<Familia> itFam = e.getValue().iterator();
			while(itFam.hasNext()) {
				Familia f = itFam.next();
				determinarValorBono(f.indiceDePreferencia(dia), f.miembros());
			}
		}
		
	}
}
