package ProgramacionIII.tp3Entregable;

import java.util.Comparator;

public class ComparadorArcos implements Comparator<Arco<Integer>>{
	
	@Override
	public int compare(Arco<Integer> arco1, Arco<Integer> arco2) {
		return arco1.getVerticeOrigen() - arco2.getVerticeOrigen();
	}

}
