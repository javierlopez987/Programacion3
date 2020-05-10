package ProgramacionIII.tp3Entregable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DFS<T> {
	private final String BLANCO = "No visitado";
	private final String AMARILLO = "Siendo visitado";
	private final String NEGRO = "Visitado totalmente";
	private Grafo<T> grafo;
	private Map<Integer, String> estadoVertices;
	private int tiempo;
	
	public DFS(Grafo<T> grafo) {
		this.grafo = grafo;
		this.estadoVertices = new HashMap<>();
		this.tiempo = 0;
		this.inicializarEstado();
	}
	
	private void inicializarEstado() {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			estadoVertices.put(it.next(), BLANCO);
		}
	}
	
	public void start() {
		Iterator<Integer> itGrafo = this.grafo.obtenerVertices();
		Iterator<String> itEstado = this.estadoVertices.values().iterator();
		
		while(itGrafo.hasNext() && itEstado.hasNext()) {
			if(itEstado.next() == BLANCO) {
				this.visit(itGrafo.next());
			}
		}
	}
	
	private void visit(Integer vertice) {
		this.estadoVertices.put(vertice, AMARILLO);
		this.tiempo = this.tiempo++;
	}
	
}
