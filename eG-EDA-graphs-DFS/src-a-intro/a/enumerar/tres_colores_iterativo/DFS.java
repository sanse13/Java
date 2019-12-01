package a.enumerar.tres_colores_iterativo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

import base.GenericIntGraph;
import base.LIFOQueue;
import base.Queue;
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
	private static final int	WHITE	= 0;
	private static final int	GRAY	= 1;
	private static final int	BLACK	= 2;

	public final GenericIntGraph<E>	g;
	private int[]					marked;

	private BufferedWriter printer;

	public DFS(GenericIntGraph<E> g, BufferedWriter printer) {
		this.g = g;
		this.printer = printer;
		marked = new int[g.size()];
	}

	/**
	 * Escribe en "printer" los nodos del grafo alcanzables desde "startNode", segun un
	 * recorrido en profundidad.
	 *
	 * Los nodos se consideran coloreados: WHITE/GRAY/BLACK
	 *
	 * </br>
	 * nodos blancos: son aun no descubiertos nodos negros: nodos descubiertos con TODAS sus
	 * aristas procesadas nodos grises: nodos descubiertos con alguna arista pendiente de ser
	 * procesada.
	 *
	 * </br>
	 * La implementacion es iterativa, usando una cola LIFO (pila), con iteradores sobre
	 * listas de adyacencia en vez de nodos. De esa forma el comportamiento es el mismo que su
	 * version recursiva.
	 */
	public void from(int from) throws IOException {

		Queue<Info<E>> Q = new LIFOQueue<>();
		/*
		 * Cola con un iterador por cada nodo gris: son los que tienen aristas pendientes de
		 * procesar.
		 */

		Iterator<E> adjacencyListIterator = g.edgesIterator(from);
		Q.add(new Info<>(from, adjacencyListIterator));

		marked[from] = GRAY;

		while (!Q.isEmpty()) {
			Info<E> item = Q.peek();

			int orig = item.from;
			assert marked[orig] == GRAY;

			BufferedWriters.println(printer, from);

			/*
			 * Procesar siguiente arista (orig, x) del grafo
			 */
			Iterator<E> origAdjacencyList = item.adjacencyListIterator;
			if (origAdjacencyList.hasNext()) {
				/*
				 * Procesar siguiente arista
				 */
				E edge = origAdjacencyList.next();
				int dest = g.destinationOf(edge);
				/*
				 * Los nodos de la arista en curso son: (orig, dest)
				 */

				if (marked[dest] == WHITE) {
					/*
					 * El nodo "dest" es descubierto POR PRIMERA VEZ
					 */
					Q.add(new Info<>(dest, g.edgesOf(dest).iterator()));
					marked[dest] = GRAY;
				} else if (marked[dest] == GRAY) {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				} else if (marked[dest] == BLACK) {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				}
			} else {
				assert !origAdjacencyList.hasNext();
				marked[orig] = BLACK;
				Q.rmv();
			}
		}
	}

	private static class Info<E> {
		int			from;
		Iterator<E>	adjacencyListIterator;

		private Info(int from, Iterator<E> adjacencyListIterator) {
			this.from = from;
			this.adjacencyListIterator = adjacencyListIterator;
		}

		@Override
		public String toString() {
			return "[from=" + from + "]";
		}
	}
}
