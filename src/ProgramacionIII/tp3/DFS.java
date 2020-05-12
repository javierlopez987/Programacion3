package ProgramacionIII.tp3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DFS<T> {
	private final String BLANCO = "Blanco";
	private final String AMARILLO = "Amarillo";
	private final String NEGRO = "Negro";
	private Grafo<T> grafo;
	private Map<Integer, Vertice> verticesDFS;
	private int tiempo;
	
	public DFS(Grafo<T> grafo) {
		this.grafo = grafo;
		this.verticesDFS = new HashMap<>();
		this.tiempo = 0;
		this.inicializarEstado();
	}
	
	private void inicializarEstado() {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			verticesDFS.put(it.next(), new Vertice(BLANCO));
		}
	}
	
	public void start() {
		Iterator<Integer> itVerticeId = this.grafo.obtenerVertices();
		Iterator<Vertice> itVertice = this.verticesDFS.values().iterator();
		
		while(itVerticeId.hasNext() && itVertice.hasNext()) {
			if(itVertice.next().getEstado() == BLANCO) {
				this.visit(itVerticeId.next());
			}
		}
	}
	
	private void visit(Integer verticeId) {
		this.verticesDFS.get(verticeId).setEstado(AMARILLO);;
		this.tiempo = this.tiempo + 1;
		this.verticesDFS.get(verticeId).setDiscoveryTime(this.tiempo);
		
		Iterator<Integer> itAdyacentesId = this.grafo.obtenerAdyacentes(verticeId);
		while(itAdyacentesId.hasNext()) {
			Integer adyacenteId = itAdyacentesId.next();
			if(this.verticesDFS.get(adyacenteId).getEstado() == BLANCO) {
				this.visit(adyacenteId);
			} else if (this.verticesDFS.get(adyacenteId).getEstado() == AMARILLO) {
				System.out.println("Grafo cíclico verificado en arco " + 
						this.grafo.obtenerArco(verticeId, adyacenteId));
			}
		}
		
		this.verticesDFS.get(verticeId).setEstado(NEGRO);
		this.tiempo = this.tiempo + 1;
		this.verticesDFS.get(verticeId).setFinishingTime(this.tiempo);
		System.out.println("Vertice Id: " + verticeId);
		System.out.println(this.verticesDFS.get(verticeId));
	}
	
}
