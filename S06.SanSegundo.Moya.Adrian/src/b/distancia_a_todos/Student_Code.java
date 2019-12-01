package b.distancia_a_todos;

import java.util.Arrays;

import hr.base.IntFIFOQueue;
import hr.base.IntFIFOQueues;
import hr.base.IntGraph;
import hr.base.IntSinglyLinkedListNode;

public class Student_Code {

	/**
	 * Dado un grafo G, y un nodo X de G, calcula para cada nodo Y de G, el minimo numero de aristas que hay
	 * que atravesar para ir desde X hasta Y.
	 *
	 * @return un array A con N posiciones (N: numero de nodos de "g) tal que:
	 *
	 *         a) A[X] = 0
	 *
	 *         b) cada posicion A[Y] (Y != X) es:
	 *
	 *         * "-1" si no puede llegarse desde X hasta Y
	 *
	 *         * si no: el minimo numero de aristas a atravesar para ir desde X hasta Y.
	 */
	public static int[] solve(IntGraph g, int x) {
		int[] dist = new int[g.nodes];
		Arrays.fill(dist, -1);
		
		boolean[] marked = new boolean[g.nodes];
		
		marked[x] = true;
		dist[x] = 0;
		
		IntFIFOQueue q = new IntFIFOQueue();
		IntFIFOQueues.addToEnd(q, x);
		
		while (q.head != null) {
			int origen = IntFIFOQueues.rmvFirst(q);
			
			IntSinglyLinkedListNode head = g.adjacencyLists[origen];
			while (head != null) {
				int dest = head.data;
				
				if (!marked[dest]) {
					IntFIFOQueues.addToEnd(q, dest);
					marked[dest] = true;
					
					dist[dest] = dist[origen] +1;
				}
				head = head.next;
			}
		}
		return dist;
	}
}
