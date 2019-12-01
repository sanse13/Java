 package e.alcanzables_a_distancia;

import java.util.Arrays;

import hr.base.IntFIFOQueue;
import hr.base.IntFIFOQueues;
import hr.base.IntGraph;
import hr.base.IntSinglyLinkedListNode;

public class Student_Code {

	/**
	 * Dado un grafo "g", y un nodo "x" de "g", calcula para cada nodo "i != x", el minimo numero de aristas
	 * que hay que atravesar para ir desde "x" hasta "i", si ese minimo es <= D.
	 *
	 * @return un array A con N posiciones (N: numero de nodos de g) tal que A[x] = -1 y cada posicion A[i] (i
	 *         != x) es:
	 *
	 *         a) "-1" si no puede llegarse desde "x" hasta "i" atravesando D aristas o menos.
	 *
	 *         b) si no: un entero "v >= 0" que es el minimo numero de aristas que hay que atravesar para ir
	 *         desde "x" hasta "i" (que sera <= D)
	 *
	 *         ATENCION! La ejecucion debe terminar tan pronto como sea posible.
	 */
	public static int[] solve(IntGraph g, int x, int D) {
		int[] dist = new int[g.nodes];
		Arrays.fill(dist, -1);
		dist[x] = 0;
		if (D == 0) { return dist; }

		boolean[] marked = new boolean[g.nodes];

		IntFIFOQueue q = new IntFIFOQueue();

		IntFIFOQueues.addToEnd(q, x);
		marked[x] = true;
		dist[x] = 0;

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

					int tmp = dist[orig] + 1;
					if (tmp > D) {
						break;
					}
					dist[dest] = tmp;
				} else {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				}
				currentAdjNode = currentAdjNode.next;
			}
		}
		return dist;
	}
}
