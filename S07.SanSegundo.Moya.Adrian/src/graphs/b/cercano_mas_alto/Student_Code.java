package graphs.b.cercano_mas_alto;

// Puede interesar usar esas clases
//
import hr.base.IntFIFOQueue;
import hr.base.IntFIFOQueues;
import hr.base.IntGraph;
import hr.base.IntSinglyLinkedListNode;

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
	 *          el grafo referido
	 *  
	 * @return el nodo de mayor altura de G
	 */ 
	public static int solve(int x, int[] H, IntGraph g) {
		int nodoMasAlto = H[x];
		IntFIFOQueue q = new IntFIFOQueue();
		IntFIFOQueues.addToEnd(q, x);
		boolean[] marked = new boolean[g.nodes];
		
		while (q.head != null) {
			int origen = IntFIFOQueues.rmvFirst(q);
			
			IntSinglyLinkedListNode head = g.adjacencyLists[origen];
			
			while(head != null) {
				int dest = head.data;
				if (!marked[dest]) {
					if (H[dest] > H[nodoMasAlto])
						nodoMasAlto = dest;
					IntFIFOQueues.addToEnd(q, dest);
					marked[dest] = true;
				}
				head = head.next;
			}
		}
		return nodoMasAlto;
	}
	
}
