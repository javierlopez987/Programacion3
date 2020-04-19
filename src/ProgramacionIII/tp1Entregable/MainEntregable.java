package ProgramacionIII.tp1Entregable;

import java.util.ArrayList;
import java.util.List;

import ProgramacionIII.tp1.IteradorSimple;
import ProgramacionIII.tp1.MySimpleLinkedList;
import ProgramacionIII.tp1.Sistema;

public class MainEntregable {
	final static int MIN = 2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<MySimpleLinkedList> contenedor = new ArrayList<>();
		MySimpleLinkedList lista = new MySimpleLinkedList();
		
		Sistema.cargarAleatorio(lista);
		Sistema.imprimir(lista);
		contenedor = obtenerSubsecuencias(lista);
		imprimirSubsecuencias(contenedor);
		
	}
	
	public static List<MySimpleLinkedList> obtenerSubsecuencias (MySimpleLinkedList lista) {
		List<MySimpleLinkedList> contenedor = new ArrayList<>();
		MySimpleLinkedList tmp = new MySimpleLinkedList();
		
		IteradorSimple it = lista.iterator();
		while(it.hasNext()) {
			Integer aux = it.next();
			tmp.insertFront(aux);
			if((it.hasNext()) && !(aux < it.get())) {
				if(tmp.size() >= MIN) {
					tmp = tmp.reverse();
					contenedor.add(tmp);
				}
				tmp = new MySimpleLinkedList();
			}
		}
		
		return contenedor;
	}
	
	public static void imprimirSubsecuencias(List<MySimpleLinkedList> lista) {
		for(MySimpleLinkedList subs: lista) {
			System.out.println(subs);
		}
	}

}
