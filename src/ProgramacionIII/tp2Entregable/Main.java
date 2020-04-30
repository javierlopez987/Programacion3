package ProgramacionIII.tp2Entregable;

public class Main {
	final static int MAXNUM = 40;
	final static int MAXNODE = 15; 
	final static int MAXDELETE = 10;

	public static void main(String[] args) {
		Tree arbol = generarArbol();
		
		procesarTestRandom(arbol);
		
	}
	
	public static void procesarTestRandom(Tree arbol) {
		System.out.print("InOrder: ");
		arbol.printInOrder();
		System.out.println();
		
		System.out.print("PreOrder: ");
		arbol.printPreOrder();
		System.out.println();
		
		System.out.print("PosOrder: ");
		arbol.printPosOrder();
		System.out.println();
		
		System.out.print("Cantidad de Elementos: ");
		System.out.println(arbol.getCantElem());
		
		System.out.print("Altura: ");
		System.out.println(arbol.getHeight());
		
		System.out.print("Frontera: ");
		System.out.println(arbol.getFrontera());
		
		System.out.print("LongestBranch: ");
		System.out.println(arbol.getLongestBranch());
		
		System.out.println("Arbol");
		for(int i = 0; i < arbol.getHeight(); i++) {
			System.out.println("Nivel " + i + ": " + arbol.getElemAtLevel(i));
		}
		
		for(int i = 0; i < MAXDELETE; i++) {
			int toBeDelete = getRandomValue();
			if(arbol.delete(toBeDelete)) {
				System.out.println("Deleted:" + toBeDelete);
			}
		}
		
		System.out.print("InOrder: ");
		arbol.printInOrder();
		System.out.println();
		
		System.out.print("PreOrder: ");
		arbol.printPreOrder();
		System.out.println();
		
		System.out.print("PosOrder: ");
		arbol.printPosOrder();
		System.out.println();
		
		System.out.print("Cantidad de Elementos: ");
		System.out.println(arbol.getCantElem());
		
		System.out.print("Altura: ");
		System.out.println(arbol.getHeight());
		
		System.out.println("Arbol");
		for(int i = 0; i < arbol.getHeight(); i++) {
			System.out.println("Nivel " + i + ": " + arbol.getElemAtLevel(i));
		}
	}
	
	public static int getRandomValue() {
		return ((int)(Math.random() * MAXNUM) + 1);
	}
	
	public static Tree generarArbol() {
		int randomValue = getRandomValue();
		Tree arbol = new Tree(randomValue);
		
		System.out.print("Secuencia de valores ingresados: ");
		while(arbol.getCantElem() < MAXNODE) {
			System.out.print(randomValue + ", ");
			randomValue = getRandomValue();
			arbol.add(randomValue);
		}
		System.out.println(randomValue);
		return arbol;
	}
}
