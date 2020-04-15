package ProgramacionIII.tp1;
import java.util.Iterator;

import ProgramacionIII.util.*;

public class Sistema {

	public static void main(String[] args) {
		
		Timer reloj = new Timer();
		int maxListado = 20000;
		
		/* Prueba PILA
		Pila pila = new Pila();
		
		reloj.start();
		for(int i = 0; i < maxListado; i++) {
			pila.push(i+1);
		}
		System.out.println(reloj.stop());
		
		reloj.start();
		System.out.println(pila.top());
		System.out.println(reloj.stop());
		
		reloj.start();
		pila.reverse();
		System.out.println(reloj.stop());
		System.out.println(pila.top());
		*/
		
		//Prueba LISTA
		MySimpleLinkedList lista = new MySimpleLinkedList();
		
		reloj.start();
		for(int i = 0; i < maxListado; i++) {
			lista.insertFront(i+1);
		}
		System.out.println(reloj.stop());
		
		reloj.start();
		for(int i = 0; i < maxListado; i++) {
			lista.get(i+1);
		}
		System.out.println(reloj.stop());
		
		reloj.start();
		Iterator<Node> it = lista.iterator();
		while(it.hasNext()) {
			it.next().getInfo();
		}
		System.out.println(reloj.stop());
	}

}
