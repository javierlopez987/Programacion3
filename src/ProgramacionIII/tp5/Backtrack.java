package ProgramacionIII.tp5;

import java.util.ArrayList;
import java.util.List;

public class Backtrack {
	private List<Familia> familias;
	private List<Integer> familiasCandidatas;
	private List<Sala> salas;
	private Solucion solucion;
	private int nrofamilias;
	
	public Backtrack (List<Familia> familias, List<Sala> salas, Solucion solucion) {
		this.familias = familias;
		this.salas = salas;
		this.solucion = solucion;
		this.familiasCandidatas = new ArrayList<Integer>();
		this.nrofamilias = 0;
	}
	
	public List<Sala> start() {
		return backtracking(0, familias.get(0).diaPreferido(), familiasCandidatas, 0);
	}
	
	private List<Sala> backtracking(int idFamilia, int nroSala, List<Integer> familiasCandidatas, int costoTotal) {
		List<Sala> solucionConBack = new ArrayList<Sala>(salas);
		
		Familia f = familias.get(idFamilia);
		
		if(estadoFinal())
		
		if(salas.get(nroSala).) {
			f.setDiaReservado(nroSala);
			salas.get(nroSala).addReserva(f);
		} else {
			int siguienteSala = f.getNextDia();
			
			while(siguienteSala != -1) { // controla llegó al final de nodo hijos
				
				int costo = costoTotal;
				costo += agregarASolucion(siguienteSala, f);
				backtracking(idFamilia, siguienteSala, familiasCandidatas, costo);
				costo -= quitarDeSolucion(siguienteSala, f);
				
			}
		}
		
		return solucionConBack;
	}
	
	private boolean estadoFinal() {
		return nrofamilias == familias.size();
	}

	private int agregarASolucion(int siguienteSala, Familia f) {
		salas.get(siguienteSala).addReserva(f);
		nrofamilias++;
		return solucion.calcularBono(f.indiceDePreferencia(siguienteSala), f.miembros());
	}
	
	private int quitarDeSolucion(int siguienteSala, Familia f) {
		salas.get(siguienteSala).removeReserva(f);
		nrofamilias--;
		return solucion.calcularBono(f.indiceDePreferencia(siguienteSala), f.miembros());
	}

}
