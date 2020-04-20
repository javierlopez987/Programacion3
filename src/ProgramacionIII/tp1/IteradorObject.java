package ProgramacionIII.tp1;

import java.util.Iterator;

public class IteradorObject implements Iterator<Object>{
	private NodeDouble nav;
	
	public IteradorObject(NodeDouble navegador) {
		this.nav = navegador;
	}
	
	@Override
	public boolean hasNext() {
		return this.nav != null;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		Object info = this.nav.getInfo();
		this.nav = this.nav.getNext();
		return info;
	}
	
	public Object prev() {
		Object info = this.nav.getInfo();
		this.nav = this.nav.getPrev();
		return info;
	}
	
}
