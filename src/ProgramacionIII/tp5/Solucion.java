package ProgramacionIII.tp5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solucion {
	private Map<Integer, Sala> salas;
	private int valorBono;
	private int costo;
	
	public Solucion(int cantDiasMax, int capacidadMax, int valorBono) {
		this.salas = createSalas(cantDiasMax, capacidadMax);
		this.valorBono = valorBono;
		this.costo = 5000;
	}
	
	public int totalReservas() {
		int suma = 0;
		Iterator<Sala> itSalas = salas.values().iterator();
		
		while(itSalas.hasNext()) {
			Sala s = itSalas.next();
			suma += s.nroReservas();
		}
		
		return suma;
	}
	
	public void addSala(Sala sala) {
		salas.put(sala.getDia(), sala);
	}
	
	public void removeSala(Sala sala) {
		salas.remove(sala.getDia());
	}
	
	private Map<Integer, Sala> createSalas(int cantDiasMax, int capacidadMax) {
		Map<Integer, Sala> salas = new HashMap<Integer, Sala>();
		
		for(int i = 1; i <= cantDiasMax; i++) {
			Sala nueva = new Sala(i, capacidadMax);
			salas.put(i, nueva);
		}
		
		return salas;
	}
	
	public Sala getSala(Integer dia) {
		return salas.get(dia);
	}
	
	public int calcularBono(int indicePref, int grupoFamiliar) {
		int totalBono = 0;
		
		if(indicePref != 0) {
			totalBono = (2 * grupoFamiliar + indicePref + valorBono) * valorBono;
		}
		
		return totalBono;
	}
	
	public int getCosto() {
		return costo;
	}
	
	public void setCosto(int costo) {
		if(costo < this.costo) {
			this.costo = costo;
		}
	}
	
	public String toString() {
		return salas.toString();
	}
}
