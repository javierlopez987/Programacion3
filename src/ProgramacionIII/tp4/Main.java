package ProgramacionIII.tp4;

import java.util.ArrayList;

public class Main {

	
	public static void main(String... args) {
		
		
		CSVReader reader = new CSVReader("./data/familias.csv");
		
		ArrayList<Familia> familias = reader.read();
		
		for (Familia familia: familias)
			System.out.println(familia);
		
	}
}
