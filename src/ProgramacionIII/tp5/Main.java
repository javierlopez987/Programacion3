package ProgramacionIII.tp5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
	private static int nrofamilias;
	
	public static void main(String... args) {
		
		
		/*
		 * Parámetros iniciales
		 */
		int cantDiasMax = 10;
		int capacidadMax = 30;
		int valorBono = 5;
		CSVReader reader = new CSVReader("./data/familias-1.csv");
		ArrayList<Familia> familias = reader.read();
		nrofamilias = familias.size();

		
		Solucion propuesta1 = new Solucion(familias, cantDiasMax, capacidadMax, valorBono);
		propuesta1.getSolucion();

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
