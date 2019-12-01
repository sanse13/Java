package a.enumerar.iterativo.BFS;

import java.io.BufferedWriter;
import java.io.IOException;

import hr.base.BufferedWriters;
import hr.base.FIFOQueue;
import hr.base.GenericIntGraph;
import hr.base.Queue;

public class BFS<E> {

	GenericIntGraph<E> g;

	BufferedWriter printer;

	boolean[] marked;

	public BFS(GenericIntGraph<E> g, BufferedWriter printer) {
		this.g = g;
		this.printer = printer;
		marked = new boolean[g.size()];
	}

	/**
	 * Escribe en "printer" los nodos del grafo alcanzables desde "startNode", segun un
	 * recorrido DFS.
	 */
	public void from(int startNode) throws IOException {

		Queue<Integer> Q = new FIFOQueue<>();
		/*
		 * Cola con los nodos marcados cuyas aristas no es seguro que hayan sido procesadas en su totalidad.
		 */

		Q.add(startNode);
		marked[startNode] = true;

		while (!Q.isEmpty()) {
			int orig = Q.rmv();
			assert marked[orig];
			/*
			 * El nodo orig ha sido descubierto pero sus aristas NO han sido procesadas. Si el grafo es
			 * dirigido, no ha sido procesada ninguna; y si no, no necesariamente todas lo han sido.
			 */

			BufferedWriters.println(printer, orig);

			/*
			 * Aristas que salen/tocan el nodo "orig"
			 */
			Iterable<E> adjacencyList = g.edgesOf(orig);
			/*
			 * Procesar cada una de esas aristas
			 */
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
