package ProgramacionIII.tp4Entregable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
	private static int inscriptos;
	private static int disponibilidadMax;
	private static int confirmados;
	private static int valorBono;
	private static Integer costoTotal;
	private static int valoracionesMax;
	private static int cantDias;
	private static int valorMax;
	
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
		
		Map<Integer, List<Familia>> resultado = greedy(familias);
		
		//imprimirSolucion(resultado);
		System.out.println(resultado);
		System.out.println("Costo total en bonos: " + costoTotal);
		
	}
	
	/*
	 * Funcion greddy
	 */
	public static Map<Integer, List<Familia>> greedy(List<Familia> C) {
		Map<Integer, List<Familia>> S = new HashMap<>(); // Solución inicial vacía
		Familia f;
		int x;
		
		/*
		 * Parámetros para selección
		 */
		valorMax = calcularBono(6, 2);
		ComparadorFamilia comparador = new ComparadorMiembros(); 
		Collections.sort(C, comparador.reversed());
		
		while(!C.isEmpty() && !solucion()) {
			
			x = seleccionar(C); // devuelve numero de día elegido
			f = C.remove(0); // Va borrando las familias para no repetir
			
			if(factible(x, f, S))
				agregar(x, f, S); // agregar candidato seleccionado a solución parcial
		}
		
		System.out.println("\nAgenda según cantidad de familias");
		imprimirFamilias(S);
		System.out.println("\nAgenda según cantidad de personas");
		imprimirPersonas(S);
		System.out.println();
		
		if(solucion()) {
			return S;
		} else {
			return null;
		}
		
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
		
		Iterator<Familia> it = tmp.iterator();
		while(it.hasNext()) {
			ocupacion += it.next().miembros();
		}
		
		return ocupacion + f.miembros() < disponibilidadMax;
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
		determinarValorBono(f.indiceDePreferencia(dia), f.miembros()); // incorpora al costo total
		
		confirmados++;
	}

	/*
	 * Devuelve número de día elegido
	 * en funcion de la cantidad de integrantes del grupo familiar
	 */
	private static int seleccionar(List<Familia> candidatos) {
		Familia f = candidatos.get(0);
		int valoracionesConsideradas = valoracionesMax;
		int i = valoracionesConsideradas;
		int bono = 0;
		boolean confirmado = false;
		
		// Criterio Greedy
		while(!confirmado && i >= 0) {
			i--;
			
			bono = calcularBono(i, f.miembros());
			
			if(bono == 0) {
				confirmado = true;
			} else if (bono <= valorMax) {
				confirmado = true;
			}
		}
		
		return f.preferenciaEn(i);
	}

	/*
	 * Establece como solucion si todos los inscriptos fueron asignados a un dia
	 */
	private static boolean solucion() {
		return confirmados == inscriptos;
	}
	
}
