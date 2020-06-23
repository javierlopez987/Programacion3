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

		Backtrack back = new Backtrack(familias);
		Solucion propuesta1 = new Solucion(cantDiasMax, capacidadMax, valorBono);
		back.start(propuesta1);
		
		/*
		 * Dataset 2
		 *
		CSVReader reader2 = new CSVReader("./data/familias-2.csv");
		ArrayList<Familia> familias2 = reader2.read();
		
		for (Familia familia: familias2)
			System.out.println(familia);
		*/
	}
	
}
