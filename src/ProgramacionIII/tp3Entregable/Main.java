package ProgramacionIII.tp3Entregable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {
	private static int contador;
	
	public static void main(String[] args) {
		/*Colección de tareas
		 * No se permite repetición de tareas con mismo id
		 * Se comparan por id en metodo equals de Tarea
		 */
		Set<Tarea> tareas = new HashSet<>();
		
		// Creo un grafo dirigido donde las etiquetas de los arcos son valores Integer
		GrafoDirigido<Integer> grafito = new GrafoDirigido<>();
		
		//Creo mapa que vinculará cada vertice del grafo con una tarea
		Map<Integer, Tarea> mapeo = new HashMap<>();
		
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
		System.out.println("El método getCaminoCritico se ejecuto: " + contador + " veces");
	}
	
	/*
	 * Complejidad: General O(n2) donde n es la cantidad de vertices 
	 * En el peor de los casos ejecutará n-1 + n-2 + n-3 + n-4 + n-5 + ... + n-(n-1)
	 * veces las consultas a la estructura hashmap
	 */
	public static Map<Integer, Integer> getCaminoCritico(Map<Integer, Tarea> mapa, Grafo<Integer> grafo, int verticeId) {
		Map<Integer, Integer> result = new HashMap<>();
		Map<Integer, Integer> tmp = new HashMap<>();
		
		Tarea tarea = mapa.get(verticeId);
		int max = 0;
		int sumaElementos = 0;
		int idTarea = tarea.getId();
		int costoAcumuladoTarea = 0;
		
		Iterator<Integer> itAdyacentesId = grafo.obtenerAdyacentes(verticeId);
		
		while(itAdyacentesId.hasNext()) {
			Integer adyacenteId = itAdyacentesId.next();
			
			tmp = getCaminoCritico(mapa, grafo, adyacenteId);
			
			sumaElementos = tmp.get(adyacenteId);
			sumaElementos += grafo.obtenerArco(verticeId, adyacenteId).getEtiqueta();
			
			if(sumaElementos > max) {
				max = sumaElementos;
				result = tmp;
			}
		}
		
		costoAcumuladoTarea = tarea.getDuracion() + max;
		
		result.put(idTarea, costoAcumuladoTarea);
		
		contador++;
		
		return result;
	}
	
}
