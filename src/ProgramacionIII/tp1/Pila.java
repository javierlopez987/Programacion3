package ProgramacionIII.tp1;

public class Pila {
	protected MySimpleLinkedList lista;
	
	public Pila() {
		lista = new MySimpleLinkedList();
	}
	
	public void push(Object o) {
		lista.insertFront(o);
	}
	
	public Object pop() {
		return lista.extractFront();
	}
	
	public Object top() {
		return lista.get(0);
	}
	
	public void reverse() {
		MySimpleLinkedList tmp = new MySimpleLinkedList();
		int size = this.lista.size();
		int index = 0;
		
		while(index < size) {
			tmp.insertFront(this.pop());
			index++;
		}
		
		this.lista = tmp;
	}
}
