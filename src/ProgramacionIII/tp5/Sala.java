package ProgramacionIII.tp5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Sala implements Iterable<Familia>{
	private int capacidadMax;
	private int dia;
	private int ocupacion;
	private List<Familia> reservas;
	
	public Sala(int dia, int capacidadMax) {
		this.dia = dia;
		this.capacidadMax = capacidadMax;
		this.ocupacion = 0;
		this.reservas = new LinkedList<Familia>();
	}
	
	public void clear() {
		reservas.clear();
		this.ocupacion = 0;
	}
	
	public void addReserva(Familia f) {
		if(factible(f)) {
			this.reservas.add(f);
			this.ocupacion += f.miembros();
			f.setDiaReservado(dia);
		}
	}
	
	public void removeReserva(Familia f) {
		if(reservas.contains(f)) {
			this.ocupacion -= f.miembros();
			f.setDiaReservado(-1);
			this.reservas.remove(f);
		}
	}
	
	public int nroReservas() {
		return reservas.size();
	}
	
	public boolean factible(Familia f) {
		return getDisponibilidad() >= f.miembros();
	}
	
	public int getDisponibilidad() {
		return this.capacidadMax - this.ocupacion;
	}
	
	public int getOcupacion() {
		return this.ocupacion;
	}
	
	public int getFamilias() {
		return this.reservas.size();
	}
	
	public int getDia() {
		return this.dia;
	}

	@Override
	public Iterator<Familia> iterator() {
		return reservas.iterator();
	}

	@Override
	public String toString() {
		return reservas.toString();
	}
}
