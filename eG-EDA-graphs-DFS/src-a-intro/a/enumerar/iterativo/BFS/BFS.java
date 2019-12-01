package a.enumerar.iterativo.BFS;

import java.io.BufferedWriter;
import java.io.IOException;

import base.FIFOQueue;
import base.GenericIntGraph;
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
 * 	BFS<Integer> bfs = new BFS<>(g, printer);
 * 	bfs.from(x);
 * }
 * </pre>
 *
 * Las aristas del grafo pueden ser de cualquier tipo E.
 */
public class BFS<E> {

	public final GenericIntGraph<E>	g;
	private boolean[]				marked;

	private BufferedWriter printer;

	public BFS(GenericIntGraph<E> g, BufferedWriter printer) {
		this.g = g;
		this.printer = printer;
		marked = new boolean[g.size()];
	}

	/**
	 * Escribe en "printer" los nodos del grafo alcanzables desde "startNode", segun un
	 * recorrido en anchura.
	 */
	public void from(int startNode) throws IOException {

		Queue<Integer> Q = new FIFOQueue<>();
		/*
		 * Cola con los nodos marcados cuyas aristas no es seguro que hayan sido procesadas en su
		 * totalidad.
		 */

		Q.add(startNode);
		marked[startNode] = true;

		while (!Q.isEmpty()) {
			int orig = Q.rmv();
			assert marked[orig];
			/*
			 * El nodo orig ha sido descubierto pero sus aristas NO han sido procesadas. Si el grafo
			 * es dirigido, no ha sido procesada ninguna; y si no, no necesariamente todas lo han
			 * sido.
			 */

			BufferedWriters.println(printer, orig);

			/*
			 * Procesar cada arista (orig, x) del grafo
			 */
			Iterable<E> adjacencyList = g.edgesOf(orig);
			for (E edge : adjacencyList) {
				int dest = g.destinationOf(edge);
				/*
				 * Los nodos de la arista en curso son: (orig, dest)
				 */

				if (!marked[dest]) {
					/*
					 * El nodo "dest" es descubierto POR PRIMERA VEZ
					 */
					Q.add(dest);
					marked[dest] = true;
				} else {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				}
			}
		}
	}
}
