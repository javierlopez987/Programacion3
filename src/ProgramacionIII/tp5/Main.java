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

		/*
		 * Dataset 2
		 *
		CSVReader reader2 = new CSVReader("./data/familias-2.csv");
		
		ArrayList<Familia> familias2 = reader2.read();
		
		for (Familia familia: familias2)
			System.out.println(familia);
		*/
	}
	

	private static List<Integer> backtracking(Familia famActual, int[] solPref, List<Familia> familiasConsultadas, List<Sala> salas) {
		
		if(estadoFinal(familiasConsultadas)) {
			if(solucion(salas)) {
				System.out.println(salas);
			}
		} else {
			
		}
	}


	private static boolean solucion(List<Sala> salas) {
		int reservas = 0;
		Iterator<Sala> it = salas.iterator();
		
		while(it.hasNext()) {
			Sala s = it.next();
			reservas += s.getFamilias();
		}
		return reservas == nrofamilias;
	}


	private static boolean estadoFinal(List<Familia> familiasConsultadas) {
		return familiasConsultadas.size() == nrofamilias;
	}
	
	
}
