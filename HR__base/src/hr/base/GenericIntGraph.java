package hr.base;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface que describe el comportamiento general de grafos representados mediante
 * listas de adyacencia, con nodos numerados.
 *
 * E: tipo de arista
 */
public interface GenericIntGraph<E> {

	public int destinationOf(E edge);

	/**
	 * Devuelve un iterador para recorrer la lista de adyacencia correspondiente al nodo
	 * especificado.
	 *
	 * @param node
	 *            el índice del nodo.
	 */
	public Iterator<E> edgesIterator(int node);

	default public Iterable<E> edgesOf(int node) {
		return new Iterable<E>() {

			@Override
			public Iterator<E> iterator() {
				return edgesIterator(node);
			}
		};
	}

	public String getName();

	/**
	 * Devuelve el número de nodos del grafo.
	 */
	public int getNumVertices();

	/**
	 * Devuelve TRUE si el grafo es dirigido; FALSE, en caso contrario.
	 */
	public boolean isDirected();

	/**
	 * Devuelve la etiqueta para el nodo especificado.
	 *
	 * Diferentes subclases pueden redefinir este método.
	 */
	default public String nodeLabel(int n) {
		return String.valueOf(n);
	}

	/**
	 * Devuelve una nueva lista con las etiquetas de los nodos especificados.
	 */
	default public List<String> nodes2labels(List<Integer> nodes) {
		return nodes.stream().map(i -> nodeLabel(i)).collect(Collectors.toList());
	}

	public void setName(String s);

	/**
	 * Devuelve un iterador para recorrer los nodos del grafo.
	 */
	default public IntIterator vertexIterator() {
		return new IntRangeIterator(getNumVertices());
	}
}