package c.camino_hasta_destino;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

import hr.base.BufferedWriters;
import hr.base.IntFIFOQueue;
import hr.base.IntFIFOQueues;
import hr.base.IntGraph;
import hr.base.IntSinglyLinkedListNode;

public class Student_Code {

	/**
	 * Escribe en "printer" los nodos de un camino desde el nodo "x" hasta el "y" de longitud minima
	 * (cualquier otro camino desde "x" hasta "y" esta compuesto por al menos el mismo numero de aristas.)
	 *
	 * Caso de haber varios caminos de longitud minima, selecciona el correspondiente a un recorrido en
	 * anchura del grafo.
	 *
	 * Los nodos del camino se escriben en "printer" desde el ultimo (que es "y") hasta el primero (que es
	 * "x"), separados por un espacio, y terminando tambien con un espacio.
	 *
	 * Si no hay camino desde el nodo "x" hasta el "y", no imprime nada.
	 *
	 * ATENCION! La ejecucion debe terminar tan pronto se encuentra un camino desde el nodo "x" hasta el "y".
	 *
	 */
	public static void solve(IntGraph g, int x, int y, BufferedWriter printer) throws IOException {
		boolean[] marked = new boolean[g.nodes];
		marked[x] = true;
		int[] predecessors = new int[g.nodes];
		Arrays.fill(predecessors, -1);
		
		Stack<Integer> q = new Stack<>();
		q.add(x);
		while (!q.isEmpty()) {
			int origen = q.pop();
			
			IntSinglyLinkedListNode head = g.adjacencyLists[origen];
			while (head != null) {
				int dest = head.data;
				
				if (!marked[dest]) {
					q.add(dest);
					marked[dest] = true;
					predecessors[dest] = origen;
					if (dest == y)
						break;
				}
				head = head.next;
			}
			
		}
		if (predecessors[y] != -1) {
			int current = y;
			while (current != -1) {
				BufferedWriters.print(printer, current+" ");
				current = predecessors[current];
			}
		}
		BufferedWriters.println(printer, "");
	}
}
