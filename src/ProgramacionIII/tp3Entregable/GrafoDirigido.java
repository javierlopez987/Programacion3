package ProgramacionIII.tp3Entregable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {
	private Map<Integer, Map<Integer, Arco<T>>> vertices;
	private Map<Integer, Arco<T>> aristas;
	
	
	public GrafoDirigido() {
		// TODO Auto-generated constructor stub
		this.vertices = new LinkedHashMap<Integer, Map<Integer, Arco<T>>>();
		this.aristas = new LinkedHashMap<Integer, Arco<T>>();
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		// TODO Auto-generated method stub
		this.vertices.put(verticeId, null);
	}

	@Override
	public void borrarVertice(int verticeId) {
		// TODO Auto-generated method stub
		this.vertices.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contieneVertice(int verticeId) {
		// TODO Auto-generated method stub
		return false;
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
