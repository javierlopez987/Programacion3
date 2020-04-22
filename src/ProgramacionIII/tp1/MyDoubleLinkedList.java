package ProgramacionIII.tp1;

public class MyDoubleLinkedList implements LinkedList{
	protected NodeDouble first;
	protected NodeDouble last;
	protected int size;
	
	public MyDoubleLinkedList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	public void insertFront(Object o) {
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
	
	public void insertBack(Object o) {
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
	
	public Object extractFront() {
		// TODO
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
	
	public Object extractBack() {
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
		// TODO
		return this.first == null;
	}

	public int size() {
		// TODO
		return this.size;
	}
	
	public Object get(int index) {
		// TODO
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

	public int indexOf(Object o) {
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
	public IteradorObject iterator() {
		// TODO Auto-generated method stub
		return new IteradorObject(this.first);
	}
	
	public IteradorObject iteratorBackward() {
		// TODO Auto-generated method stub
		return new IteradorObject(this.last);
	}
	
	public boolean isPalindroma() {
		boolean palindroma = false;
		IteradorObject itForward = this.iterator();
		IteradorObject itBackward = this.iteratorBackward();
		
		while(itForward.hasNext() && itBackward.hasNext()) {
			if(itForward.next().equals(itBackward.prev())) {
				palindroma = true;
			} else {
				return false;
			}
		}
		
		return palindroma;
	}
}
