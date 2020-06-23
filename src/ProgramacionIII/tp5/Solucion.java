package ProgramacionIII.tp5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solucion {
	private List<Sala> salas;
	private ArrayList<Familia> familias;
	private int valorBono;
	
	public Solucion( ArrayList<Familia> familias, int cantDiasMax, int capacidadMax, int valorBono) {
		this.familias = familias;
		this.salas = createSalas(cantDiasMax, capacidadMax);
		this.valorBono = valorBono;
	}
	
	private List<Sala> createSalas(int cantDiasMax, int capacidadMax) {
		List<Sala> salas = new ArrayList<Sala>();
		
		for(int i = 0; i < cantDiasMax; i++) {
			Sala nueva = new Sala(i + 1, capacidadMax);
			salas.add(nueva);
		}
		
		return salas;
	}
	
	public List<Sala> getSolucion() {
		List<Sala> propuesta = new ArrayList<Sala>();
		resolverConBacktracking();
		propuesta.addAll(salas);
		
		return propuesta;
	}
	
	private void resolverConBacktracking() {
		Backtrack back = new Backtrack(familias, salas, this);
		back.start();
	}
	
	public int calcularBono(int indicePref, int grupoFamiliar) {
		int totalBono = 0;
		
		if(indicePref != 0) {
			totalBono = (2 * grupoFamiliar + indicePref + valorBono) * valorBono;
		}
		
		return totalBono;
	}
	
}
