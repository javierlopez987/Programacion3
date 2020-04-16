package ProgramacionIII.tp1;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;

import ProgramacionIII.util.*;

public class Sistema {
	final static int MAX = 10;
	final static int MAXNUM = 10;
	final static Timer reloj = new Timer();

	public static void main(String[] args) {
		MySimpleLinkedList lista1 = new MySimpleLinkedList();
		MySimpleLinkedList lista2 = new MySimpleLinkedList();
		MySimpleLinkedList lista3 = new MySimpleLinkedList();
		
		//reloj.start();
		cargarOrdenado(lista1);
		cargarOrdenado(lista2);
		System.out.println("Lista 1");
		imprimir(lista1);
		System.out.println("Lista 2");
		imprimir(lista2);
		//System.out.println(reloj.stop());
		//reloj.start();
		System.out.println("Lista resultado");
		lista3 = listarContienePrimeraNoSegunda(lista1, lista2);
		//System.out.println(reloj.stop());
		//reloj.start();
		lista3 = lista3.reverse();
		//System.out.println(reloj.stop());
		//reloj.start();
		imprimir(lista3);
		//System.out.println(reloj.stop());
		
	}
	
	public static void imprimir(MySimpleLinkedList lista) {
		for(Integer element: lista) {
			System.out.print(", ");
			System.out.print(element);
		}
		System.out.println("");
	}
	
	public static void cargarAleatorio(MySimpleLinkedList lista) {
		for(int i = 0; i < Math.random()*MAX; i++) {
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
				it2.next();
			}
		}
		
		while(it1.hasNext()) {
			result.insertFront(it1.next());
		}
		
		return result;
	}
	
}
