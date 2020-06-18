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

		/*
		 * Ordenamiento
		 */
		indiceFamilias = calcularFrecuencia(familias);
		Comparator<Familia> compMiembros = new ComparadorMiembros();
		Comparator<Familia> compFrecDia = new ComparadorIndiceDias(frecuenciaDias);
		Comparator<Familia> compFrecFam = new ComparadorIndice(indiceFamilias);
		
		/*
		 * Ejecución del greedy
		 */
		Map<Integer, Sala> resultado = greedy(familias, compMiembros);
		
		/*
		 * Proceso de mejora
		 */
		resultado = mejora(3);
		
		/*
		 * Precesa y muestra información del resultado
		 */
		calcularBonoTotal(resultado);
		System.out.println(resultado); 
		System.out.println("Costo total en bonos: " + costoTotal);
		imprimirOcupacion(resultado);
		//imprimirPreferencias(resultado);
		
	}
	
	/*
	 * Funcion greddy
	 */
	public static Map<Integer, Sala> greedy(List<Familia> C, Comparator<Familia> comp) {
		Map<Integer, Sala> S = reservas; // Solución inicial vacía
		Familia f;
		int x;
		
		Collections.sort(C, comp);
		
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
	
	private static Map<Integer, Sala> mejora(int maxSearch) {
		Map<Integer, Sala> S = reservas;
		Map<Integer, List<Familia>> asignadasPreferencia = getAsignadasSegunPrefencia(reservas);
		
		List<Integer> indexPref = new LinkedList<Integer>(asignadasPreferencia.keySet());
		Collections.sort(indexPref);
		Collections.reverse(indexPref);
		
		for(int i = 0; i < indexPref.size() && i < maxSearch; i++) {
			Integer index = indexPref.get(i); // index desde le peor preferencia
			Iterator<Familia> itFam = asignadasPreferencia.get(index).iterator(); //iterador de familias de peores preferencias
			while(itFam.hasNext()) {
				Familia f = itFam.next(); // Familia a permutar
				reubicarFamilia(f);
			}
		}
		
		return S;
	}
	
	private static Map<Integer, List<Familia>> getAsignadasSegunPrefencia(Map<Integer, Sala> S) {
		Map<Integer, List<Familia>> result =  new HashMap<Integer, List<Familia>>();
		
		Iterator<Sala> itSalas = S.values().iterator();
		
		while(itSalas.hasNext()) {
			
			Sala sala = itSalas.next();
			List<Familia> list;
			int dia = sala.getDia();
			Iterator<Familia> itFam = sala.iterator();
			while(itFam.hasNext()) {
				Familia f = itFam.next();
				int indicePref = f.indiceDePreferencia(dia);
				if(result.containsKey(indicePref)) {
					list = result.get(indicePref);
				} else {
					list = new LinkedList<Familia>();
				}
				list.add(f);
				result.put(indicePref, list);
			}
			
		}
		
		return result;
	}
	
	private static Familia reubicarFamilia(Familia f1) {
		
		int diaAntF1 = f1.getDiaReservado();
		int diaPrefF1 = f1.diaPreferido();
		
		Sala f1SalaOut = reservas.get(diaAntF1);
		Sala f1SalaIn = reservas.get(diaPrefF1);
		
		int j = 0;
		boolean fusibleWhile = false;
		
		while(!f1SalaIn.factible(f1) && !fusibleWhile) {
			
			Familia f2 = getFamiliaAPermutar(f1SalaIn);
			
			int diaAntF2 = f2.getDiaReservado();
			int diaPrefF2 = f2.getNextDia();
			
			Sala f2SalaIn = reservas.get(diaPrefF2);
			Sala f2SalaOut = f1SalaIn;
			
			if(f2SalaIn.factible(f2)) {
				f2SalaOut.removeReserva(f2);
				f2SalaIn.addReserva(f2);
			}
			
			if(j > 500) {
				fusibleWhile = true;
			}
			
			j++;
		}
		
		if(!fusibleWhile) {
			f1SalaOut.removeReserva(f1);
			f1SalaIn.addReserva(f1);
		}
		
		return f1;
	}
	
	private static Familia getFamiliaAPermutar(Sala sala) {
		Familia f2 = null;
		double minFrec = 1;
		int minGF = 3;
		
		Iterator<Familia> itFam = sala.iterator();
		while(itFam.hasNext()) {
			Familia f = itFam.next();
			double frecDia = f.frecuenciaAcum(frecuenciaDias);
			int GF = f.miembros();
			if(GF < minGF) {
				minGF = GF;
				if(frecDia < minFrec) {
					minFrec = frecDia;
					f2 = f;
				}
			}
		}
		
		return f2;
	}
	
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
