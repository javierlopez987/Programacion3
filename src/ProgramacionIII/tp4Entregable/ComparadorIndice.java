package ProgramacionIII.tp4Entregable;

import java.util.Comparator;
import java.util.Map;

public class ComparadorIndice implements Comparator<Familia>{
	private Map<Integer, Double> indiceFamilias;
	
	public ComparadorIndice(Map<Integer, Double> indiceFamilias) {
		this.indiceFamilias = indiceFamilias;
	}

	@Override
	public int compare(Familia f1, Familia f2) {
		return Double.compare(f1.indiceGrupoFamiliar(indiceFamilias), f2.indiceGrupoFamiliar(indiceFamilias));
	}
	
}
