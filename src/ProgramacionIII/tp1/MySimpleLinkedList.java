package ProgramacionIII.tp1;

public class MySimpleLinkedList implements Iterable<Integer>{
	protected Node first;
	private int size;
	
	public MySimpleLinkedList() {
		this.first = null;
		this.size = 0;
	}
	
	public void insertFront(Integer o) {
		Node tmp = new Node(o,null);
		tmp.setNext(this.first);
		this.first = tmp;
		this.size++;
	}
	
	public Integer extractFront() {
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
	
	public Integer get(int index) {
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

	public int indexOf(Integer o) {
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
	
	public MySimpleLinkedList reverse() {
		MySimpleLinkedList tmp = new MySimpleLinkedList();
		IteradorSimple it = this.iterator();
		while(it.hasNext()) {
			tmp.insertFront(it.next());
		}
		return tmp;
	}

	@Override
	public IteradorSimple iterator() {
		// TODO Auto-generated method stub
		return new IteradorSimple(this.first);
	}
}
