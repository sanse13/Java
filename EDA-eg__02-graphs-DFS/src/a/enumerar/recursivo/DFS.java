package a.enumerar.recursivo;

import java.io.BufferedWriter;
import java.io.IOException;

import hr.base.BufferedWriters;
import hr.base.GenericIntGraph;

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
		marked[startNode] = true;
		
		Iterable<E> adjacencyList = g.edgesOf(startNode);
		for (E edge: adjacencyList) {
			int dest = g.destinationOf(edge);
			if (!marked[dest])
				from(dest);
			}
		}
	
	
}
