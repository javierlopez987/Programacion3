package ProgramacionIII.tp5;

import java.util.ArrayList;

public class Main {

	
	public static void main(String... args) {
		
		
		CSVReader reader = new CSVReader("./data/familias-1.csv");
		
		ArrayList<Familia> familias = reader.read();
		
		for (Familia familia: familias)
			System.out.println(familia);


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
