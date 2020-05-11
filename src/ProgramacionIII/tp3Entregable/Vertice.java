package ProgramacionIII.tp3Entregable;

public class Vertice {
	private String estado;
	private int discovery;
	private int finishing;
	
	public Vertice(String estado) {
		this.estado = estado;
		this.discovery = 0;
		this.finishing = 0;
	}
	
	public void setDiscoveryTime(int time) {
		this.discovery = time;
	}
	
	public void setFinishingTime(int time) {
		this.finishing = time;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public int getDiscoveryTime() {
		return this.discovery;
	}
	
	public int getFinishingTime() {
		return this.finishing;
	}
	
	public String toString() {
		return "Estado: " + this.estado + 
				" DiscoveryTime: " + this.discovery + 
				" FinishingTime: " + this.finishing;
	}
}
