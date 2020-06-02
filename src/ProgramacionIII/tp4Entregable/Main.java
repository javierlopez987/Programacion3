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
	private static int valoracionesMax;
	private static int[][] matrizPreferencia;
	private static int promedioMax;
	
	public static void main(String... args) {
		
		CSVReader reader = new CSVReader("./data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		inscriptos = familias.size();
		valorBono = 5;
		costoTotal = 0;
		valoracionesMax = 8;
		
		establecerMatrizPreferencia(valoracionesMax);
		
		promedioMax = matrizPreferencia[7][1];
		
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
		
		while(!C.isEmpty() && !solucion(S)) {
			
			x = seleccionar(C); // devuelve numero de día elegido
			f = C.remove(0); // Va borrando las familias para no repetir
			
			if(factible(x, S))
				agregar(x, f, S); // agregar candidato seleccionado a solución parcial
		}
		
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
		determinarValorBono(f.indiceDePreferencia(dia), f.miembros());
		
		confirmados++;
	}

	/*
	 * Devuelve número de día elegido
	 * en funcion de la cantidad de integrantes del grupo familiar
	 */
	private static int seleccionar(List<Familia> candidatos) {
		Familia f = candidatos.get(0);
		int i = valoracionesMax;
		int bono = 0;
		boolean confirmado = false;
		
		// Criterio Greedy
		while(!confirmado) {
			i--;
			
			bono = calcularBono(i, f.miembros());
			
			if(bono == 0) {
				confirmado = true;
				i++;
			} else if (bono <= promedioMax) {
				confirmado = true;
			}
		}
		
		return f.preferenciaEn(i);
	}

	/*
	 * Establece como solucion si todos los inscriptos fueron asignados a un dia
	 */
	private static boolean solucion(Map<Integer, List<Familia>> S) {
		return confirmados == inscriptos;
	}
	
	private static void establecerMatrizPreferencia(int valoracionMax) {
		
		// Supuesto de grupo familiar maximo
		int gfMax = 4;
		
		matrizPreferencia = new int[valoracionMax][gfMax];
		
		for(int i = 0; i < valoracionMax; i++) {
			for(int j = 0; j < gfMax; j++) {
 				matrizPreferencia[i][j] = calcularBono(i, j);				
			}
		}
	}
	
}
