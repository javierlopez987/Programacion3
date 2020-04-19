package ProgramacionIII.tp1;

import java.util.Iterator;

public class MyDoubleLinkedList implements Iterable<Object>{
	protected NodeDouble first;
	protected NodeDouble last;
	protected int size;
	
	public MyDoubleLinkedList() {
		this.first = null;
		this.size = 0;
	}
	
	public void insertFront(Object o) {
		NodeDouble tmp = new NodeDouble(o,null,null);
		tmp.setNext(this.first);
		if(this.first != null) {
			this.first.setPrev(tmp);
		}
		this.first = tmp;
		this.size++;
	}
	
	public void insertBack(Object o) {
		
	}
	
	public Object extractFront() {
		// TODO
		NodeDouble tmp = this.first;
		this.first = this.first.getNext();
		this.first.setPrev(null);
		this.size--;
		return tmp.getInfo();
	}
	
	public Object extractBack() {
		return null;
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
		NodeDouble tmp = this.first;
		
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
		NodeDouble tmp = this.first;
		
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
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
