package ProgramacionIII.tp1Entregable;

import java.util.ArrayList;
import java.util.List;

public class MainEntregable {
	final static int MIN = 2;
	
	public static void main(String[] args) {
		
		MyDoubleLinkedList lista1 = new MyDoubleLinkedList();
		procesar(lista1);
		
		MyDoubleLinkedList lista2 = new MyDoubleLinkedList();
		lista2.insertBack(new Integer(1));
		lista2.insertBack(new Integer(2));
		lista2.insertBack(new Integer(3));
		procesar(lista2);
		
		MyDoubleLinkedList lista3 = new MyDoubleLinkedList();
		lista3.insertBack(new Integer(1));
		lista3.insertBack(new Integer(2));
		lista3.insertBack(new Integer(2));
		procesar(lista3);
		
		MyDoubleLinkedList lista4 = new MyDoubleLinkedList();
		lista4.insertBack(new Integer(3));
		lista4.insertBack(new Integer(5));
		lista4.insertBack(new Integer(2));
		lista4.insertBack(new Integer(7));
		lista4.insertBack(new Integer(19));
		lista4.insertBack(new Integer(14));
		lista4.insertBack(new Integer(28));
		procesar(lista4);
		
		MyDoubleLinkedList lista5 = new MyDoubleLinkedList();
		lista5.insertBack(new Integer(3));
		lista5.insertBack(new Integer(5));
		lista5.insertBack(new Integer(2));
		lista5.insertBack(new Integer(2));
		lista5.insertBack(new Integer(7));
		lista5.insertBack(new Integer(19));
		lista5.insertBack(new Integer(14));
		lista5.insertBack(new Integer(28));
		procesar(lista5);
		
		MyDoubleLinkedList lista6 = new MyDoubleLinkedList();
		lista6.insertBack(new Integer(1));
		lista6.insertBack(new Integer(2));
		lista6.insertBack(new Integer(3));
		lista6.insertBack(new Integer(1));
		lista6.insertBack(new Integer(2));
		lista6.insertBack(new Integer(3));
		procesar(lista6);
		
		MyDoubleLinkedList lista7 = new MyDoubleLinkedList();
		lista7.insertBack(new Integer(1));
		lista7.insertBack(new Integer(2));
		lista7.insertBack(new Integer(3));
		lista7.insertBack(new Integer(1));
		procesar(lista7);
		
	}
	
	public static void procesar(MyDoubleLinkedList lista) {
		List<MyDoubleLinkedList> contenedor = new ArrayList<>();
		System.out.println("Lista origen: " + lista);
		contenedor = obtenerSubsecuencias(lista);
		System.out.println("Subsecuencias: " + contenedor);
		System.out.println("------");
	}
	
	public static List<MyDoubleLinkedList> obtenerSubsecuencias (MyDoubleLinkedList lista) {
		List<MyDoubleLinkedList> contenedor = new ArrayList<>();
		MyDoubleLinkedList tmp = new MyDoubleLinkedList();
		
		IteradorDoble it = lista.iterator();
		while(it.hasNext()) {
			Integer aux = it.next();
			tmp.insertBack(aux);
			if(it.hasNext()) {
				if(aux > it.get()) {
					if(tmp.size() >= MIN) {
						contenedor.add(tmp);
					}
					tmp = new MyDoubleLinkedList();
				} else if(aux.equals(it.get())) {
					tmp.extractBack();
				}
			}
		}
		if(tmp.size() >= MIN) {
			contenedor.add(tmp);
		}
		
		return contenedor;
	}
	
	public static void imprimirSubsecuencias(List<MyDoubleLinkedList> lista) {
		System.out.println("Subsecuencias");
		for(MyDoubleLinkedList subs: lista) {
			System.out.println(subs);
		}
	}

}
