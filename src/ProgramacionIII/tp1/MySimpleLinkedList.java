package ProgramacionIII.tp1;

import java.util.Iterator;

public class MySimpleLinkedList implements Iterable<Node>{
	protected Node first;
	private int size;
	
	public MySimpleLinkedList() {
		this.first = null;
		this.size = 0;
	}
	
	public void insertFront(Object o) {
		Node tmp = new Node(o,null);
		tmp.setNext(this.first);
		this.first = tmp;
		this.size++;
	}
	
	public Object extractFront() {
		// TODO
		Node tmp = this.first;
		this.first = this.first.getNext();
		this.size--;
		return tmp.getInfo();
	}

	public boolean isEmpty() {
		// TODO
		return this.first == null;
	}

	public int size() {
		// TODO
		return this.size;
	}
	
	public Object get(int index) {
		// TODO
		int i = 0;
		Node tmp = this.first;
		
		while(index < this.size && i < index) {
			tmp = tmp.getNext();
			i++;
		}
		
		if(index < this.size) {
			return tmp.getInfo();
		} else {
			return null;
		}
	}

	public int indexOf(Object o) {
		int index = 0;
		Node tmp = this.first;
		
		while(tmp != null && !(tmp.getInfo().equals(o))) {
			tmp = tmp.getNext();
			index++;
		}
		
		if(tmp != null) {
			return index;
		} else {
			return -1;
		}
	}

	@Override
	public Iterator<Node> iterator() {
		// TODO Auto-generated method stub
		return new IteradorSimple(this.first);
	}
}
