package ProgramacionIII.tp5;

import java.util.Iterator;
import java.util.List;

public class Backtrack {
	final int MAXPREF = 3;
	private List<Familia> familias;
	private List<Sala> salas;
	private int nrofamilias;
	private Long contador;
	
	public Backtrack (List<Familia> familias, List<Sala> salas, Solucion solucion) {
		this.familias = familias;
		this.salas = salas;
		this.nrofamilias = familias.size();
		this.contador = 0L;
	}
	
	public void start() {
		int[][] solucion = new int[nrofamilias][MAXPREF];
		backtracking(0, salas, solucion);
	}
	
	private int[][] backtracking(int indiceFamActual, List<Sala> salas, int[][] solucion) {
		int indice = 0;
		
		if(contador % 1000000 == 0) {
			System.out.println(contador);
		}
		contador++;
		
		if(estadoFinal(indiceFamActual)) {
			imprimirMatriz(solucion);
			if(solucion(salas)) {
			}
		} else {
			while(indice < MAXPREF) {
				
				// Hago cambios
				solucion[indiceFamActual][indice] = 1;
				indiceFamActual++;
				
				//Llama a backtracking
				backtracking(indiceFamActual, salas, solucion);
				
				//Deshago cambios
				indiceFamActual--;
				solucion[indiceFamActual][indice] = 0;
				
				indice++;
			} 
				
		}
		
		return solucion;
	}

	private boolean solucion(List<Sala> salas) {
		int reservas = 0;
		Iterator<Sala> it = salas.iterator();
		
		while(it.hasNext()) {
			Sala s = it.next();
			reservas += s.getFamilias();
		}
		return reservas == nrofamilias;
	}
	
	private boolean estadoFinal(int indiceFamActual) {
		return indiceFamActual == nrofamilias;
	}

	private void imprimirMatriz(int[][] mat) {
		for(int i = 0; i < nrofamilias; i++) {
			for(int j = 0; j < MAXPREF; j++) {
				System.out.print(mat[i][j] + ", ");
			}
			System.out.print("\t");
		}
		System.out.println("Estado Final: " + contador);
	}
}
