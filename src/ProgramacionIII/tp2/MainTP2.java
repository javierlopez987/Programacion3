package ProgramacionIII.tp2;

public class MainTP2 {

	public static void main(String[] args) {
		Tree arbol = new Tree(10);
		arbol.add(15);
		arbol.add(80);
		arbol.add(99);
		arbol.add(20);
		arbol.add(60);
		arbol.add(25);
		arbol.add(18);
		arbol.add(22);
		arbol.add(25);
		arbol.add(45);
		
		arbol.printPreOrder();
		System.out.println();
		arbol.printInOrder();
		System.out.println();
		arbol.printPosOrder();
		System.out.println();
		
		System.out.println(arbol.hasElem(10));
		System.out.println(arbol.hasElem(45));
		System.out.println(arbol.hasElem(50));
		System.out.println(arbol.hasElem(99));
		System.out.println(arbol.hasElem(9));
		
		System.out.println(arbol.getHeight());
	}
}
