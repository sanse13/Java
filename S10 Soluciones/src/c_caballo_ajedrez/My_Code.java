package c_caballo_ajedrez;

public class My_Code {

	/**
	 * Busca un camino entre dos casillas de un tablero de ajedrez pasando de una casilla
	 * a la siguiente mediante alguno de los movimientos que puede hacer un caballo.
	 *
	 * Cada casilla del camino solo puede atravesarse en una ocasion.
	 *
	 * @param rowStart
	 *            fila de la casilla de inicio
	 * @param colStart
	 *            columna de la casilla de inicio
	 * @param rowEnd
	 *            fila de la casilla de llegada
	 * @param colEnd
	 *            columna de la casilla de llegada
	 *
	 * @precondition las coordenadas de las casillas de inicio y llegada son legales y
	 *               distintas.
	 *
	 * @return una lista con las casillas del camino. La primera casilla sera la casilla
	 *         de inicio y la ultima la de llegada. Si no hay camino, devuelve null.
	 *
	 *         Observacion: Debe resolverse mediante un recorrido en PROFUNDIDAD.
	 *
	 *         Considerar que el tablero es un grafo: las casillas son los nodos, y hay
	 *         una arista entre dos casillas si un caballo puede desplazarse de una a
	 *         otra. Pero no hay que construir explicitamente ese grafo, sino adaptar el
	 *         esquema del recorrido DFS a ese escenario.
	 *
	 *         La solucion debe tener un tiempo de ejecucion lineal en el numero de nodos
	 *         y aristas del grafo.
	 */
	public static CoordinatesSinglyLinkedListNode findPath(int rowStart, int colStart, int rowEnd,
			int colEnd) {
		/*
		 * Si se prefiere, eliminar lo que sigue y resolver a su estilo.
		 *
		 * Si no, completar DFS.
		 */
		Coordinates start = new Coordinates(rowStart, colStart);
		Coordinates end = new Coordinates(rowEnd, colEnd);

		DFS dfs = new DFS(start, end);
		CoordinatesSinglyLinkedListNode path = dfs.call();

		return path;
	}
}
