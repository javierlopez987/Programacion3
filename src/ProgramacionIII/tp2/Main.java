package ProgramacionIII.tp2;

public class Main {

	public static void main(String[] args) {
		Tree arbol = new Tree(50);
		
		//procesarTest1(arbol);
		//procesarTest2(arbol);
		procesarTest3(arbol);
		
	}
	
	public static void procesarTest1(Tree arbol) {
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
		
		System.out.println("getHeight():");
		System.out.println(arbol.getHeight());
		
		System.out.println("getFrontera():");
		System.out.println(arbol.getFrontera());
		
		System.out.println("getLongestBranch():");
		System.out.println(arbol.getLongestBranch());
		
		System.out.println("getElemAtLevel(4):");
		System.out.println(arbol.getElemAtLevel(4));
		
		System.out.println("getElemAtLevel(8):");
		System.out.println(arbol.getElemAtLevel(8));
		
		//arbol.delete(45);
		arbol.delete(50);
		
		arbol.printPreOrder();
		System.out.println();
		arbol.printInOrder();
		System.out.println();
		arbol.printPosOrder();
		System.out.println();
		System.out.println(arbol.hasElem(45));
		System.out.println(arbol.getHeight());
		
		System.out.println(arbol.getLongestBranch());
		
		System.out.println(arbol.getFrontera());
		
		System.out.println(arbol.getElemAtLevel(4));
		
		System.out.println(arbol.getElemAtLevel(8));
	}
	
	public static void procesarTest2(Tree arbol) {
		arbol.add(20);
		//arbol.add(80);
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
		//arbol.add(99);
		//arbol.add(60);
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
		
		System.out.println(arbol.getFrontera());
		
		System.out.println(arbol.getLongestBranch());
		
		System.out.println(arbol.getElemAtLevel(4));
		
		System.out.println(arbol.getElemAtLevel(8));
		
		//arbol.delete(45);
		arbol.delete(50);
		
		arbol.printPreOrder();
		System.out.println();
		arbol.printInOrder();
		System.out.println();
		arbol.printPosOrder();
		System.out.println();
		System.out.println(arbol.hasElem(45));
		System.out.println(arbol.getHeight());
		
		System.out.println(arbol.getLongestBranch());
		
		System.out.println(arbol.getFrontera());
		
		System.out.println(arbol.getElemAtLevel(4));
		
		System.out.println(arbol.getElemAtLevel(8));
	}
	
	public static void procesarTest3(Tree arbol) {
		arbol.add(80);
		arbol.add(99);
		arbol.add(60);
		
		System.out.println("printInOrder():");
		arbol.printInOrder();
		System.out.println();
		
		System.out.println("getHeight():");
		System.out.println(arbol.getHeight());
		
		System.out.println("getFrontera():");
		System.out.println(arbol.getFrontera());
		
		System.out.println("getLongestBranch():");
		System.out.println(arbol.getLongestBranch());
		
		System.out.println("getElemAtLevel(4):");
		System.out.println(arbol.getElemAtLevel(4));
		
		System.out.println("getElemAtLevel(8):");
		System.out.println(arbol.getElemAtLevel(8));
		
		//arbol.delete(45);
		arbol.delete(50);
		
		System.out.println("printInOrder():");
		arbol.printInOrder();
		System.out.println();
		
		
		System.out.println("getHeight():");
		System.out.println(arbol.getHeight());
		
		System.out.println("getFrontera():");
		System.out.println(arbol.getFrontera());
		
		System.out.println("getLongestBranch():");
		System.out.println(arbol.getLongestBranch());
		
		System.out.println("getElemAtLevel(4):");
		System.out.println(arbol.getElemAtLevel(4));
		
		System.out.println("getElemAtLevel(8):");
		System.out.println(arbol.getElemAtLevel(8));
	}
}
