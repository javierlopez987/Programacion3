package ProgramacionIII.tp3Entregable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	
	private static int contador;
	
	public static void main(String[] args) {
		//Colección de tareas
		Set<Tarea> tareas = new HashSet<>();
		
		//Creo mapa para de tareas
		Map<Integer, Tarea> mapeo = new HashMap<>();
		
		// Creo un grafo dirigido donde las etiquetas de los arcos son valores Integer
		GrafoDirigido<Integer> grafito = new GrafoDirigido<>();
		
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
		//grafito.agregarArco(12, 0, 15); // grafo cíclico
		
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
		Tarea inicio = mapeo.get(0);
		Map<Integer, Integer> sec = getSecuencia(mapeo, grafito, inicio);
		System.out.println(sec.keySet());
		System.out.println(contador);
	}
	/*
	 * Complejidad: O(n) donde n es la cantidad de elementos del Map
	 * En el peor de los casos tendrá que recorrer todos los elementos
	 */
	public static int indexOfTarea(Map<Integer, Tarea> mapeo, Tarea tarea) {
		int index = -1;
		
		Iterator<Integer> itTareasId = mapeo.keySet().iterator();
		Iterator<Tarea> itTareas = mapeo.values().iterator();
		
		while(itTareas.hasNext() && itTareasId.hasNext()) {
			Integer aux = itTareasId.next();
			Tarea tmp = itTareas.next();
			if(tmp.equals(tarea)) {
				index = aux;
			}
		}
		
		return index;
	}
	
	public static Map<Integer, Integer> getSecuencia(Map<Integer, Tarea> mapa, Grafo<Integer> grafo, Tarea tarea) {
		Map<Integer, Integer> result = new HashMap<>();
		Map<Integer, Integer> tmp = new HashMap<>();
		
		int verticeId = indexOfTarea(mapa, tarea);
		int max = 0;
		int sumaElementos = 0;
		
		Iterator<Integer> itAdyacentesId = grafo.obtenerAdyacentes(verticeId);
		
		while(itAdyacentesId.hasNext()) {
			Integer adyacenteId = itAdyacentesId.next();
			
			tmp = getSecuencia(mapa, grafo, mapa.get(adyacenteId));
			
			sumaElementos = tmp.get(adyacenteId);
			sumaElementos += grafo.obtenerArco(verticeId, adyacenteId).getEtiqueta();
			
			if(sumaElementos > max) {
				max = sumaElementos;
				result = tmp;
			}
		}
		
		result.put(tarea.getId(), tarea.getDuracion() + max);
		contador++;
		
		return result;
	}
	
}
