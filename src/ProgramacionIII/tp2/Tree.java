package ProgramacionIII.tp2;

import java.util.LinkedList;
import java.util.List;

public class Tree {
	private final int ROOTLEVEL = 1;
	private int value;
	private Tree left;
	private Tree right;
	private int level;

	public Tree(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.level = ROOTLEVEL;
	}
	
	public Integer getValue() {
		return value;
	}
	
	private void setValue(Integer value) {
		this.value = value;
	}
	
	private void setLevel(int level) {
		this.level = level;
	}

	/* 
	 * Se agrega condición: si ya existe el valor ingresado no hace nada.
	 * Se agrega indicador de nivel.
	 */
	public void add(int newValue) {
		int level = this.level;
		
		if (newValue < this.value) {
			if (this.left == null) {
				this.left = new Tree(newValue);
				this.left.setLevel(++level);
			}
			else
				this.left.add(newValue);
		} else if(newValue > this.value ){
			if (this.right == null) {
				this.right = new Tree(newValue);
				this.right.setLevel(++level);
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
						this.left.level--;
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
						this.right.level--;
						deletion = true;
					}
				} else {
					deletion = this.right.delete(value); // O(h)
				}
			}
		} else {
			if(this.isLeaf()) {
				this.setValue(null);
				deletion = true;
			} else if(this.isComplete()) {
				Integer NMDSI = this.getNMDSI(); // O(h)
				deletion = this.delete(NMDSI); // O(h)
				this.setValue(NMDSI);
			} else {
				if(this.left != null) {
					this.value = this.left.getValue();
					this.right = this.left.right;
					this.left = this.left.left;
				} else {
					this.value = this.right.getValue();
					this.left = this.right.left;
					this.right = this.right.right;
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
		
		
		if(this.level == level) {
			tmp.add(this.getValue());
		} else if (this.level < level){ //controla la busqueda
			if(this.left != null) {
				tmp.addAll(this.left.getElemAtLevel(level));
			}
			if(this.right !=null) {
				tmp.addAll(this.right.getElemAtLevel(level));
			}
		}
		
		return tmp;
	}
}
