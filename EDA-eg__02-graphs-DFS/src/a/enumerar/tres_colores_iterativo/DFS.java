package a.enumerar.tres_colores_iterativo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

import hr.base.BufferedWriters;
import hr.base.GenericIntGraph;
import hr.base.LIFOQueue;
import hr.base.Queue;

public class DFS<E> {
	private static final int	WHITE	= 0;
	private static final int	GRAY	= 1;
	private static final int	BLACK	= 2;

	GenericIntGraph<E> g;

	BufferedWriter printer;

	int[] marked;

	public DFS(GenericIntGraph<E> g, BufferedWriter printer) {
		this.g = g;
		this.printer = printer;
		marked = new int[g.size()];
	}

	public void from(int from) throws IOException {

		Queue<Info<E>> Q = new LIFOQueue<>();
		/*
		 * Cola con los nodos marcados cuyas aristas no es seguro que hayan sido procesadas en su totalidad.
		 */

		Iterator<E> adjacencyListIterator = g.edgesIterator(from);
		Q.add(new Info<>(from, adjacencyListIterator));
		marked[from] = GRAY;

		BufferedWriters.println(printer, from);

		while (!Q.isEmpty()) {
			Info<E> item = Q.peek();

			int orig = item.from;
			assert marked[orig] == GRAY;

			/*
			 * El nodo orig ha sido descubierto pero sus aristas NO han sido procesadas. Si el grafo es
			 * dirigido, no ha sido procesada ninguna; y si no, no necesariamente todas lo han sido.
			 */

			/*
			 * Aristas que salen/tocan el nodo "orig"
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

					BufferedWriters.println(printer, dest);
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
