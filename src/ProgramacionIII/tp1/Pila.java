package ProgramacionIII.tp1;

public class Pila {
	protected MySimpleLinkedList lista;
	
	public Pila() {
		lista = new MySimpleLinkedList();
	}
	
	public void push(Integer o) {
		lista.insertFront(o);
	}
	
	public Integer pop() {
		return lista.extractFront();
	}
	
	public Integer top() {
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
