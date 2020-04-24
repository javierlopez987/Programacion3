package ProgramacionIII.tp1Entregable;

import java.util.Iterator;

public class IteradorDoble implements Iterator<Integer>{
	private NodeDouble nav;
	
	public IteradorDoble(NodeDouble navegador) {
		this.nav = navegador;
	}

	@Override
	public boolean hasNext() {
		return this.nav != null;
	}

	@Override
	public Integer next() {
		Integer info = this.nav.getInfo();
		this.nav = this.nav.getNext();
		return info;
	}

	public Integer prev() {
		Integer info = this.nav.getInfo();
		this.nav = this.nav.getPrev();
		return info;
	}
	
	public Integer get() {
		return this.nav.getInfo();
	}
}
