package b.frontera;

import base.IntFIFOQueue;
import base.IntGraph;
import base.IntSinglyLinkedListNode;
import base.IntFIFOQueues;

public class Student_Code {

	/**
	 * Dado un grafo G, un nodo X de G, y un entero D encontrar los nodos de G cuya
	 * distancia a X (es decir, el numero de aristas del camino mas corto) es igual a D.
	 *
	 * @param x
	 *            indice de uno de los nodos del grafo (0 <= x < g.nodes)
	 * @param D
	 *            la distancia referida.
	 * @param g
	 *            el grafo referido
	 *
	 * @return una lista con los nodos encontrados; null, si no hay ninguno.
	 */
	public static IntSinglyLinkedListNode solve(int x, int d, IntGraph g) {

		boolean[] marked = new boolean[g.nodes];
		IntFIFOQueue q = new IntFIFOQueue();
		IntFIFOQueue res = new IntFIFOQueue();
		
		IntFIFOQueues.addToEnd(q, x);
		marked[x] = true;
		int[] distancia = new int[g.nodes];
		distancia[x] = 0;
		
		while (q.head != null) {
			int elMasViejo = IntFIFOQueues.rmvFirst(q);
			
			IntSinglyLinkedListNode head = g.adjacencyLists[elMasViejo];
			while (head != null) {
				int dest = head.data;
				
				if (!marked[dest]) {
					IntFIFOQueues.addToEnd(q, dest);
					marked[dest] = true;
					distancia[dest] = distancia[elMasViejo]+1;
					if (distancia[dest] == d)
						IntFIFOQueues.addToEnd(res, dest);
				}
				head = head.next;
			}
		}
		invertir(res);
		return res.head;
	}
	
	public static void invertir(IntFIFOQueue q) {
		IntSinglyLinkedListNode aux, current, prev;
		current = q.head.next;
		prev = q.head;
		aux = current;
		prev.next = null;
		
		while (current != null) {
			aux = current.next;
			current.next = prev;
			prev = current;
			current = aux;
		}
		aux = q.head;
		q.head = q.tail;
		q.tail = aux;
	}
}
