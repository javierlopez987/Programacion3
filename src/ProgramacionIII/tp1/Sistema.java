package ProgramacionIII.tp1;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;

import ProgramacionIII.util.*;

public class Sistema {
	final static int MAXLENGHT = 200;
	final static int MAXNUM = 10;
	final static Timer reloj = new Timer();
	
	public static void imprimir(MySimpleLinkedList lista) {
		for(Integer element: lista) {
			System.out.print(", ");
			System.out.print(element);
		}
		System.out.println("");
	}
	
	public static void cargarAleatorio(MySimpleLinkedList lista) {
		for(int i = 0; i < Math.random()*MAXLENGHT; i++) {
			lista.insertFront((int) (Math.random()*MAXNUM));
		}
	}
	
	public static void cargarOrdenado(MySimpleLinkedList lista) {
		cargarAleatorio(lista);
		ordenarLista(lista);
	}
	
	public static void ordenarLista(MySimpleLinkedList lista) {
		List<Integer> tmp = new ArrayList<>();
		Iterator<Integer> it = lista.iterator();
		while(it.hasNext()) {
			it.next();
			tmp.add(lista.extractFront());
		}
		Collections.sort(tmp, Collections.reverseOrder());
		for (Integer element: tmp) {
			lista.insertFront(element);
		}
	}
		
	public static MySimpleLinkedList listarContienePrimeraNoSegunda(MySimpleLinkedList lista1, MySimpleLinkedList lista2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		
		IteradorSimple it1 = lista1.iterator();
		IteradorSimple it2 = lista2.iterator();
		
		while(it1.hasNext() && it2.hasNext()) {
			Integer aux1 = it1.get();
			Integer aux2 = it2.get();
			
			if(aux1 < aux2) {
				aux1 = it1.next();
				while(it1.hasNext() && it1.get().equals(aux1)) {
					aux1 = it1.next();
				}
				result.insertFront(aux1);
			} else if (aux1.equals(aux2)) {
				aux1 = it1.next();
				while(it1.hasNext() && it1.get().equals(aux1)) {
					aux1 = it1.next();
				}
				aux2 = it2.next();
				while(it2.hasNext() && it2.get().equals(aux2)) {
					aux2 = it2.next();
				}
			} else {
				aux2 = it2.next();
				while(it2.hasNext() && it2.get().equals(aux2)) {
					aux2 = it2.next();
				}
			}
		}
		
		while(it1.hasNext()) {
			result.insertFront(it1.next());
		}
		
		return result;
	}
	
	public static MySimpleLinkedList listarComunesPrimeraSegundaIt(MySimpleLinkedList lista1, MySimpleLinkedList lista2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		
		IteradorSimple it1 = lista1.iterator();
		IteradorSimple it2 = lista2.iterator();
		
		while(it1.hasNext() && it2.hasNext()) {
			Integer aux1 = it1.get();
			Integer aux2 = it2.get();
			
			if(aux1 < aux2) {
				aux1 = it1.next();
				while(it1.hasNext() && it1.get().equals(aux1)) {
					aux1 = it1.next();
				}
			} else if (aux1.equals(aux2)) {
				aux1 = it1.next();
				while(it1.hasNext() && it1.get().equals(aux1)) {
					aux1 = it1.next();
				}
				aux2 = it2.next();
				while(it2.hasNext() && it2.get().equals(aux2)) {
					aux2 = it2.next();
				}
				result.insertFront(aux1);
			} else {
				aux2 = it2.next();
				while(it2.hasNext() && it2.get().equals(aux2)) {
					aux2 = it2.next();
				}
			}
		}
		result = result.reverse();
		return result;
	}
	
	public static MySimpleLinkedList listarComunesPrimeraSegundaFor(MySimpleLinkedList lista1, MySimpleLinkedList lista2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		
		for (Integer element1: lista1) {
			for (Integer element2: lista2) {
				if(element1.equals(element2)) {
					result.insertFront(element1);
				}
			}
		}
		
		result = result.reverse();
		return result;
	}
	
	public static MySimpleLinkedList listarComunesDesordenadosIt(MySimpleLinkedList lista1, MySimpleLinkedList lista2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		
		Integer aux;
		IteradorSimple it1 = lista1.iterator();
		
		while(it1.hasNext()) {
			aux = it1.next();
			IteradorSimple it2 = lista2.iterator();
			
			while(it2.hasNext()) {
				if(aux.equals(it2.next())) {
					result.insertFront(aux);
				}
			}
		}
		ordenarLista(result);
		return result;
	}
	
	public static MySimpleLinkedList listarComunesDesordenadosFor(MySimpleLinkedList lista1, MySimpleLinkedList lista2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		
		for (Integer element1: lista1) {
			for (Integer element2: lista2) {
				if(element1 == element2) {
					result.insertFront(element1);
				}
			}
		}
		ordenarLista(result);
		return result;
	}
	
	public static char[] stringToChar(String cadena) {
		char[] tmp = cadena.toCharArray();
		boolean capicua = false;
		
		int i = 0;
		int j = tmp.length;
		
		while(i < j) {
			
		}
		
		return cadena.toCharArray();
	}
}
