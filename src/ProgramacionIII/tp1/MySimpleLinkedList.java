package ProgramacionIII.tp1;

public class MySimpleLinkedList {
	
	protected Node first;
	
	public MySimpleLinkedList() {
		this.first = null;
	}
	
	public void insertFront(Object o) {
		Node tmp = new Node(o,null);
		tmp.setNext(this.first);
		this.first = tmp;
	}
	
	public Object extractFront() {
		// TODO
		return null;
	}

	public boolean isEmpty() {
		// TODO
		return false;
	}

	public int size() {
		// TODO
		return 0;
	}
	
	public Object get(int index) {
		// TODO
		return null;
	}

}
