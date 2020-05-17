package ProgramacionIII.tp3Entregable;

/*
 * Documentacion consultada
 * https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
 */


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {
	/*
	 * MAP VERTICES
	 * las claves representan los id de vértices
	 * los valores son mapas con claves y valores
	 * 		cuyas claves representan los id de vertices adyacentes 
	 * 		y cuyos valores contienen el Arco (si existe)
	 */
	private Map<Integer, Map<Integer, Arco<T>>> vertices;
	
	/*
	 * CONTRUCTOR GrafoDirigido()
	 * Elegí implementar con LinkedHashMap porque "es ligeramente más rápida a la hora de acceder 
	 * a los elementos que su superclase (HashMap), pero más lenta a la hora de añadirlos".
	 */
	public GrafoDirigido() {
		this.vertices = new LinkedHashMap<Integer, Map<Integer, Arco<T>>>();
	}
	
	/*
	 * METODO agregarVertice(int verticeId)
	 * Complejidad: 2 * O(1)
	 * En el peor de los casos ejecutará los dos métodos: get y luego put
	 * los cuales en la implementacion LinkedHashMap son constant-time performance
	 */
	@Override
	public void agregarVertice(int verticeId) {
		
		if(this.vertices.get(verticeId) == null) { //Control que no permite sobreescribir vertices
			this.vertices.put(verticeId, new LinkedHashMap<Integer, Arco<T>>());
		}
	}

	/*
	 * METODO borrarVertice(int verticeId)
	 * Complejidad: O(v) donde v es la cantidad de vértices del grafo.
	 * En el peor de los casos, recorrerá hasta el último elemento
	 * para encontrar el vértice a ser borrado.
	 */
	@Override
	public void borrarVertice(int verticeId) {
		
		Iterator<Integer> it = this.vertices.keySet().iterator(); //Itera sobre los id de vertices:
		
		while(it.hasNext()) { 
			Integer auxId = it.next();
			if(this.existeArco(auxId, verticeId)) {
				this.borrarArco(auxId, verticeId); //Si existen arcos de entrada, se borran
			}
		}
		
		this.vertices.remove(verticeId); //Borra la clave: elimina vertice y arcos de salida
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		
		if(!tmp.containsKey(verticeId2) && this.contieneVertice(verticeId2)) {
			Arco<T> nuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
			tmp.put(verticeId2, nuevo);
			this.vertices.put(verticeId1, tmp);
		}
	}

	/*
	 * Complejidad: O(1) + O(va) donde "va" es la cantidad vertices adyacentes a verticeId1
	 * En el peor de los casos recorre todos los vertices adyacentes a verticeId1
	 * para encontrar la clave (verticeId2) que contiene el arco a ser eliminado
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1); //O(1) ya que LinkedHasMap.get(key) es "constant-time performance"
		
		// if(this.vertices.containsKey(verticeId1)) posee complejidad O(n) por eso no implemento ese control acá
		if(tmp != null) // se utiliza en lugar de this.vertices.containsKey(verticeId1)
			tmp.remove(verticeId2); // O(va) => va = cantidad vertices adyacentes a verticeId1
		
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		
		return this.vertices.containsKey(verticeId);
	}

	/*
	 * Complejidad: O(1) + O(va) donde va es la cantidad de vértices adyacentes al vertice origen
	 * El método get de LinkedHashMap es "constant-time performance"
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		boolean existe = false;
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1); //Obtiene datos de vértice de origen (VO)
		
		if(tmp != null) {
			existe = tmp.containsKey(verticeId2); //Devuelve dato si vertice de destino es adyacente a VO
		}
		
		return existe;
	}

	/*
	 * Complejidad: 2 * O(1)
	 * En el peor de los casos ejecutará 2 veces el get(key) de LinkedHashMap 
	 * que es es "constant-time performance"
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Arco<T> result = null;
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);

		if(tmp != null) {
			result = tmp.get(verticeId2);
		}
		
		return result;
	}

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		int size = 0;
		
		//Coleccion de mapas cuya clave es verticeDestino y cuyo valor es el Arco
		Collection<Map<Integer, Arco<T>>> tmp = this.vertices.values();
		
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
