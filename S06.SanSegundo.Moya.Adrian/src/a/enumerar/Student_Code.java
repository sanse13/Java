package a.enumerar;

import java.io.BufferedWriter;
import java.io.IOException;

import hr.base.BufferedWriters;
import hr.base.IntFIFOQueue;
import hr.base.IntFIFOQueues;
import hr.base.IntGraph;
import hr.base.IntSinglyLinkedListNode;

public class Student_Code {

	/**
	 * Escribe en "printer" los nodos del grafo alcanzables desde "startNode", segun un recorrido en anchura
	 * del mismo.
	 */
	public static void solve(IntGraph g, int startNode, BufferedWriter printer) throws IOException {
		/*
		 * Un BufferedWriter es un objeto que sirve para escribir en un archivo.
		 *
		 * Para hacerlo mas facil, usar:
		 *
		 * BufferedWriters.print(printer, "int/boolean/char/String");
		 *
		 * BufferedWriters.println(printer,"int/boolean/char/String/int[]/IntSinglyLinkedListNode");
		 *
		 * Para escribir un espacio en blanco:
		 *
		 * BufferedWriters.print(printer, ' ');
		 *
		 * Para terminar la linea en curso:
		 *
		 * BufferedWriters.println(printer);
		 */

		int cantidadDeNodosDelGrafo = g.nodes;
		/*
		 * Los nodos de los grafos se suponen siempre numerados 0 ..cantidadDeNodosDelGrafo-1
		 */

		boolean[] marked = new boolean[cantidadDeNodosDelGrafo];

		IntFIFOQueue q = new IntFIFOQueue();
		/*
		 * Cola con los nodos marcados cuyas aristas no es seguro que hayan sido procesadas en su totalidad.
		 */

		IntFIFOQueues.addToEnd(q, startNode);
		marked[startNode] = true;

		while (q.head != null) {
			int orig = IntFIFOQueues.rmvFirst(q);
			assert marked[orig];
			/*
			 * El nodo orig ha sido descubierto pero sus aristas NO han sido procesadas. Si el grafo es
			 * dirigido, no ha sido procesada ninguna; y si no, no necesariamente todas lo han sido.
			 */

			BufferedWriters.println(printer, orig);

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
	}
}
