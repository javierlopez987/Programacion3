package ProgramacionIII.tp3Entregable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BFS<T> {
	private final String VISITADO = "Visitado";
	private final String NO_VISITADO = "No visitado";
	private Grafo<T> grafo;
	private Map<Integer, Vertice> verticesBFS;
	private List<Integer> fila;
	private int tiempo;
	
	public BFS(Grafo<T> grafo) {
		this.grafo = grafo;
		this.verticesBFS = new HashMap<>();
		this.inicializarEstado();
		this.fila = new LinkedList<>();
		this.tiempo = 0;
	}
	
	private void inicializarEstado() {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			verticesBFS.put(it.next(), new Vertice(NO_VISITADO));
		}
	}
	
	public void start() {
		this.fila.clear();
		
		Iterator<Integer> it = this.verticesBFS.keySet().iterator();
		while(it.hasNext()) {
			Integer verticeId = it.next();
			if(this.verticesBFS.get(verticeId).getEstado() == NO_VISITADO) {
				this.visit(verticeId);
			}
		}
	}

	private void visit(Integer verticeId) {
		this.verticesBFS.get(verticeId).setEstado(VISITADO);
		this.fila.add(verticeId);
		
		while(!this.fila.isEmpty()) {
			Integer auxId = this.fila.remove(0);
			Iterator<Integer> itAdyacentesId = this.grafo.obtenerAdyacentes(auxId);
			while(itAdyacentesId.hasNext()) {
				Integer adyacenteId = itAdyacentesId.next();
				if(this.verticesBFS.get(adyacenteId).getEstado() == NO_VISITADO) {
					this.verticesBFS.get(adyacenteId).setEstado(VISITADO);
					this.fila.add(adyacenteId);
				}
			}
			this.tiempo = this.tiempo + 1;
			this.verticesBFS.get(auxId).setDiscoveryTime(this.tiempo);
			System.out.println("Vertice Id: " + auxId);
			System.out.println(this.verticesBFS.get(auxId));
		}
	}
}
