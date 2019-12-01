package a.bfs.connected_test;

import base.FIFOQueue;
import base.GenericIntGraph;
import base.Queue;

/**
 * Una clase de objetos para determinar si un grafo NO-DIRIGIDO G es "conexo"; y si no lo
 * es, determinar cuantas componentes conexas tiene.
 *
 * </br>
 * Ejemplo de uso
 *
 * <pre>
 * public static void procesar(IntGraph g) {
 * 	BFS bfs = new BFS(g);
 * 	int componentesConexas = bfs.call();
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

	private int componentesConexas = 0;

	/*
	 * componenteConexa[i] indica cual es la componente conexa a la cual pertenece el nodo
	 * "i".
	 */
	private int[] componenteConexa;

	private Queue<Integer> q = new FIFOQueue<>();

	public BFS(GenericIntGraph<Integer> g) {
		this.g = g;
		marked = new boolean[g.size()];

		componenteConexa = new int[g.size()];
	}

	public int call() {
		for (Integer node : g) {
			if (!marked[node]) {
				searchFrom(node);
				componentesConexas++;
			}
		}
		return componentesConexas;
	}

	/**
	 * Recorrido en anchura desde el nodo s.
	 */
	private void searchFrom(int s) {
		assert !marked[s];

		q.add(s);
		marked[s] = true;

		while (!q.isEmpty()) {
			int orig = q.rmv();
			assert marked[orig];

			componenteConexa[orig] = componentesConexas;

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
				} else {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				}
			}
		}
	}
}
