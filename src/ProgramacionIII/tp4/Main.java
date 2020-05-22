package ProgramacionIII.tp4;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
	private static Integer W;
	private static Integer S; //S = variable que acumula y controla soluciones parciales
	
	public static void main(String[] args) {
		W = 289;
		Map<Integer, Integer> resultado;
		
		List<Integer> monedas = new LinkedList<>();
		monedas.add(100);
		monedas.add(25);
		monedas.add(10);
		monedas.add(5);
		monedas.add(1);

		resultado = greedyMonedas(monedas);
		System.out.println(resultado);
	}

	public static Map<Integer, Integer> greedyMonedas(List<Integer> C) {
		Map<Integer, Integer> solucion = new LinkedHashMap<>(); // Guarda soluciones
		Integer x; //x = billete
		
		S = W; // Lo que la teoría conoce como "Solución inicial vacía"
		
		Collections.sort(C, Collections.reverseOrder()); //Ordenar lista para mejorar rendimiento
		
		while(!C.isEmpty() && !solucion(S)) {
			x = seleccionar(C);
			C.remove(x);
			
			if(factible(x, S)) // es factible explicito
				agregar(x, solucion); // agregar candidato seleccionado a solución parcial
		}
		
		if(solucion(S)) {
			return solucion;
		} else {
			return null;
		}
	}
	
	private static boolean factible(Integer x, Integer S) {
		
		return x <= S;
	}

	private static void agregar(Integer x, Map<Integer, Integer> solucion) {

		int aux = 0;
		int veces = 1;
		
		if(x != 0)
			veces = S/x; //cantidad de veces que entra el billete
		
		//S.add(aux);
		
		aux = veces * x;
		
		S -= aux; //Se agrega
		
		solucion.put(x, veces);
		
		/*
		while (x <= P) {
			//Integer tmp = new Integer(x);
			S.add(x);
			P -= x;
		}
		*/
	}

	private static int seleccionar(List<Integer> candidatos) {
		int max = 0; // max => billete de mayor denominacion
		/*
		 * int aux = 0;
		 */
		
		
		// No recorrer candidatos si los llevo ordenados
		/*
		Iterator<Integer> it = candidatos.iterator();
		
		while(it.hasNext()) {
			aux = it.next();
			if(aux > max) {
				max = aux;
			}
		}
		*/
		if(!candidatos.isEmpty()) {
			max = candidatos.get(0);
		}
		
		return max;
	}

	private static boolean solucion(Integer S) {
		return S == 0;
		/*
		int suma = 0;
		Iterator<Integer> it = S.iterator();
		
		while(it.hasNext()) {
			suma += it.next();
		}
		
		return suma == W;
		*/
	}
}
