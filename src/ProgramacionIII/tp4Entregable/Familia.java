package ProgramacionIII.tp4Entregable;

import java.util.Arrays;
import java.util.Map;

/* Una familia, con su cantidad de dias, y una arreglo con el top de 4 dias preferidos */
public class Familia {

	private int id;
	private int miembros;
	private int[] diasPreferidos;
	
	public Familia(int id, int miembros, int... diasPreferidos) {
		this.id = id;
		this.miembros = miembros;
		this.diasPreferidos = diasPreferidos;
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
		return this.diasPreferidos[indice];
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

}
