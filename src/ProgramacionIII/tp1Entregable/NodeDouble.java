package ProgramacionIII.tp1Entregable;

public class NodeDouble{
	private Integer info;
	private NodeDouble next;
	private NodeDouble prev;
	
	public NodeDouble() {
		this.info = null;
		this.next = null;
		this.prev = null;
	}
	
	public NodeDouble(Integer o, NodeDouble n, NodeDouble p) {
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

	public Integer getInfo() {
		return info;
	}

	public void setInfo(Integer info) {
		this.info = info;
	}
	
	public void setPrev(NodeDouble prev) {
		this.prev = prev;
	}
	
	public NodeDouble getPrev() {
		return this.prev;
	}
}
