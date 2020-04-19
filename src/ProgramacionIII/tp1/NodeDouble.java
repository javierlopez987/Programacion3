package ProgramacionIII.tp1;

public class NodeDouble{
	private Object info;
	private NodeDouble next;
	private NodeDouble prev;
	
	public NodeDouble() {
		this.info = null;
		this.next = null;
		this.prev = null;
	}
	
	public NodeDouble(Object o, NodeDouble n, NodeDouble p) {
		this.setInfo(o);
		this.setNext(n);
		this.setPrev(p);
	}
	
	public NodeDouble getNext() {
		return next;
	}

	public void setNext(NodeDouble next) {
		this.next = next;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
	
	public void setPrev(NodeDouble prev) {
		this.prev = prev;
	}
	
	public NodeDouble getPrev() {
		return this.prev;
	}
}
