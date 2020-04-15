package ProgramacionIII.tp1;

import java.util.Iterator;

public class IteradorSimple implements Iterator<Node>{
	Node pos;
	
	public IteradorSimple(Node first) {
		this.pos = first;
	}
	
	@Override
	public boolean hasNext() {
		return pos != null;
	}

	@Override
	public Node next() {
		Node tmp = pos;
		pos = pos.getNext();
		return tmp;
	}
	
}
