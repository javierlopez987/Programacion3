package ProgramacionIII.tp5;

import java.util.Iterator;
import java.util.List;

public class Backtrack {
	final int MAXPREF = 3;
	private List<Familia> familias;
	private int nrofamilias;
	private Long contador;
	private int minBono;
	
	public Backtrack (List<Familia> familias) {
		this.familias = familias;
		this.nrofamilias = familias.size();
		this.contador = 0L;
	}
	
	public void start(Solucion propuesta) {
		this.minBono = propuesta.getCosto();
		backtracking(0, propuesta, 0);
		System.out.println("Total de estados visitados: " + contador);
	}
	
	private void backtracking(int indiceFamActual, Solucion salas, int bono) {
		
		contador++;
		
		if(estadoFinal(indiceFamActual)) {
			if(solucion(salas)) {
				salas.setCosto(bono);
				minBono = bono;
				System.out.println(salas);
				System.out.println("Costo de bono: " + salas.getCosto());
				System.out.println("Estado visitado N°: " + contador);
			}
		} else {
			Familia f = familias.get(indiceFamActual);
			Iterator<Integer> it = f.iterator();
			while(it.hasNext()) {
				
				int dia = it.next();
				Sala sala = salas.getSala(dia);
				
				if(sala.factible(f)) {
					
					// Hace cambios
					indiceFamActual++;
					sala.addReserva(f);
					bono += salas.calcularBono(f.indiceDePreferencia(dia), f.miembros());
					
					if(bono < minBono) {
						//Llama a backtracking
						backtracking(indiceFamActual, salas, bono);
					}
					
					//Deshace cambios
					sala.removeReserva(f);
					indiceFamActual--;
					bono-= salas.calcularBono(f.indiceDePreferencia(dia), f.miembros());
				}
			} 
		}
	}
	
	private boolean solucion(Solucion s) {
		return s.totalReservas() == nrofamilias;
	}
	
	private boolean estadoFinal(int indiceFamActual) {
		return indiceFamActual == nrofamilias;
	}
	
}
