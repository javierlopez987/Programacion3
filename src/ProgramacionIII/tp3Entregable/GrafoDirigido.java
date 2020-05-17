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
	 * Las claves representan los id de vértices
	 * Los valores son mapas con claves y valores
	 * 		cuyas claves representan los id de vertices adyacentes 
	 * 		y cuyos valores contienen el Arco (si existe)
	 */
	private Map<Integer, Map<Integer, Arco<T>>> vertices;
	
	/*
	 * CONTRUCTOR GrafoDirigido()
	 * Elegí implementar con LinkedHashMap porque la estructura deberá dar mayor cantidad 
	 * de respuestas de consulta que de agregado de elementos y la documentación dice que 
	 * "es ligeramente más rápida a la hora de acceder  a los elementos que su superclase (HashMap),
	 *  pero más lenta a la hora de añadirlos".
	 */
	public GrafoDirigido() {
		this.vertices = new LinkedHashMap<Integer, Map<Integer, Arco<T>>>();
	}
	
	/*
	 * METODO agregarVertice(int verticeId)
	 * Complejidad: O(1)
	 * En el peor de los casos ejecutará los dos métodos: get y luego put
	 * los cuales en la implementacion LinkedHashMap son constant-time performance
	 */
	@Override
	public void agregarVertice(int verticeId) {
		
		if(this.vertices.get(verticeId) == null) { //Control que no se permita sobreescribir vertices
			this.vertices.put(verticeId, new LinkedHashMap<Integer, Arco<T>>());
		}
	}

	/*
	 * METODO borrarVertice(int verticeId)
	 * Complejidad: O(log v) + O(v * v) => 
	 * 
	 * O(log v) + 2 * O(v) * 2 * O(va) donde v es la cantidad
	 *  de vértices del grafo y va es la cantidad de vertices adyacentes de un vertice.
	 * Si consideramos la obtención de la colección y su correspondiente iterator como log v
	 * 
	 * En el peor de los casos, recorrerá los todos los vertices para verificar si existe algún 
	 * arco de entrada y, en caso afirmativo, lo eliminará. Luego borrará, a través del método remove, 
	 * la clave del vértice con sus correspondientes valores (arcos de salida).
	 */
	@Override
	public void borrarVertice(int verticeId) {
		
		Iterator<Integer> it = this.vertices.keySet().iterator(); //O(log v)
		
		while(it.hasNext()) { 
			Integer auxId = it.next();
			if(this.existeArco(auxId, verticeId)) { // O(va)
				this.borrarArco(auxId, verticeId); // O(va)
			}
		}
		
		this.vertices.remove(verticeId); //O(v) busca clave y borra clave-valor: elimina vertice y arcos de salida
	}

	/*
	 * Complejidad: O(v) + O(va) donde v es la cantidad de vertices en el grafo y 
	 * va es la cantidad de vertices adyacentes al vertice de origen
	 * Se consideran los metodos get y put como constant-time performance.
	 * En el peor de los casos, recorre todos los vertices adyacentes al vertice origen
	 * y luego recorre todos los vertices del grafo para verificar y controlar que no se
	 * sobreescriban arcos
	 * 
	 */
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1);
		
		if(tmp != null) {
			if(!tmp.containsKey(verticeId2) && this.vertices.containsKey(verticeId2)) { //O(va) + O(v)
				Arco<T> nuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
				tmp.put(verticeId2, nuevo);
				this.vertices.put(verticeId1, tmp);
			}
		}
	}

	/*
	 * Complejidad: O(va) donde va es la cantidad vertices adyacentes a vertice de origen.
	 * 
	 * En el peor de los casos recorre todos los vertices adyacentes al vertice de origen
	 * para encontrar la clave (verticeId2) que contiene el arco a ser eliminado
	 * Se considera al metodo LinkedHasMap.get(key) como constant-time performance
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1); //O(1)
		
		if(tmp != null) {
			tmp.remove(verticeId2); // O(va) => va = cantidad vertices adyacentes a verticeId1
		}
	}

	/*
	 * Complejidad: O(v) donde v es la cantidad de vertices del grafo
	 * En el peor de los casos, el metodo containskey recorre todos los vertices del grafo
	 * para evaluar si existe.
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		
		return this.vertices.containsKey(verticeId);
	}

	/*
	 * Complejidad: O(va) donde va es la cantidad de vértices adyacentes al vertice origen (verticeId1)
	 * En el peor de los casos, el metodo containskey recorre todos los vertices adyacentes a verticeId
	 * para evaluar si existe.
	 * El método get de LinkedHashMap es "constant-time performance"
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		boolean existe = false;
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId1); //Obtiene datos de vértice de origen
		
		if(tmp != null) {
			existe = tmp.containsKey(verticeId2);
		}
		
		return existe;
	}

	/*
	 * Complejidad: O(1) ya que get es "constant-time performance"
	 * En el peor de los casos ejecutará 2 veces el get(key) de LinkedHashMap,
	 * es decir 2 * O(1) 
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

	/*
	 * Complejidad: O(1) si consideramos constante la obtención de size
	 */
	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	/*
	 * Complejidad: O(v) + O(log v) donde v es la cantidad de vértices del grafo.
	 * 
	 * En el peor de los casos se crea una coleccion de tamaño v
	 * y se la recorre completa para obtener el size de cada elemento.
	 * Si consideramos O(log n) la obtención de una colección y su correspondiente iterator.
	 * Además, si consideramos constantes la obtención del size, 
	 * la complejidad será en función a la cantidad de vertices, por ello es O(v) + O(log v)
	 */
	@Override
	public int cantidadArcos() {
		int size = 0;
		
		//Iterador de mapas cuya clave es verticeDestino y cuyo valor es el Arco
		Iterator<Map<Integer, Arco<T>>> itDestinos = this.vertices.values().iterator(); // O(log v)
		
		while(itDestinos.hasNext()) {
			size += itDestinos.next().size();
		}
		
		return size;
	}

	/*
	 * Complejidad: O(log v) donde v es la cantidad de vertices del grafo.
	 * 
	 * La complejidad es función de la eficiencia de la estructura para obtener 
	 * la colección de los vertices del grafo a través del keySet y el correspondiente iterator 
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	/*
	 * Complejidad: O(log va) donde va es la cantidad de vertices adyacentes a verticeId.
	 * 
	 * El método get es explícitamente constant-time performance.
	 * La complejidad es función de la eficiencia de la estructura para obtener 
	 * la colección de los vertices adyacentes a través del keySet y el correspondiente iterator 
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId);
		Iterator<Integer> it = null;
		
		if(tmp != null) {
			it = tmp.keySet().iterator();
		}
		
		return it;
	}
	
	/*
	 * Complejidad: O(v2) donde v es la cantidad de vértices del grafo.
	 * 
	 * => O(log v) + O(v) * (O(v) + O(log v)) 
	 * 
	 * => O(log v) + O(v) * (O(va) + O(log va)) donde v es la cantidad de vértices del grafo
	 * y va es la cantidad de vertices adyacentes de cada vertice.
	 * Si consideramos que la cantidad de vértices adyacentes puede ser igual a la cantidad de vertices 
	 * del grafo. Además, si consideramos O(log v) la obtención de la coleccion de vertices adyacentes 
	 * a traves de values() y su correspondiente iterator y la obtención de sus correspondientes arcos.
	 *  
	 * En el peor de los casos se recorren todos los vertices para agregar cada elemento (arco)
	 * de la coleccion tamaño va (vertices adyacentes).
	 * 
	 * La complejidad será en función a la cantidad de vertices adyacentes que tenga cada vertice, 
	 * por ello es O(v) * O(v). Es decir, O(v2)
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Collection<Arco<T>> arcos = new LinkedList<>();
		
		Iterator<Map<Integer, Arco<T>>> it = this.vertices.values().iterator(); // O(log v)
		while(it.hasNext()) {
			arcos.addAll(it.next().values()); // O(va) + O(log va)
		}
		
		return arcos.iterator();
	}

	/*
	 * Complejidad: O(log va) donde va es la cantidad de vértices adyacentes del verticeId
	 * 
	 * En el peor de los casos se crea una coleccion de vertices adyacentes 
	 * y su correspondiente iterator.
	 * 
	 * Si consideramos O(log n) la obtención de esa colección de n elementos y
	 * la su correspondiente iterator, la complejidad será en función
	 *  a la cantidad vértices adyacentes del verticeId, por ello es O(log va)
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		Map<Integer, Arco<T>> tmp = this.vertices.get(verticeId);
		Iterator<Arco<T>> it = null;
		
		if(tmp != null) {
			it = tmp.values().iterator(); //O(log va) se obtiene iterador de la coleccion va
		}
		
		return it;
	}
}
