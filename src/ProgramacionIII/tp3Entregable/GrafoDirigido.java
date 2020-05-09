package ProgramacionIII.tp3Entregable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {
	private Map<Integer, Map<Integer, Arco<T>>> vertices;
	
	
	public GrafoDirigido() {
		this.vertices = new LinkedHashMap<Integer, Map<Integer, Arco<T>>>();
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		this.vertices.put(verticeId, null);
	}

	@Override
	public void borrarVertice(int verticeId) {
		this.vertices.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		Arco<T> nuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
		if(tmp == null) {
			tmp = new LinkedHashMap<Integer, Arco<T>>();
		}
		if(!tmp.containsKey(verticeId2)) {
			tmp.put(verticeId2, nuevo);
			this.vertices.put(verticeId1, tmp);
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		tmp.remove(verticeId2);
		this.vertices.put(verticeId1, tmp);

	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
