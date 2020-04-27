package ProgramacionIII.tp2;

public class Main {

	public static void main(String[] args) {
		Tree arbol = new Tree(50);
		arbol.add(20);
		arbol.add(80);
		arbol.add(15);
		arbol.add(10);
		arbol.add(18);
		arbol.add(25);
		arbol.add(22);
		arbol.add(45);
		arbol.add(40);
		arbol.add(46);
		arbol.add(39);
		arbol.add(47);
		arbol.add(99);
		arbol.add(60);
		arbol.add(25);
		arbol.add(42);
		arbol.add(41);
		arbol.add(44);
		arbol.add(43);
		
		arbol.printPreOrder();
		System.out.println();
		arbol.printInOrder();
		System.out.println();
		arbol.printPosOrder();
		System.out.println();
		
		//System.out.println(arbol.hasElem(10));
		System.out.println(arbol.hasElem(45));
		//System.out.println(arbol.hasElem(50));
		//System.out.println(arbol.hasElem(99));
		//System.out.println(arbol.hasElem(9));
		
		System.out.println(arbol.getHeight());
		
		arbol.delete(45);
		
		arbol.printPreOrder();
		System.out.println();
		arbol.printInOrder();
		System.out.println();
		arbol.printPosOrder();
		System.out.println();
		System.out.println(arbol.hasElem(45));
		System.out.println(arbol.getHeight());
	}
}
