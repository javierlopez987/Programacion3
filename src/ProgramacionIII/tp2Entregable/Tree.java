package ProgramacionIII.tp2Entregable;

import java.util.LinkedList;
import java.util.List;

public class Tree {
	private int value;
	private Tree left;
	private Tree right;

	public Tree(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public Integer getValue() {
		return value;
	}
	
	private void setValue(Integer value) {
		this.value = value;
	}

	/* 
	 * Se agrega condición: si ya existe el valor ingresado no hace nada.
	 * Se agrega indicador de nivel.
	 */
	public void add(int newValue) {
		
		if (newValue < this.value) {
			if (this.left == null) {
				this.left = new Tree(newValue);
			}
			else
				this.left.add(newValue);
		} else if(newValue > this.value ){
			if (this.right == null) {
				this.right = new Tree(newValue);
			}
			else
				this.right.add(newValue);
		}
	}
	
	/*
	 * Complejidad: O(h) donde h es la altura del arbol
	 * En el peor de los casos el elemento buscado estará en la rama más baja del arbol,
	 * el programa deberá pasar al menos una vez por cada rama hasta llegar allí.
	 */
	public boolean hasElem(Integer value) {
		boolean hasElem = false;
		
		if (value == this.value) {
			hasElem = true;
		} else if (value < this.value){
			if(this.left != null)
				hasElem = this.left.hasElem(value);
		} else if (value > this.value) {
			if(this.right != null)
				hasElem = this.right.hasElem(value);
		}
		
		return hasElem;
	}
	
	/* Complejidad: O(2h) donde es la altura del arbol
	 * Al menos una vez el programa recorrerá la altura del arbol.
	 * En el peor de los casos deberá llegar hasta el último nivel 
	 * de cada subarbol (izquierdo y derecho)
	 * 
	 */
	public int getHeight() {
		int height = 0;
		int leftHeight = 0;
		int rightHeight = 0;
		
		if(this.left != null)
			leftHeight += this.left.getHeight();
		
		if (this.right != null)
			rightHeight += this.right.getHeight();
		
		if (leftHeight >= rightHeight)
			height = leftHeight;
		else
			height = rightHeight;
		
		height++;
		
		return height;
	}
	
	/*
	 * Complejidad: O(1) operaciones lógicas sobre variables de instacia.
	 * No dependen del tamaño del arbol.
	 */
	private boolean isLeaf() {
		return this.left == null && this.right == null;
	}
	
	/*
	 * Complejidad: O(1) operaciones lógicas sobre variables de instancia.
	 * No dependen del tamaño del arbol.
	 */
	private boolean isComplete() {
		return this.left != null && this.right != null;
	}
	
	/*
	 * Complejidad: O(h) donde h es la altura del arbol.
	 * En el peor de los casos recorrerá hasta el nivel más bajo del arbol,
	 * el programa deberá pasar al menos una vez por cada nivel hasta llegar allí.
	 */
	public Integer getMaxElem() {
		Integer max = null;
		
		if(this.right != null) {
			max = this.right.getMaxElem();
		} else {
			max = this.value;
		}
		
		return max;
	}
	
	/*
	 * Complejidad: O(h) donde h es la altura del arbol.
	 * En el peor de los casos recorrerá hasta el nivel más bajo del arbol,
	 * el programa deberá pasar al menos una vez por cada nivel hasta llegar allí.
	 */
	public Integer getMinElem() {
		Integer min = null;
		
		if(this.left != null) {
			min = this.left.getMinElem();
		} else {
			min = this.value;
		}
		
		return min;
	}
	
	/*
	 * Complejidad: 0(h) donde h es la altura del arbol.
	 * En el peor de los casos comenzará desde el primer nivel [root],
	 * y deberá recorrer el subarbol izquierdo hasta el último nivel
	 */
	private Integer getNMDSI() {
		Integer NMDSI = null;
		
		if(this.left != null) {
			 NMDSI = this.left.getMaxElem();
		}
		
		return NMDSI;
	}
	
	/*
	 * Complejidad: 0(h) donde h es la altura del arbol.
	 * En el peor de los casos comenzará desde el primer nivel [root],
	 * y deberá recorrer el subarbol derecho hasta el último nivel
	 */
	private Integer getNMISD() {
		Integer NMISD = null;
		
		if(this.right != null) {
			NMISD = this.right.getMinElem();
		}
		
		return NMISD;
	}
	
	/*
	 * Complejidad: O(2h) donde h es la altura del arbol
	 * En el peor de los casos recorre dos veces la altura del arbol,
	 * pasando por cada nivel al menos una vez.
	 */
	public boolean delete(Integer value) {
		boolean deletion = false;
		
		//Corresponde al Subarbol izquierdo
		if(value < this.value) {
			if(this.left != null) {
				if(this.left.getValue() == value) { 	// Pregunta desde el padre
					this.left = this.deletion(this.left);
					deletion = true;
				} else {
					deletion = this.left.delete(value); // O(h)
				}
			}
			
		//Corresponde al Subarbol derecho
		} else if (value > this.value) {
			if(this.right != null) {
				if(this.right.getValue() == value) { 	// Pregunta desde el padre
					this.right = this.deletion(this.right);
					deletion = true;
				} else {
					deletion = this.right.delete(value); // O(h)
				}
			}
			
		// En todos los casos si el programa llega a este else 
		// quiere decir que el valor a borrar es el valor de la raiz
		} else { 
			if(this.isLeaf()) {					// Caso 1: es hoja
				this.setValue(null);
				deletion = true;
			} else if(this.isComplete() 		// Caso 2: esta completo o tiene un hijo izquierdo
					|| this.left != null) { 	// la nueva raiz sera el valor del NMDSI
				Integer NMDSI = this.getNMDSI(); // O(h)
				deletion = this.delete(NMDSI); // O(h)
				this.setValue(NMDSI);
			} else {		// Caso 3: tiene un hijo derecho, la nueva raiz sera el valor del NMISD
				Integer NMISD = this.getNMISD(); // O(h)
				deletion = this.delete(NMISD); // O(h)
				this.setValue(NMISD);
			}
		}
		
		return deletion;
	}
	
	private Tree deletion(Tree rama) {
		
		if(rama.isLeaf()) { 				// Caso 1: es hoja
			rama = null;
		} else if(rama.isComplete()) {	// Caso 2: tiene dos hijos
			Integer NMDSI = rama.getNMDSI(); // Complejidad del metodo invocado: O(h)
			rama.delete(NMDSI); // Complejidad del metodo invocado: O(h)
			rama.setValue(NMDSI);
		} else { 							// Caso 3: tiene un hijo
			if(rama.left != null) { 
				rama = rama.left;
			} else {
				rama = rama.right;
			}
		}
		
		return rama;
	}
	
	/*
	 * Complejidad: O(n) donde n es cantidad de elementos del arbol.
	 * En el peor de los casos recorre la totalidad de los elementos del arbol.
	 */
	public void printPreOrder() {
		System.out.print(this);
		
		if(this.left != null)
			this.left.printPreOrder();
		else
			System.out.print("-");
		
		if(this.right != null)
			this.right.printPreOrder();
		else
			System.out.print("-");
	}
	
	/*
	 * Complejidad: O(n) donde n es cantidad de elementos del arbol.
	 * En el peor de los casos recorre la totalidad de los elementos del arbol.
	 */
	public void printPosOrder() {
		if(this.left != null)
			this.left.printPosOrder();
		
		if(this.right != null)
			this.right.printPosOrder();
		
		System.out.print(this);
	}
	
	/*
	 * Complejidad: O(n) donde n es cantidad de elementos del arbol.
	 * En el peor de los casos recorre la totalidad de los elementos del arbol.
	 */
	public void printInOrder() {
		if(this.left != null)
			this.left.printInOrder();
		
		System.out.print(this);
		
		if(this.right != null)
			this.right.printInOrder();
	}
	
	public String toString() {
		return "[" + this.value + "]";
	}
	
	/*
	 * Complejidad O(n) donde n es la cantidad de elementos del arbol.
	 * En el peor de los casos recorre cada uno de los elementos del arbol.
	 */
	public List<Integer> getLongestBranch() {
		List<Integer> tmp = new LinkedList<Integer>();
		List<Integer> leftBranch = new LinkedList<Integer>();
		List<Integer> rightBranch = new LinkedList<Integer>();
		
		
		if(this.left != null) {
			leftBranch.addAll(this.left.getLongestBranch()); // O(h)
		}
		if(this.right != null) {
			rightBranch.addAll(this.right.getLongestBranch()); // O(h)
		}
		
		tmp.add(this.getValue());
		if(leftBranch.size() >= rightBranch.size()) { // O(n) 
			tmp.addAll(leftBranch); 
		} else {
			tmp.addAll(rightBranch);
		}
		
		return tmp;
	}
	
	/*
	 * Complejidad: O(n) donde n es la cantidad de elementos del arbol.
	 * En el peor de los casos recorre todos los elementos del arbol
	 * para obtener las hojas.
	 */
	public List<Integer> getFrontera() {
		List<Integer> tmp = new LinkedList<Integer>();
		
		if(isLeaf()) {
			tmp.add(this.getValue());
		} else {
			if(this.left != null) {
				tmp.addAll(this.left.getFrontera());
			}
			if(this.right !=null) {
				tmp.addAll(this.right.getFrontera());
			}
		}
		
		return tmp;
	}
	
	/*
	 * Complejidad: O(n) donde n es la cantidad de elementos
	 * en el arbol hasta el nivel indicado inclusive.
	 * Se controla que no realice operaciones inecesariamente
	 * buscando más allá del nivel indicado porque no tendría sentido.
	 */
	public List<Integer> getElemAtLevel(int level) {
		List<Integer> tmp = new LinkedList<Integer>();
		
		if(level == 0) {
			tmp.add(this.getValue());
		} else {
			if(this.left != null) {
				tmp.addAll(this.left.getElemAtLevel(level - 1));
			}
			if(this.right !=null) {
				tmp.addAll(this.right.getElemAtLevel(level - 1));
			}
		}
		
		return tmp;
	}
	
	public int getCantElem() {
		int cantidad = 0;
		
		if(this.left != null) {
			cantidad += this.left.getCantElem();
		}
		
		cantidad++;
		
		if(this.right != null) {
			cantidad += this.right.getCantElem();
		}
		
		return cantidad;
	}
}
