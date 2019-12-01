package dfs.topological_sort;

import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;

import base.GenericIntGraph;
import base.IntSinglyLinkedListNode;
import base.IntSinglyLinkedLists;

/**
 * Una clase de objetos para encontrar una "ordenacion topologica" de un grafo DIRIGIDO.
 *
 * </br>
 * Una ordenacion topologica de un grafo es una lista con los nodos del grafo tal que: si
 * hay un camino en el grafo desde un nodo i a otro nodo j, entonces el nodo i precede al
 * j en la lista.
 *
 * </br>
 * Si el grafo no es aciclico, no existe ninguna "ordenacion topologica" de sus nodos.
 * Pero si lo es puede haber mas de una.
 *
 * </br>
 * Ejemplo de uso
 *
 * <pre>
 * public static void procesar(IntGraph g) {
 * 	DFS dfs = new DFS(g);
 * 	boolean isDAG = dfs.call();
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
public class DFS {
	private static final int	WHITE	= 0;
	private static final int	GRAY	= 1;
	private static final int	BLACK	= 2;

	public final GenericIntGraph<Integer> g;

	private int[]	marked;
	private int[]	parent;

	private boolean isDAG = true;

	private int	lastNode;
	private int	firstNode;

	private IntSinglyLinkedListNode topologicalSort;

	public DFS(GenericIntGraph<Integer> g) {
		this.g = g;
		marked = new int[g.size()];
		parent = new int[g.size()];
	}

	public boolean call() {
		for (Integer node : g) {
			if (!isDAG) {
				break;
			}

			if (marked[node] == WHITE) {
				searchFrom(node);
			}
		}
		return isDAG;
	}

	/**
	 * Devuelve un iterable con los nodos de un ciclo del grafo; null si no hay ciclos.
	 */
	public PrimitiveIterator.OfInt cycleIterator() {
		if (isDAG) { throw new IllegalStateException(); }
		return new MyIterator();
	}

	public Iterable<Integer> cycleNodes() {
		if (isDAG) { throw new IllegalStateException(); }
		Iterable<Integer> iterable = () -> cycleIterator();
		return iterable;
	}

	/**
	 * Devuelve cierto si NO hay ciclos en el grafo.
	 */
	public boolean isDAG() {
		return isDAG;
	}

	public IntSinglyLinkedListNode topologicalSort() {
		return topologicalSort;
	}

	private void searchFrom(int from) {

		assert marked[from] == WHITE;
		marked[from] = GRAY;

		/*
		 * Procesar cada arista (orig, x) del grafo
		 */
		Iterable<Integer> adjacencyList = g.edgesOf(from);
		for (Integer edge : adjacencyList) {
			int dest = g.destinationOf(edge);
			/*
			 * Los nodos de la arista en curso son: (orig, dest)
			 */

			if (marked[dest] == WHITE) {
				parent[dest] = from;
				/*
				 * El nodo "dest" es descubierto POR PRIMERA VEZ
				 */
				searchFrom(dest);
				if (!isDAG) { return; }

			} else if (marked[dest] == GRAY) {
				/*
				 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
				 */
				lastNode = from;
				firstNode = dest;
				isDAG = false;
				return;
			} else if (marked[dest] == BLACK) {
				/*
				 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
				 */
			}
		}
		marked[from] = BLACK;
		topologicalSort = IntSinglyLinkedLists.of(from, topologicalSort);
	}

	private class MyIterator implements PrimitiveIterator.OfInt {
		boolean	hasNext	= !isDAG;
		int		current	= lastNode;

		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public int nextInt() {
			if (!hasNext) { throw new NoSuchElementException(); }
			int old = current;
			if (current != firstNode) {
				current = parent[current];
			} else {
				hasNext = false;
			}
			return old;
		}
	}
}
