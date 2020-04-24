package ProgramacionIII.tp1Entregable;

public class MyDoubleLinkedList implements Iterable<Integer>{
	protected NodeDouble first;
	protected NodeDouble last;
	protected int size;
	
	public MyDoubleLinkedList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	public void insertFront(Integer o) {
		NodeDouble tmp = new NodeDouble(o,null,null);
		tmp.setNext(this.first);
		if(this.first != null) {
			this.first.setPrev(tmp);
		} else {
			this.last = tmp;
		}
		this.first = tmp;
		this.size++;
	}
	
	public void insertBack(Integer o) {
		NodeDouble tmp = new NodeDouble(o, null, null);
		tmp.setPrev(this.last);
		if(this.last != null) {
			this.last.setNext(tmp);
		} else {
			this.first = tmp;
		}
		this.last = tmp;
		this.size++;
	}
	
	public Integer extractFront() {
		if(!this.isEmpty()) {
			NodeDouble tmp = this.first;
			this.first = this.first.getNext();
			if(this.first != null) {
				this.first.setPrev(null);
			} else {
				this.last = null;
			}
			this.size--;
			return tmp.getInfo();
		} else {
			return null;
		}
	}
	
	public Integer extractBack() {
		if(!this.isEmpty()) {
			NodeDouble tmp = this.last;
			this.last = this.last.getPrev();
			if(this.last != null) {
				this.last.setNext(null);
			} else {
				this.first = null;
			}
			this.size--;
			return tmp.getInfo();
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		return this.first == null;
	}

	public int size() {
		return this.size;
	}
	
	public Integer get(int index) {
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

	public int indexOf(Integer o) {
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
	public IteradorDoble iterator() {
		return new IteradorDoble(this.first);
	}
	
	public IteradorDoble iteratorBackward() {
		return new IteradorDoble(this.last);
	}
	
	@Override
	public String toString() {
		String result = new String("[");
		for(Integer element: this) {
			result += element + ",";
		}
		return result + "]";
	}
}
