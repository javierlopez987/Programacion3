package ProgramacionIII.tp3Entregable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	private static Map<Integer, Map<Integer, Integer>> costos;
	private static Map<Integer, Map<Integer, Integer>> costosTest;
	
	final static int INICIO = 0;
	private static int contadorEjecucionPorVertice;
	private static int contadorEjecucionPorArco;
	
	public static void main(String[] args) {
		costos = new HashMap<Integer, Map<Integer,Integer>>();
		costosTest = new HashMap<Integer, Map<Integer,Integer>>();
		
		
		/*Colección de tareas
		 * No se permite repetición de tareas con mismo id
		 * Se comparan por id en metodo equals de Tarea
		 */
		Tarea inicialTarea = new Tarea(INICIO, INICIO);
		Set<Tarea> tareas = new HashSet<>();
		Set<Tarea> tareasTest = new HashSet<>();
		Set<Tarea> tareasTest2 = new HashSet<>();
		tareas.add(inicialTarea);
		tareasTest.add(inicialTarea);
		tareasTest2.add(inicialTarea);
		
		// Creo un grafo dirigido donde las etiquetas de los arcos son valores Integer
		GrafoDirigido<Integer> grafito = new GrafoDirigido<>();
		GrafoDirigido<Integer> grafoTest = new GrafoDirigido<>();
		GrafoDirigido<Integer> grafoTest2 = new GrafoDirigido<>();
		
		//Creo mapa que vinculará cada vertice del grafo con una tarea
		Map<Integer, Tarea> mapeo = new HashMap<>();
		Map<Integer, Tarea> mapeoTest = new HashMap<>();
		Map<Integer, Tarea> mapeoTest2 = new HashMap<>();
		
		//cargarDatosTestControlado(tareasTest, mapeoTest, grafoTest);
		
		//procesarTestMetodosGrafo(grafoTest);
		
		//cargarDatosTestControlado(tareasTest2, mapeoTest2, grafoTest2);
		
		//procesarCaminoCritico(mapeoTest2, grafoTest2, tareasTest2, inicialTarea, costosTest);
		
		cargarDatosEntregable(tareas, mapeo, grafito);
		
		procesarCaminoCritico(mapeo, grafito, tareas, inicialTarea, costos);
		
	}
	
	public static void cargarDatosEntregable(Set<Tarea> tareas, Map<Integer, Tarea> mapeo, Grafo<Integer> grafito) {
		//Horas de tareas
		int[] horasTareas = {0, 4, 18, 4, 13, 22 , 18, 12, 3, 2, 3, 1, 5};
		
		//Se carga la colección con tareas
		for(int i =0; i < horasTareas.length; i++) {
			tareas.add(new Tarea(i, horasTareas[i]));
		}
		
		//Agrego tareas al mapa y vertices al grafo
		Iterator<Tarea> it = tareas.iterator();
		while(it.hasNext()) {
			Tarea tmpTarea = it.next();
			mapeo.put(tmpTarea.getId(), tmpTarea);
			grafito.agregarVertice(tmpTarea.getId());
		}
		
		// Genero los arcos entre vértices
		grafito.agregarArco(0, 1, 0);
		grafito.agregarArco(0, 2, 0);
		grafito.agregarArco(1, 3, 3);
		grafito.agregarArco(2, 5, 1);
		grafito.agregarArco(2, 7, 18);
		grafito.agregarArco(3, 4, 5);
		grafito.agregarArco(3, 6, 8);
		grafito.agregarArco(4, 11, 3);
		grafito.agregarArco(5, 6, 2);
		grafito.agregarArco(6, 12, 2);
		grafito.agregarArco(6, 10, 6);
		grafito.agregarArco(7, 8, 7);
		grafito.agregarArco(8, 9, 4);
		grafito.agregarArco(9, 10, 1);
		grafito.agregarArco(11, 12, 9);
		
	}
	
	/*
	 * Procedimiento que muestra el algoritmo de caminoCritico
	 */
	public static void procesarCaminoCritico(Map<Integer, Tarea> mapa, Grafo<Integer> grafo, Set<Tarea> tareas, Tarea inicial, Map<Integer, Map<Integer, Integer>> costosTable) {
		
		System.out.println("--- INICIO DE ANALISIS DE CAMINO CRITICO ---");
		
		if(tareas.contains(inicial)) {
			contadorEjecucionPorVertice = 0;
			contadorEjecucionPorArco = 0;
			
			Map<Integer, Integer> sec = getCaminoCritico(mapa, grafo, inicial.getId(), costosTable);
			
			imprimirSecuenciaOrdenAscendente(sec);
			
			System.out.println("Complejidad método getCaminoCritico:  O(v) + O (a) => v = " + 
					contadorEjecucionPorVertice + "; a = " + contadorEjecucionPorArco);
		}
		
		System.out.println("--- FIN DE ANALISIS DE CAMINO CRITICO ---");
	}
	
	/* METODO CAMINO CRITICO
	 * Complejidad: O(v) + O(a)
	 * donde v es la cantidad de vértices y a es la cantidad de 
	 * arcos (vertices adyacentes conectados con vertice de origen)
	 * 
	 * En el peor de los casos recorre todos los vértices y 
	 * sus correspondientes arcos (vertices adyacentes conectados con vertice de origen) una sola vez
	 * 
	 * Analíticamente:
	 * O(v * log a) + O(a * (c + log r + t)) 
	 * donde v es cantidad de vertices en el grafo
	 * a es la cantidad de arcos
	 * c es la cantidad de elementos en almacenados en variable costos
	 * r es la cantidad de elementos en almacenados en la variable result
	 * t es la cantidad de elementos en almacenados en la variable tmp
	 */
	private static Map<Integer, Integer> getCaminoCritico(Map<Integer, Tarea> mapa, Grafo<Integer> grafo, int verticeId, Map<Integer, Map<Integer, Integer>> costosTable) {
		Map<Integer, Integer> result = new LinkedHashMap<>();
		Map<Integer, Integer> tmp;
		
		Tarea tarea = mapa.get(verticeId);//O(1)
		int max = 0;
		int sumaElementos = 0;
		int idTarea = tarea.getId();//O(1)
		int costoAcumuladoTarea = 0;
		
		Iterator<Integer> itAdyacentesId = grafo.obtenerAdyacentes(verticeId); //O(log va)
		
		while(itAdyacentesId.hasNext()) { // O(c) + 0(1) + O(log r) + O(t)
			contadorEjecucionPorArco++;
			
			Integer adyacenteId = itAdyacentesId.next();
			
			if(!costosTable.containsKey(adyacenteId)) { //O(c) => c = cantidad de elementos en "costos"
				tmp = getCaminoCritico(mapa, grafo, adyacenteId, costosTable);
			} else {
				tmp = costosTable.get(adyacenteId); // O(1)
			}
			
			sumaElementos = tmp.get(adyacenteId); // O(1)
			sumaElementos += grafo.obtenerArco(verticeId, adyacenteId).getEtiqueta(); //O(1)
			
			if(sumaElementos > max) {
				max = sumaElementos;
				result.clear(); // O(log r) => r = cantidad de elementos en result
				result.putAll(tmp); // O(t) => t = cantidad de elementos en "tmp"
			}
		}
		
		costoAcumuladoTarea = tarea.getDuracion() + max; //O(1)
		
		result.put(idTarea, costoAcumuladoTarea); //O(1)
		
		costosTable.put(idTarea, result); //O(1)
		
		contadorEjecucionPorVertice++;
		
		return result;
	}

	/*
	 * Invierte el orden de la secuencia de claves de un mapa y la imprime
	 */
	private static void imprimirSecuenciaOrdenAscendente(Map<Integer, Integer> mapa) {
		List<Integer> tmp = new LinkedList<>();
		Iterator<Integer> it = mapa.keySet().iterator();
		while(it.hasNext()) {
			tmp.add(it.next());
		}
		Collections.reverse(tmp);
		System.out.println(tmp);
	}
	
	/*
	 * Testea los metodos del grafo
	 */
	public static void procesarTestMetodosGrafo(Grafo<Integer> grafo) {
		
		System.out.println("--- INICIO PROCEDIMIENTO TEST 1 DE GRAFO---");
		
		System.out.println("--ESTADO INICIAL--");
		printGrafo(grafo);
		
		testGrafo(grafo);
		
		System.out.println("--ESTADO LUEGO DEL TEST 1--");
		printGrafo(grafo);
		System.out.println("--- FIN PROCEDIMIENTO TEST 1 DE GRAFO---");
	}
	
	private static void testGrafo(Grafo<Integer> grafo) {
		
		grafo.borrarVertice(2); // borra vertice
		grafo.borrarVertice(20); // no hace nada porque no existe
		
		grafo.borrarArco(0, 5); // borra arco
		grafo.borrarArco(32, 6); // no hace nada porque no existe
		grafo.borrarArco(0, 13); // no hace nada porque no existe
		
		grafo.obtenerVertices(); // devuelve un iterador para recorrer los vertices
		grafo.obtenerArcos(); // devuelve un iterador para recorrer los arcos
		grafo.obtenerArcos(50); // devuelve null porque no existe el vertice
		grafo.obtenerArco(0, 1); // devuelve el Arco 0-1
		grafo.obtenerArco(100, 1); // devuelve null porque no existe el arco
		grafo.obtenerArco(0, 100); // devuelve null porque no existe el arco
		grafo.obtenerAdyacentes(200); // devuelve null porque no existe el vertice
		grafo.obtenerAdyacentes(0); // devuelve un iterador para recorrer los adyacentes
		
	}
	
	public static void printGrafo(Grafo<Integer> grafo) {
		System.out.println("Cantidad de arcos: " + grafo.cantidadArcos());
		System.out.println("Cantidad de vértices: " + grafo.cantidadVertices());
		
		System.out.println("-DETALLE DE ARCOS-");
		printArcos(grafo);
		
	}
	
	public static void cargarDatosTestControlado(Set<Tarea> tareas, Map<Integer, Tarea> mapa, Grafo<Integer> grafo) {
		//Horas de tareas
		int[] valorTareas = {0, 80, 70, 55, 24, 10, 66, 32, 22, 14, 35, 99, 7, 5, 90, 88, 63, 74};
		
		//Se carga la colección con tareas
		for(int i = 1; i < valorTareas.length; i++) {
			tareas.add(new Tarea(i, valorTareas[i]));
		}
		
		//Agrego tareas al mapa y vertices al grafo
		Iterator<Tarea> it = tareas.iterator();
		while(it.hasNext()) {
			Tarea tmpTarea = it.next();
			mapa.put(tmpTarea.getId(), tmpTarea);
			grafo.agregarVertice(tmpTarea.getId());
		}
		
		// Genero los arcos entre vértices
		grafo.agregarArco(0, 1, 0);
		grafo.agregarArco(0, 2, 0);
		grafo.agregarArco(0, 5, 0);
		grafo.agregarArco(0, 6, 0);
		grafo.agregarArco(1, 4, 0);
		grafo.agregarArco(1, 2, 0);
		grafo.agregarArco(2, 3, 0);
		grafo.agregarArco(3, 4, 0);
		grafo.agregarArco(4, 10, 0);
		grafo.agregarArco(10, 12, 0);
		grafo.agregarArco(4, 13, 0);
		grafo.agregarArco(12, 17, 0);
		grafo.agregarArco(5, 7, 0);
		grafo.agregarArco(7, 9, 0);
		grafo.agregarArco(7, 4, 0);
		grafo.agregarArco(9, 13, 0);
		grafo.agregarArco(13, 17, 0);
		grafo.agregarArco(6, 8, 0);
		grafo.agregarArco(8, 11, 0);
		grafo.agregarArco(11, 13, 0);
		grafo.agregarArco(6, 14, 0);
		grafo.agregarArco(14, 15, 0);
		grafo.agregarArco(15, 16, 0);
		grafo.agregarArco(16, 17, 0);
	}
	
	public static void printArcos(Grafo<Integer> grafo) {
		List<Arco<Integer>> lista = new LinkedList<>();
		Iterator<Arco<Integer>> it = grafo.obtenerArcos();
		
		while(it.hasNext()) {
			lista.add(it.next());
		}
		
		Collections.sort(lista, new ComparadorArcos());
		
		Iterator<Arco<Integer>> itLista = lista.iterator();
		while(itLista.hasNext()) {
			System.out.println(itLista.next());
		}
	}
}
