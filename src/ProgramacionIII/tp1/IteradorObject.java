package ProgramacionIII.tp1;

import java.util.Iterator;

public class IteradorObject implements Iterator<Object>{
	private NodeDouble navegador;
	
	public IteradorObject(NodeDouble first) {
		this.navegador = first;
	}
	
	@Override
	public boolean hasNext() {
		return this.navegador != null;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		Object info = this.navegador.getInfo();
		this.navegador = this.navegador.getNext();
		return info;
	}
	
	public boolean hasPrev() {
		return this.navegador.getNext() != null;
	}
	
	public Object prev() {
		Object info = this.navegador.getInfo();
		this.navegador = this.navegador.getPrev();
		return info;
	}
	
}
