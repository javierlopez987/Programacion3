package ProgramacionIII.tp2;

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

	/* Se agrega condición: si existe el valor ingresado no hace nada
	 *
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
	//O(n) - Recorre todos los elementos del arbol
	
	public boolean hasElem(Integer value) {
		if(this.value == value) {
			return true;
		} else if (this.left.hasElem(value)){
			return true;
		} else if (this.right.hasElem(value)) {
			return true;
		} else {
			return false;
		}
	}
	 */
	
	// MEJORADO - Antes de recorrer el subarbol pregunta si es posible encontrar el valor allí
	public boolean hasElem(Integer value) {
		boolean hasElem = false;
		
		if (value == this.value) {
			hasElem = true;
		} else if (value < this.value){
			if(this.left != null) {
				hasElem = this.left.hasElem(value);
			}
		} else if (value > this.value) {
			if(this.right != null) {
				hasElem = this.right.hasElem(value);
			}
		}
		
		return hasElem;
	}
	
	public int getHeight() {
		int height = 0;
		int leftHeight = 0;
		int rightHeight = 0;
		
		if(this.left != null) {
			leftHeight += this.left.getHeight();
		}
		
		if (this.right != null) {
			rightHeight += this.right.getHeight();
		}
		
		if (leftHeight >= rightHeight) {
			height = leftHeight;
		} else {
			height = rightHeight;
		}
		
		height++;
		
		return height;
	}
	
	public boolean delete(Integer value) {
		return false;
	}
	
	public void printPreOrder() {
		System.out.print(this);
		if(this.left != null)
		this.left.printPreOrder();
		if(this.right != null)
		this.right.printPreOrder();
	}
	
	public void printPosOrder() {
		if(this.left != null)
		this.left.printPosOrder();
		if(this.right != null)
		this.right.printPosOrder();
		System.out.print(this);
	}
	
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
}
