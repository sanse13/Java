package bfs.bipartite_test;

import base.FIFOQueue;
import base.GenericIntGraph;
import base.Queue;

/**
 * Una clase de objetos para determinar si un grafo es "bipartito".
 *
 * </br>
 * Un grafo es "bipartito" si sus nodos se pueden colorear usando a lo sumo dos colores,
 * de manera que si (u, v) es una arista del grafo, el color asignado al nodo u sea
 * distinto del asignado a v.
 *
 * </br>
 * Ejemplo de uso
 *
 * <pre>
 * public static void procesar(IntGraph g) {
 * 	BFS bfs = new BFS(g);
 * 	boolean esBipartito = bfs.call();
 *
 * 	// resto...
 * }
 * </pre>
 *
 * </br>
 * El grafo se establece en la constructora.
 *
 * </br>
 * Los resultados de las consultoras pueden ser erroneos si el grafo se modifica despues
 * de llamar a "call".
 */
public class BFS {

	public final GenericIntGraph<Integer> g;

	private boolean[] marked;

	/*
	 * el nodo "i" esta marcado sii "i" tiene color asignado
	 *
	 * si el nodo "i" esta marcado y colorAsignado[i] es false su color es A, si no es B
	 */
	private boolean[]	colorAsignado;
	private boolean		esBipartito	= true;

	private Queue<Integer> q = new FIFOQueue<>();

	public BFS(GenericIntGraph<Integer> g) {
		this.g = g;
		marked = new boolean[g.size()];

		colorAsignado = new boolean[g.size()];
	}

	public boolean call() {
		for (Integer node : g) {
			if (!marked[node]) {
				searchFrom(node);
				if (!esBipartito) {
					break;
				}
			}
		}
		return esBipartito;
	}

	private void searchFrom(int s) {
		assert !marked[s];

		q.add(s);
		marked[s] = true;

		while (!q.isEmpty()) {
			int orig = q.rmv();
			assert marked[orig];

			/*
			 * Procesar cada arista (orig, x) del grafo
			 */
			Iterable<Integer> adjacencyList = g.edgesOf(orig);
			for (Integer edge : adjacencyList) {
				int dest = g.destinationOf(edge);
				/*
				 * La arista en curso es: (orig, dest)
				 */
				if (!marked[dest]) {
					/*
					 * El nodo "dest" es descubierto POR PRIMERA VEZ
					 */
					q.add(dest);
					marked[dest] = true;

					/*
					 * dest solo puede colorearse con el opuesto al asignado a orig
					 */
					colorAsignado[dest] = !colorAsignado[orig];
				} else {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 *
					 * Si su color es incompatible con el de "orig" => el grafo no es bipartito
					 */
					if (colorAsignado[dest] == colorAsignado[orig]) {
						esBipartito = false;
						return;
					}
				}
			}
		}
	}
}
