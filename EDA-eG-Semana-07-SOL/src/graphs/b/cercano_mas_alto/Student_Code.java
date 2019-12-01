package graphs.b.cercano_mas_alto;

import base.IntFIFOQueue;
import base.IntFIFOQueues;
import base.IntGraph;
import base.IntSinglyLinkedListNode;

public class Student_Code {

	/**
	 * Dado un grafo no dirigido G, un nodo X de G, y un array H que indica la
	 * altura de cada nodo de G, encuentra el nodo de mayor altura de G. Si hay
	 * varios esa altura, el mas cercano de X (menos aristas a atravesar desde X).
	 * 
	 * @param x
	 *            indice de uno de los nodos del grafo (0 <= x < g.nodes)
	 * @param H
	 *            array con g.nodes posiciones, una por cada nodo de g: H[i] es la
	 *            altura del nodo i.
	 * @param g
	 *            el grafo referido
	 * 
	 * @return el nodo de mayor altura de G
	 */
	public static int solve(int x, int[] H, IntGraph g) {
		int currentCandidate = -1;

		boolean[] marked = new boolean[g.nodes];

		IntFIFOQueue q = new IntFIFOQueue();

		IntFIFOQueues.addToEnd(q, x);
		marked[x] = true;

		while (q.head != null) {
			int orig = IntFIFOQueues.rmvFirst(q);

			if (currentCandidate == -1 || H[currentCandidate] < H[orig]) {
				currentCandidate = orig;
			}

			/*
			 * Aristas que salen/tocan el nodo "orig"
			 */
			IntSinglyLinkedListNode currentAdjNode = g.adjacencyLists[orig];

			/*
			 * Procesar cada una de esas aristas
			 */
			while (currentAdjNode != null) {
				int dest = currentAdjNode.data;

				/*
				 * Los nodos de la arista en curso son: (orig, dest)
				 */

				if (!marked[dest]) {
					/*
					 * El nodo "dest" es descubierto POR PRIMERA VEZ
					 */
					IntFIFOQueues.addToEnd(q, dest);
					marked[dest] = true;
				} else {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				}
				currentAdjNode = currentAdjNode.next;
			}
		}
		return currentCandidate;
	}
}
