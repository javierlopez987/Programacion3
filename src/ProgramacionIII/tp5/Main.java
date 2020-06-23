package ProgramacionIII.tp5;

import java.util.ArrayList;

public class Main {
	
	public static void main(String... args) {
		
		
		/*
		 * Parámetros iniciales
		 */
		int cantDiasMax = 10;
		int capacidadMax = 30;
		int valorBono = 5;
		
		/*
		 * Dataset 1
		 */
		CSVReader reader = new CSVReader("./data/familias-1.csv");
		ArrayList<Familia> familias = reader.read();

		Backtrack back1 = new Backtrack(familias);
		Solucion propuesta1 = new Solucion(cantDiasMax, capacidadMax, valorBono);
		//back1.start(propuesta1);
		
		/*
		 * Dataset 2
		 */
		CSVReader reader2 = new CSVReader("./data/familias-2.csv");
		ArrayList<Familia> familias2 = reader2.read();
		
		Backtrack back2 = new Backtrack(familias2);
		Solucion propuesta2 = new Solucion(cantDiasMax, capacidadMax, valorBono);
		back2.start(propuesta2);

	}
	
}
