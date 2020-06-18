package ProgramacionIII.tp4Entregable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* Una familia, con su cantidad de dias, y una arreglo con el top de 4 dias preferidos */
public class Familia implements Iterable<Integer>{

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
	
	public int getDiaMenosDemanda(double[][] matrizPreferencia) {
		int indexDia = 0;
		double min = 0;
		
		int i = 0;
		
		while(i < diasPreferidos.length) {
			
			double indiceDemanda = matrizPreferencia[this.preferenciaEn(i) -1][i];
			if(indiceDemanda < min) {
				min = indiceDemanda;
				indexDia = i;
			}
			i++;
		}
		
		return diasPreferidos[indexDia];
	}
	
	public double frecuenciaAcum(double[][] matrizPreferencia) {
		double suma = 0;
		int i = 0;
		
		while(i < diasPreferidos.length) {
			suma += matrizPreferencia[this.preferenciaEn(i) -1][i];
			i++;
		}
		
		return suma;
	}
	
	public double indiceGrupoFamiliar(Map<Integer, Double> registro) {
		return registro.get(this.miembros);
	}

	/* Id de la familia */
	public int getId() {
		return id;
	}


	/* Retorna la cantidad de miembros de la familia. */
	public int miembros() {
		return miembros;
	}

	
	/* Dado un indice entre 0 y 7, retorna el día preferido por la familia para ese indice. */
	public int preferenciaEn(int indice) {
		int diaPref = -1;
		if(indice < diasPreferidos.length) {
			diaPref = this.diasPreferidos[indice];
		}
		
		return diaPref;
	}
	
	/* Retorna el día preferido de la familia */
	public int diaPreferido() {
		return preferenciaEn(0);
	}
	
	/* Dado un dia pasado por parametro, indica el orden de ese dia en el top 8 de preferencias.
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
	public Iterator<Integer> iterator() {
		List<Integer> preferencias = new LinkedList<Integer>();
		
		for (int i = 0; i < diasPreferidos.length; i++) {
			preferencias.add(diasPreferidos[i]);
		}
		
		return preferencias.iterator();
	}

}
