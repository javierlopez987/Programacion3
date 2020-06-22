package ProgramacionIII.tp5;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* Una familia, con su cantidad de dias, y una arreglo con el top de 3 dias preferidos */
public class Familia implements Comparable<Familia>, Iterable<Integer> {

	private int id;
	private int miembros;
	private int[] diasPreferidos;
	private int diaReservado;
	
	public Familia(int id, int miembros, int... diasPreferidos) {
		this.id = id;
		this.miembros = miembros;
		this.diasPreferidos = diasPreferidos;
		this.diaReservado = -1;
	}
	
	public void setDiaReservado(int dia) {
		if(indiceDePreferencia(dia) != -1) {
			this.diaReservado = dia;
		}
	}
	
	public int getDiaReservado() {
		return this.diaReservado;
	}
	
	public int getNextDia() {
		int nextDia = -1;
		int indexReserva = indiceDePreferencia(diaReservado);
		indexReserva++;
		if(preferenciaEn(indexReserva) != -1) {
			nextDia = preferenciaEn(indexReserva);
		}
		
		return nextDia;
	}

	/* Id de la familia */
	public int getId() {
		return id;
	}


	/* Retorna la cantidad de miembros de la familia. */
	public int miembros() {
		return miembros;
	}

	
	/* Dado un indice entre 0 y 2, retorna el día preferido por la familia para ese indice. */
	public int preferenciaEn(int indice) {
		return this.diasPreferidos[indice];
	}
	
	/* Retorna el día preferido de la familia */
	public int diaPreferido() {
		return preferenciaEn(0);
	}
	
	/* Dado un dia pasado por parametro, indica el orden de ese dia en el top 5 de preferencias.
	Si el dia no forma parte de las preferencias del usuario, se retorna -1. */ 
	public int indiceDePreferencia(int dia) {
		for (int indice = 0; indice < diasPreferidos.length; indice++)
			if (dia == diasPreferidos[indice])
				return indice;
		return -1;
	}

	@Override
	public String toString() {
		return "Familia: id=" + id + ", miembros=" + miembros + ", preferencias=" + Arrays.toString(diasPreferidos);
	}

	@Override
	public int compareTo(Familia o) {
		return Integer.compare(this.miembros, o.miembros);
	}
	
	@Override
	public Iterator<Integer> iterator() {
		List<Integer> preferencias = new LinkedList<Integer>();
		
		for (int i = 0; i < diasPreferidos.length; i++) {
			preferencias.add(diasPreferidos[i]);
		}
		
		return preferencias.iterator();
	}

}
