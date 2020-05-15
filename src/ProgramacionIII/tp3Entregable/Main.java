package ProgramacionIII.tp3Entregable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {
	/*
	 * Intenté con un Mapa de Mapas para registrar los costos acumulados por cada vertice 
	 * y el correspondiente camino de indices de vertices
	 * SIN EXITO - el indice del vertice lo guarda pero luego va actualizando la secuencia
	 * de un indice ya cargado a medida que agrega el resto de los indices de vertices
	 * Al final no obtiene una foto del indice y su secuencia segun el momento en el que paso
	 * por el metodo sino un mapa que se va actulizando
	 */
	private static Map<Integer, Map<Integer, Integer>> costos;
	
	private static int contadorVertices;
	private static int contadorArcos;
	
	public static void main(String[] args) {
		costos = new HashMap<Integer, Map<Integer,Integer>>();
		
		/*Colección de tareas
		 * No se permite repetición de tareas con mismo id
		 * Se comparan por id en metodo equals de Tarea
		 */
		Set<Tarea> tareas = new HashSet<>();
		
		// Creo un grafo dirigido donde las etiquetas de los arcos son valores Integer
		GrafoDirigido<Integer> grafito = new GrafoDirigido<>();
		
		//Creo mapa que vinculará cada vertice del grafo con una tarea
		Map<Integer, Tarea> mapeo = new HashMap<>();
		
		testGrafo(grafito);
		procesarTest(tareas, mapeo, grafito);
	}
	
	public static void procesarTest(Set<Tarea> tareas, Map<Integer, Tarea> mapeo, Grafo<Integer> grafito) {
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
		//grafito.agregarArco(3, 5, 2);
		//grafito.agregarArco(0, 3, 6);
		
		//Obtengo cantidad de vértices y de arcos
		grafito.cantidadVertices();
		grafito.cantidadArcos();
		
		//Imprimo los arcos del grafo
		/*
		Iterator<Arco<Integer>> itArcos = grafito.obtenerArcos();
		while(itArcos.hasNext()) {
			System.out.println(itArcos.next());
		}
		*/
		
		// Si existe, obtengo el arco entre 1 y 2, y le pido la etiqueta
		if(grafito.existeArco(1, 2)) {
			Integer etiqueta = grafito.obtenerArco(1, 2).getEtiqueta();
			System.out.println(etiqueta); // Debería imprimir 3
		}
		
		//Secuecia de ejecucion critica
		Map<Integer, Integer> sec = getCaminoCritico(mapeo, grafito, 0);
		System.out.println(sec.keySet());
		System.out.println("Complejidad método getCaminoCritico:  O(v) + O (a) => v = " + contadorVertices + "; a = " + contadorArcos);
		
	}
	
	/* Solución anterior era de complejidad O(n!) donde n es la cantidad de vertices 
	 * En el peor de los casos ejecutaba n-1 * n-2 * n-3 * n-4 * n-5 * ... * n-(n-1)
	 * veces las consultas a la estructura hashmap
	 * 
	 * Esta solución tiene un complejidad O(v) + O(a)
	 * donde v es la cantidad de vértices y a es la cantidad de arcos
	 */
	public static Map<Integer, Integer> getCaminoCritico(Map<Integer, Tarea> mapa, Grafo<Integer> grafo, int verticeId) {
		Map<Integer, Integer> result = new LinkedHashMap<>();
		Map<Integer, Integer> tmp;
		
		Tarea tarea = mapa.get(verticeId);
		int max = 0;
		int sumaElementos = 0;
		int idTarea = tarea.getId();
		int costoAcumuladoTarea = 0;
		
		Iterator<Integer> itAdyacentesId = grafo.obtenerAdyacentes(verticeId);
		
		while(itAdyacentesId.hasNext()) {
			contadorArcos++;
			
			Integer adyacenteId = itAdyacentesId.next();
			
			if(!costos.containsKey(adyacenteId)) {
				tmp = getCaminoCritico(mapa, grafo, adyacenteId);
			} else {
				tmp = costos.get(adyacenteId);
			}
			
			sumaElementos = tmp.get(adyacenteId);
			sumaElementos += grafo.obtenerArco(verticeId, adyacenteId).getEtiqueta();
			
			if(sumaElementos > max) {
				max = sumaElementos;
				result.clear();
				result.putAll(tmp);
			}
		}
		
		costoAcumuladoTarea = tarea.getDuracion() + max;
		
		result.put(idTarea, costoAcumuladoTarea);
		
		costos.put(idTarea, result);
		
		contadorVertices++;
		
		return result;
	}

	
	/*
	 * Testea los metodos del grafo
	 */
	public static void testGrafo(Grafo<Integer> grafo) {
		final int MAX = 13;
		
		//Se carga el grafo
		for(int i =0; i < MAX; i++) {
			grafo.agregarVertice(i);
		}
		
		for(int i = 0; i < MAX; i++) {
			if(grafo.contieneVertice(i)) {
				grafo.agregarArco(i, i + 2, 2*i);
			}
		}
		
		if(grafo.contieneVertice(MAX/2)) {
			grafo.borrarVertice(MAX/2);
		}
		
		if(grafo.contieneVertice(2)) {
			grafo.borrarVertice(2);
		}
		
		if(grafo.existeArco(1, 3)) {
			grafo.borrarArco(1, 3);
		}
		
		grafo.cantidadArcos();
		grafo.cantidadVertices();
		
	}
}
