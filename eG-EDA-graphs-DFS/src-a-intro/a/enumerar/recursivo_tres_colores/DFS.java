package a.enumerar.recursivo_tres_colores;

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
	 * procesada
	 */
	public void from(int startNode) throws IOException {

		assert marked[startNode] == WHITE;
		marked[startNode] = GRAY;

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

			if (marked[dest] == WHITE) {
				/*
				 * El nodo "dest" es descubierto POR PRIMERA VEZ
				 */
				from(dest);
			} else if (marked[dest] == GRAY) {
				/*
				 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
				 */
			} else if (marked[dest] == BLACK) {
				/*
				 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
				 */
			}
		}
		marked[startNode] = BLACK;
	}
}
