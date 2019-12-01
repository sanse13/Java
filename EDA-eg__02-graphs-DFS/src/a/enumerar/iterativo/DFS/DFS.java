package a.enumerar.iterativo.DFS;

import java.io.BufferedWriter;
import java.io.IOException;

import hr.base.BufferedWriters;
import hr.base.GenericIntGraph;
import hr.base.LIFOQueue;
import hr.base.Queue;

public class DFS<E> {

	GenericIntGraph<E> g;

	BufferedWriter printer;

	boolean[] marked;

	public DFS(GenericIntGraph<E> g, BufferedWriter printer) {
		this.g = g;
		this.printer = printer;
		marked = new boolean[g.size()];
	}

	public void from(int startNode) throws IOException {
		Queue<Integer> q = new LIFOQueue<>();
		
		q.add(startNode);
		marked[startNode] = true;
		
		while(!q.isEmpty()) {
			int elMasViejo = q.rmv();
			Iterable<E> adjacencyList = g.edgesOf(elMasViejo);
			
			for (E edge: adjacencyList) {
				int dest = g.destinationOf(edge);
				if (!marked[dest]) {
					marked[dest] = true;
					q.add(dest);
				}
			}
		}
	}
}
