package ProgramacionIII.tp3Entregable;

public class Vertice {
	private String color;
	private int discovery;
	private int finishing;
	
	public Vertice(String color) {
		this.color = color;
		this.discovery = 0;
		this.finishing = 0;
	}
	
	public void setDiscoveryTime(int time) {
		this.discovery = time;
	}
	
	public void setFinishingTime(int time) {
		this.finishing = time;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public int getDiscoveryTime() {
		return this.discovery;
	}
	
	public int getFinishingTime() {
		return this.finishing;
	}
}
