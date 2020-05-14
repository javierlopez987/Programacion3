package ProgramacionIII.tp3Entregable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {
	private Map<Integer, Map<Integer, Arco<T>>> vertices;
	
	
	public GrafoDirigido() {
		/*
		 * LinkedHashMap "es ligeramente más rápida a la hora de acceder a los elementos que su
		 * superclase (HashMap), pero más lenta a la hora de añadirlos".
		 */
		this.vertices = new LinkedHashMap<Integer, Map<Integer, Arco<T>>>();
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		this.vertices.put(verticeId, new LinkedHashMap<Integer, Arco<T>>());
	}

	@Override
	public void borrarVertice(int verticeId) {
		
		Iterator<Integer> it = this.obtenerVertices();
		
		while(it.hasNext()) {
			Integer auxId = it.next();
			if(this.existeArco(auxId, verticeId)) {
				this.borrarArco(auxId, verticeId);
			}
		}
		
		this.vertices.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		
		if(!tmp.containsKey(verticeId2)) {
			Arco<T> nuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
			tmp.put(verticeId2, nuevo);
			this.vertices.put(verticeId1, tmp);
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		
		tmp.remove(verticeId2);
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		
		return this.vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		
		return tmp.containsKey(verticeId2);
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		
		return tmp.get(verticeId2);
	}

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		Collection<Map<Integer, Arco<T>>> tmp = this.vertices.values();
		int size = 0;
		
		Iterator<Map<Integer, Arco<T>>> it = tmp.iterator();
		while(it.hasNext()) {
			size += it.next().size();
		}
		
		return size;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId);
		
		return tmp.keySet().iterator();
	}
	
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Collection<Map<Integer, Arco<T>>> tmp = this.vertices.values();
		Collection<Arco<T>> arcos = new LinkedList<>();
		
		Iterator<Map<Integer, Arco<T>>> it = tmp.iterator();
		while(it.hasNext()) {
			arcos.addAll(it.next().values());
		}
		
		return arcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId);
		
		return tmp.values().iterator();
	}
}
