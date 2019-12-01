package d.camino_hasta_destino;

import java.util.Arrays;

import hr.base.IntFIFOQueue;
import hr.base.IntFIFOQueues;
import hr.base.IntGraph;
import hr.base.IntSinglyLinkedListNode;

public class Student_Code {

	/**
	 * Busca en el grafo "g" un camino desde el nodo "x" hasta el nodo "y" de longitud minima (cualquier otro
	 * camino desde el nodo "x" hasta el nodo "y" esta compuesto por al menos el mismo numero de aristas.)
	 *
	 * Caso de haber varios caminos de longitud minima, devolvera el correspondiente a un recorrido en anchura
	 * del grafo.
	 *
	 * @return null si no se puede llegar al nodo "y" desde el nodo "x"; si no, una lista con los nodos de un
	 *         camino con las caracteristicas indicadas y tal que:
	 *
	 *         a) el primer entero de la lista es "x"
	 *
	 *         b) el ultimo entero de la lista es "y".
	 */
	public static IntSinglyLinkedListNode solve(IntGraph g, int x, int y) {
		int[] A = new int[g.nodes];
		Arrays.fill(A, -1);
		/*
		 * Para todo 0 <= i < N (=g.nodes):
		 *
		 * a) si A[i] = -1 si no se ha encontrado un camino desde "x" hasta "i"
		 *
		 * b) si se ha encontrado un camino desde "x" hasta "i", A[i] >= 0 y la ultima arista de ese camino es
		 * la arista (A[i], i).
		 *
		 */

		boolean[] marked = new boolean[g.nodes];

		IntFIFOQueue q = new IntFIFOQueue();

		IntFIFOQueues.addToEnd(q, x);
		marked[x] = true;

		while (q.head != null) {
			int orig = IntFIFOQueues.rmvFirst(q);

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

					A[dest] = orig;

					if (dest == y) {
						break;
					}
				} else {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				}
				currentAdjNode = currentAdjNode.next;
			}
		}
		IntSinglyLinkedListNode path = null;
		if (A[y] != -1) {
			int current = y;
			while (current != -1) {
				IntSinglyLinkedListNode node = new IntSinglyLinkedListNode(current);
				node.next = path;
				path = node;
				current = A[current];
			}
		}
		System.out.println();
		return path;
	}
}
	
