package ProgramacionIII.tp4Entregable;

import java.util.Comparator;
import java.util.Map;

public class ComparadorIndiceDias implements Comparator<Familia>{
	private double[][] frecuenciaDias;
	
	public ComparadorIndiceDias(double[][] frecuenciaDias) {
		this.frecuenciaDias = frecuenciaDias;
	}

	@Override
	public int compare(Familia f1, Familia f2) {
		return Double.compare(f1.frecuenciaAcum(frecuenciaDias), f2.frecuenciaAcum(frecuenciaDias));
	}
	
}
