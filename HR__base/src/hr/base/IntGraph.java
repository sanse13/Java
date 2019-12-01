package hr.base;

import java.util.NoSuchElementException;

public class IntGraph implements GenericIntGraph<Integer> {
	/*
	 * INV: adjacencyLists != null && nodes == adjacencyLists.length Para todo 0 <=
	 * i < nodes: Para todo x in adjacencyLists[i]: 0 <= x < nodes
	 */

	public final int						nodes;
	public final IntSinglyLinkedListNode[]	adjacencyLists;
	/*
	 * INV: adjacencyLists != null && nodes == adjacencyLists.length Para todo 0 <=
	 * i < nodes: Para todo x in adjacencyLists[i]: 0 <= x < nodes
	 */

	public final boolean isDirectedGraph;

	public String name;

	public IntGraph(boolean isDirected, int nodes,
			IntSinglyLinkedListNode[] adjacencyLists) {
		this.nodes = nodes;
		this.adjacencyLists = adjacencyLists;
		isDirectedGraph = isDirected;
	}

	@Override
	public int destinationOf(Integer edge) {
		return edge;
	}

	@Override
	public IntIterator edgesIterator(int node) {
		return new IntIterator() {
			IntSinglyLinkedListNode current = adjacencyLists[node];

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public int nextInt() {
				if (current == null) { throw new NoSuchElementException(); }
				int tmp = current.data;
				current = current.next;
				return tmp;
			}
		};
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getNumVertices() {
		return nodes;
	}

	@Override
	public boolean isDirected() {
		return isDirectedGraph;
	}

	@Override
	public void setName(String s) {
		name = s;
	}
}
