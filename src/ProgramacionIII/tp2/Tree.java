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
	
	private void setValue(Integer value) {
		this.value = value;
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
	
	// MEJORADO - Antes de recorrer el subarbol pregunta si es posible encontrar el valor allí
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
	
	private boolean isLeaf() {
		return this.left == null && this.right == null;
	}
	
	private boolean isComplete() {
		return this.left != null && this.right != null;
	}
	
	private Integer getNMDSI() {
		Integer NMDSI = null;
		
		if(this.left != null) {
			 NMDSI = this.left.getMaxElem();
		}
		
		return NMDSI;
	}
	
	public boolean delete(Integer value) {
		boolean deletion = false;
		
		if(value < this.value) {
			if(this.left != null) {
				if(this.left.getValue() == value) {
					if(this.left.isLeaf()) {
						this.left = null;
						deletion = true;
					} else if (this.left.isComplete()){
						Integer NMDSI = this.left.getNMDSI();
						deletion = this.left.delete(NMDSI);
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
					deletion = this.left.delete(value);
				}
			}
		} else if (value > this.value) {
			if(this.right != null) {
				if(this.right.getValue() == value) {
					if(this.right.isLeaf()) {
						this.right = null;
						deletion = true;
					} else if(this.right.isComplete()) {
						Integer NMDSI = this.right.getNMDSI();
						deletion = this.right.delete(NMDSI);
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
					deletion = this.right.delete(value);
				}
			}
		}
		return deletion;
	}
	
	public Integer getMaxElem() {
		Integer max = null;
		
		if(this.right != null) {
			max = this.right.getMaxElem();
		} else {
			max = this.value;
		}
		
		return max;
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
