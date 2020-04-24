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

	public void add(int newValue) {
		if (newValue < this.value) {
			if (this.left == null)
				this.left = new Tree(newValue);
			else
				this.left.add(newValue);
		} else {
			if (this.right == null)
				this.right = new Tree(newValue);
			else
				this.right.add(newValue);
		}
	}
	
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
	
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
	 /* Replantear
	public int getHeight() {
		int height = 0;
		if(isLeaf() ) {
			return 1;
		} else {
			this.left.getHeight();
		}
	}
	*/
}
