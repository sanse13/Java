package a.enumerar.recursivo;

import java.io.BufferedWriter;
import java.io.IOException;

import base.GenericIntGraph;
import util.BufferedWriters;

/**
 * Una clase de objetos para imprimir los nodos de un grafo que son alcanzables desde un
 * nodo x del grafo.
 *
 * </br>
 * Ejemplo de uso
 *
 * <pre>
 * public static void procesar(IntGraph g, BufferedWriter printer, int x) {
 * 	DFS<Integer> dfs = new DFS<>(g, printer);
 * 	dfs.from(x);
 * }
 * </pre>
 *
 * Las aristas del grafo pueden ser de cualquier tipo E.
 */
public class DFS<E> {

	public final GenericIntGraph<E>	g;
	private boolean[]				marked;

	private BufferedWriter printer;

	public DFS(GenericIntGraph<E> g, BufferedWriter printer) {
		this.g = g;
		this.printer = printer;
		marked = new boolean[g.size()];
	}

	/**
	 * Escribe en "printer" los nodos del grafo alcanzables desde "startNode", segun un
	 * recorrido en profundidad.
	 */
	public void from(int startNode) throws IOException {
		assert !marked[startNode];
		marked[startNode] = true;

		BufferedWriters.println(printer, startNode);

		/*
		 * Procesar cada arista (orig, x) del grafo
		 */
		Iterable<E> adjacencyList = g.edgesOf(startNode);
		for (E edge : adjacencyList) {
			int dest = g.destinationOf(edge);
			/*
			 * Los nodos de la arista en curso son: (orig, dest)
			 */

			if (!marked[dest]) {
				/*
				 * El nodo "dest" es descubierto POR PRIMERA VEZ
				 */
				from(dest);
			} else {
				/*
				 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
				 */
			}
		}
	}
}

