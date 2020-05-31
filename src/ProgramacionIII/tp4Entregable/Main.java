package ProgramacionIII.tp4Entregable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
	final static int MAXDISPONIBILIDAD = 340;
	private static int inscriptos;
	private static int confirmados;
	private static int valorBono;
	private static Integer costoTotal;
	
	public static void main(String... args) {
		
		CSVReader reader = new CSVReader("./data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		inscriptos = familias.size();
		valorBono = 5;
		costoTotal = 0;
		
		Map<Integer, List<Familia>> resultado = greedy(familias);
		System.out.println("Costo total en bonos: " + costoTotal);
		
		System.out.println(resultado);
		
	}
	
	/*
	 * Funcion greddy
	 */
	public static Map<Integer, List<Familia>> greedy(List<Familia> C) {
		Map<Integer, List<Familia>> S = new HashMap<>(); // Solución inicial vacía
		Familia f;
		int x;
		
		while(!C.isEmpty() && !solucion(S)) {
			
			x = seleccionar(C); // devuelve numero de día elegido
			f = C.remove(0); // borra la Familia en inidice 0, que fue el criterio para evaluar seleccion
			
			if(factible(x, S))
				agregar(x, f, S); // agregar candidato seleccionado a solución parcial
		}
		
		imprimirSolucion(S);
		
		if(solucion(S)) {
			return S;
		} else {
			return null;
		}
		
	}
	
	/*
	 * Imprime solucion
	 */
	private static void imprimirSolucion(Map<Integer, List<Familia>> S) {
		Iterator<Integer> itKey = S.keySet().iterator();
		Iterator<List<Familia>> it = S.values().iterator();
		
		while(it.hasNext() && itKey.hasNext()) {
			System.out.println(itKey.next() + "; " + it.next().size());
		}
	}

	private static int establecerBono(int valoracion, int grupoFamiliar) {
		int totalBono = 0;
		
		if(valoracion != 0) {
			totalBono = (2 * grupoFamiliar + valoracion + valorBono) * valorBono;
		}
		costoTotal += totalBono;
		
		return totalBono;
	}

	/*
	 * Es factible si hay lugar disponible ese dia
	 */
	private static boolean factible(Integer x, Map<Integer, List<Familia>> S) {
		List<Familia> tmp = S.get(x);
		
		if(tmp == null) {
			tmp = new LinkedList<Familia>();
		}
		
		return tmp.size() < MAXDISPONIBILIDAD;
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
		establecerBono(0, f.miembros());
		
		confirmados++;
	}

	/*
	 * Devuelve número de día elegido
	 * en funcion de la cantidad de integrantes del grupo familiar
	 */
	private static int seleccionar(List<Familia> candidatos) {
		Familia x = candidatos.get(0);
		Integer i = 0;
		
		return x.preferenciaEn(i);
	}

	/*
	 * Establece como solucion si todos los inscriptos fueron asignados a un dia
	 */
	private static boolean solucion(Map<Integer, List<Familia>> S) {
		return confirmados == inscriptos;
	}
	
}
