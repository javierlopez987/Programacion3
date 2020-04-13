package ProgramacionIII.tp1;

public class Pila {
	protected MySimpleLinkedList lista;
	
	public Pila() {
		lista = new MySimpleLinkedList();
	}
	
	public void push(Object o) {
		this.reverse();
		this.lista.insertFront(o);
		this.reverse();
	}
	
	public Object pop() {
		this.reverse();
		Object tmp = this.lista.extractFront();
		this.reverse();
		return tmp;
	}
	
	public Object top() {
		this.reverse();
		Object tmp = this.lista.get(0);
		this.reverse();
		return tmp;
	}
	
	public void reverse() {
		MySimpleLinkedList tmp = new MySimpleLinkedList();
		int size = this.lista.size();
		int index = 0;
		
		while(index < size) {
			tmp.insertFront(this.lista.extractFront());
			index++;
		}
		
		this.lista = tmp;
	}
}
