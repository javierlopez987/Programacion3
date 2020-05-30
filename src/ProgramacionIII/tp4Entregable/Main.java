package ProgramacionIII.tp4Entregable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

	
	public static void main(String... args) {
		
		
		CSVReader reader = new CSVReader("./data/familias.csv");
		
		ArrayList<Familia> familias = reader.read();
		
		for (Familia familia: familias)
			System.out.println(familia);
		
		Map<Integer, Familia> resultado = greedy(familias);
		
		System.out.println(resultado);
		
	}
	
	public static Map<Integer, Familia> greedy(List<Familia> familias) {
		
		
		
		return null;
	}
}
