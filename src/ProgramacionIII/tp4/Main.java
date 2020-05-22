package ProgramacionIII.tp4;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
	private static Integer W;
	private static Integer S;
	
	public static void main(String[] args) {
		W = 289;
		List<Integer> resultado;
		
		List<Integer> monedas = new LinkedList<>();
		monedas.add(100);
		monedas.add(25);
		monedas.add(10);
		monedas.add(5);
		monedas.add(1);

		resultado = greedyMonedas(monedas);
		System.out.println(resultado);
		System.out.println(resultado.size());
	}

	public static List<Integer> greedyMonedas(List<Integer> C) {
		Integer S;
		Integer x;
		
		S = W; // Lo que la teoría conoce como "Solución inicial vacía"
		
		Collections.sort(C); //Ordenar lista para mejorar rendimiento
		
		while(!C.isEmpty() && !solucion(S)) {
			x = seleccionar(C);
			C.remove(x);
			
			if(factible(x, S)) // es factible explicito
				agregar(x, S); //
			else
				S.add(0);
		}
		
		if(solucion(S)) {
			return S;
		} else {
			return null;
		}
	}
	
	private static boolean factible(Integer x, Integer p2) {
		
		return x <= p2;
	}

	private static void agregar(int x, List<Integer> S) {
		
		int aux = S/x; //cantidad de veces que entra el billete
		
		S.add(aux);
		
		S -= aux * x; 
		
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
		int aux = 0;
		
		
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
		
		return max;
	}

	private static boolean solucion(List<Integer> S) {
		return S.get(0) == 0;
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
