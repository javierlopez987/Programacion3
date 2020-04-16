package ProgramacionIII.tp1;

import java.util.Iterator;

public class IteradorSimple implements Iterator<Integer>{
	private Node pos;
	
	public IteradorSimple(Node first) {
		this.pos = first;
	}
	
	@Override
	public boolean hasNext() {
		return this.pos != null;
	}

	@Override
	public Integer next() {
		Integer info = this.pos.getInfo();
		this.pos = this.pos.getNext();
		return info;
	}
	
	public Integer get() {
		return this.pos.getInfo();
	}
	
}
