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

import com.sun.glass.ui.View.Capability;

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
	private static Map<Integer, Sala> reservas;
	

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
		reservas = new HashMap<Integer, Sala>();
		
		
		
		  Map<Integer, Sala> resultado = greedy(familias);
		  
		  //resultado = mejora(resultado, 2); 
		  
		  calcularBonoTotal(resultado);
		  
		  System.out.println(resultado); 
		  System.out.println("Costo total en bonos: " + costoTotal);
		  imprimirOcupacion(resultado);
		  imprimirPreferencias(resultado);
		
	}
	
	/*
	 * Funcion greddy
	 */
	public static Map<Integer, Sala> greedy(List<Familia> C) {
		Map<Integer, Sala> S = reservas; // Solución inicial vacía
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
			
			boolean reservado = false;
			f = C.get(0);
			Iterator<Integer> it = f.iterator();
			
			while(!reservado && it.hasNext()) {
				
				x = it.next(); 
				
				if(factible(x, f)) {
					agregar(x, f);
					reservado = true;
				}
			}
			
			C.remove(f);
		}
		
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

	private static void imprimirOcupacion(Map<Integer, Sala> S) {
		Iterator<Sala> it = S.values().iterator();
		int totalPersonas = 0;		
		while(it.hasNext()) {
			Sala sala = it.next();
			totalPersonas += sala.getFamilias();
			System.out.print(sala.getDia() + ", " + sala.getOcupacion() + " - ");
		}
		
		System.out.println("\nTotal familias: " + totalPersonas);
	}
	
	private static void imprimirPreferencias(Map<Integer, Sala> S) {
		Iterator<Sala> it = S.values().iterator();
		while(it.hasNext()) {
			Sala sala = it.next();
			int reservado = sala.getDia();
			Iterator<Familia> itFam = sala.iterator();
			while(itFam.hasNext()) {
				Familia f = itFam.next();
				System.out.print(reservado + " - " +
						f.getId() + " - " +
						f.miembros() + " - " +
						f.indiceDePreferencia(reservado) + "; ");
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
	private static boolean factible(Integer dia, Familia f) {
		
		boolean factible = false;
		
		if(reservas.containsKey(dia)) {
			factible = reservas.get(dia).factible(f);
		} else {
			Sala nueva = new Sala(dia, disponibilidadMax);
			reservas.put(dia, nueva);
			factible = true;
		}
		
		return factible;
	}
	
	/*
	 * Agrega a solucion
	 */
	private static void agregar(Integer dia, Familia f) {
		
		Sala seleccionada = reservas.get(dia);
		seleccionada.addReserva(f);
		
		//determinarValorBono(f.indiceDePreferencia(dia), f.miembros()); // incorpora al costo total
		
		confirmados++;
	}

	/*
	 * Establece como solucion si todos los inscriptos fueron asignados a un dia
	 */
	private static boolean solucion() {
		return confirmados == inscriptos;
	}
	
	/*
	private static Map<Integer, List<Familia>> mejora(Map<Integer, Sala> candidatos, int maxSearch) {
		Map<Integer, Sala> C = candidatos;
		Map<Integer, Sala> asignadasPreferencia = getAsignadasSegunPrefencia(C);
		
		List<Integer> indexPref = new LinkedList<Integer>(asignadasPreferencia.keySet());
		Collections.sort(indexPref);
		Collections.reverse(indexPref);
		
		for(int i = 0; i < indexPref.size() && i < maxSearch; i++) {
			Integer index = indexPref.get(i); // index desde le peor preferencia
			Iterator<Familia> itFam = asignadasPreferencia.get(index).iterator(); //iterador de familias de peores preferencias
			while(itFam.hasNext()) {
				Familia f = itFam.next(); // Familia a permutar
				Integer diaAnt = f.preferenciaEn(index);
				Integer diaPref = f.diaPreferido();
				C = permutarFamilias(f, diaAnt, diaPref, C);
			}
		}
		
		return C;
	}
	
	private static Map<Integer, Sala> getAsignadasSegunPrefencia(Map<Integer, Sala> S) {
		Map<Integer, Sala> result =  new HashMap<Integer, Sala>();
		Iterator<Entry<Integer, Sala>> it = S.entrySet().iterator();
		int indicePreferencia = 0;
		
		while(it.hasNext()) {
			Entry<Integer, Sala> e = it.next();
			Iterator<Familia> itFam = e.getValue().iterator();
			
			while(itFam.hasNext()) {
				Familia f = itFam.next();
				indicePreferencia = f.indiceDePreferencia(e.getKey());
				
				List<Familia> listFam;
				if(!result.containsKey(indicePreferencia)) {
					listFam = new Sala();
				} else {
					listFam = result.get(indicePreferencia);
				}
				listFam.add(f);
				result.put(indicePreferencia, listFam);
			}
		}
		
		return result;
	}
	
	private static Map<Integer, List<Familia>> permutarFamilias(Familia f1, Integer diaAnt, Integer diaPref, Map<Integer, List<Familia>> candidatos) {
		Map<Integer, List<Familia>> C = candidatos;
		List<Familia> listDiaPreferido = C.get(diaPref);
		List<Familia> listCandidatosDiaPref = new LinkedList<>();
		listCandidatosDiaPref.addAll(listDiaPreferido);
		List<Familia> listDiaSaliente;
		List<Familia> listDiaAnterior;
		
		int diaPerm = 0;
		boolean factible = false;
		boolean espacioSuficiente = false;
		int j = 0;
		
		while(!espacioSuficiente) {
			Familia f2 = getFamiliaAPermutar(diaPref, listCandidatosDiaPref);
			int indexPrefF2 = f2.indiceDePreferencia(diaPref);
			
			while(!factible && indexPrefF2 < valoracionesMax) {
				indexPrefF2++;
				diaPerm = f2.preferenciaEn(indexPrefF2);
				if(factible(diaPerm, f2)) {
					listDiaPreferido.remove(f2);
					listDiaSaliente = C.get(diaPerm);
					listDiaSaliente.add(f2);
					factible = true;
				}
			}
			
			if(indexPrefF2 == valoracionesMax) {
				listCandidatosDiaPref.remove(f2);
			}
			
			if(factible(diaPref, f1)) {
				listDiaPreferido.add(f1);
				listDiaAnterior = C.get(diaAnt);
				listDiaAnterior.remove(f1);
				espacioSuficiente = true;
			}
			
			if(j > 500) {
				espacioSuficiente = true;
			}
			
			j++;
		}
			
		return C;
	}
	
	private static Familia getFamiliaAPermutar(Integer dia, List<Familia> listFam) {
		Familia f2 = null;
		double minFrec = 1;
		int minGF = 100;
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
	*/
	
	private static void calcularBonoTotal(Map<Integer, Sala> S) {
		costoTotal = 0;
		
		Iterator<Entry<Integer, Sala>> it = S.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Integer, Sala> e = it.next();
			Integer dia = e.getKey();
			Iterator<Familia> itFam = e.getValue().iterator();
			while(itFam.hasNext()) {
				Familia f = itFam.next();
				determinarValorBono(f.indiceDePreferencia(dia), f.miembros());
			}
		}
		
	}
}
