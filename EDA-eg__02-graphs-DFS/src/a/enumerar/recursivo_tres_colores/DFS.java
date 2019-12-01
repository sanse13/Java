package a.enumerar.recursivo_tres_colores;

import java.io.BufferedWriter;
import java.io.IOException;

import hr.base.BufferedWriters;
import hr.base.GenericIntGraph;

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

	public void from(int startNode) throws IOException {

		assert marked[startNode] == WHITE;

		BufferedWriters.println(printer, startNode);

		marked[startNode] = GRAY;
		/*
		 * Aristas que salen/tocan el nodo "orig"
		 */
		Iterable<E> adjacencyList = g.edgesOf(startNode);
		/*
		 * Procesar cada una de esas aristas
		 */
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
