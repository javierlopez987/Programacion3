package ProgramacionIII.tp2;

import java.util.Collection;
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
	 * Se agrega condición: si ya existe el valor ingresado no hace nada
	 */
	public void add(int newValue) {
		if (newValue < this.value) {
			if (this.left == null)
				this.left = new Tree(newValue);
			else
				this.left.add(newValue);
		} else if(newValue > this.value ){
			if (this.right == null)
				this.right = new Tree(newValue);
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
	 * En el peor de los casos deberá llegar hasta el último nivel de cada subarbol (izquierdo y derecho)
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
	 * Complejidad: O(1) operaciones lógicas sobre variables de instacia.
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
	 * Complejidad: O(2h) donde h es la altura del arbol
	 * En el peor de los casos recorre dos veces la altura del arbol,
	 * pasando por cada nivel al menos una vez.
	 */
	public boolean delete(Integer value) {
		boolean deletion = false;
		
		if(value < this.value) {
			if(this.left != null) {
				if(this.left.getValue() == value) {
					if(this.left.isLeaf()) {
						this.left = null;
						deletion = true;
					} else if (this.left.isComplete()){
						Integer NMDSI = this.left.getNMDSI(); // O(h)
						deletion = this.left.delete(NMDSI); // O(h)
						this.left.setValue(NMDSI);
					} else {
						if(this.left.left != null) {
							this.left = this.left.left;
						} else {
							this.left = this.left.right;
						}
						deletion = true;
					}
				} else {
					deletion = this.left.delete(value); // O(h)
				}
			}
		} else if (value > this.value) {
			if(this.right != null) {
				if(this.right.getValue() == value) {
					if(this.right.isLeaf()) {
						this.right = null;
						deletion = true;
					} else if(this.right.isComplete()) {
						Integer NMDSI = this.right.getNMDSI(); // O(h)
						deletion = this.right.delete(NMDSI); // O(h)
						this.right.setValue(NMDSI);
					} else {
						if(this.right.left != null) {
							this.right = this.right.left;
						} else {
							this.right = this.right.right;
						}
						deletion = true;
					}
				} else {
					deletion = this.right.delete(value); // O(h)
				}
			}
		}
		return deletion;
	}
	
	/*
	 * Complejidad: O(n) donde n es cantidad de elementos del arbol.
	 * En el peor de los casos recorre la totalidad de los elementos del arbol.
	 */
	public void printPreOrder() {
		System.out.print(this);
		
		if(this.left != null)
			this.left.printPreOrder();
		
		if(this.right != null)
			this.right.printPreOrder();
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
	 * Complejidad O(n) donde n es la cantidad de elementos de un subarbol.
	 * En el peor de los casos y suponiendo que h es la altura del arbol, 
	 * recorre dos veces la altura de la rama izquierda (h-1)
	 * para obtener la altura de esa rama, 
	 * luego recorre dos veces la altura de la rama derecha (h-1) para obtener su altura 
	 * -propio de la implementación de getHeight()-
	 * Luego compara las alturas de ramas, elige la más larga y recorre todos sus elementos (n)
	 * Por lo tanto en el peor de los casos es O(4(h-1)) + O(n)
	 */
	public List<Integer> getLongestBranch() {
		List<Integer> tmp = new LinkedList<Integer>();
		int leftHeight = 0;
		int rightHeight = 0;
		
		
		if(this.left != null) {
			leftHeight = this.left.getHeight(); // O(h)
		}
		if(this.right != null) {
			rightHeight = this.right.getHeight(); // O(h)
		}
		
		if(leftHeight >= rightHeight) { // O(n) 
			tmp.addAll(this.left.getBranch()); 
		} else {
			tmp.addAll(this.right.getBranch());
		}
		
		return tmp;
	}

	/*
	 * Complejidad: O(n) donde n es la cantidad de elementos del subarbol
	 */
	private List<Integer> getBranch() {
		List<Integer> tmp = new LinkedList<Integer>();
		
		if(this.left != null) {
			tmp.addAll(this.left.getBranch());
		}
		
		tmp.add(this.getValue());
		
		if(this.right != null) {
			tmp.addAll(this.right.getBranch());
		}
		
		return tmp;
	}
}
