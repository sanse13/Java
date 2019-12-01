package c_caballo_ajedrez;

/**
 * Una clase de objetos para encontrar un camino entre dos casillas de un tablero de ajedrez pasando
 * de una casilla a la siguiente mediante alguno de los movimientos que puede hacer un caballo.
 *
 * </br>
 * Las casillas de inicio y fin se establecen en la constructora.
 *
 * </br>
 * Observacion: Debe resolverse mediante un recorrido en PROFUNDIDAD.
 *
 * </br>
 * Ejemplo de uso: ver My_Code.
 */
public class DFS {

	private boolean[][] marked;

	final Coordinates startingBox;
	final Coordinates endingBox;

	public DFS(Coordinates start, Coordinates end) {
		startingBox = start;
		endingBox = end;

		marked = new boolean[ChessBoard.DIMENSION][ChessBoard.DIMENSION];
	}

	/**
	 * Recorrido en profundidad desde la casilla de origen a la de destino.
	 *
	 * @return true si hay camino; false si no.
	 */
	public CoordinatesSinglyLinkedListNode call() {
		CoordinatesSinglyLinkedListNode path = searchFrom(startingBox.row, startingBox.column);
		return path;
	}

	/**
	 * Recorrido en profundidad de los nodos (casillas) alcanzables desde (fila=row; columna=col)
	 * que no estan marcados.
	 * 
	 * @return una lista con las casillas de un camino desde (fila=row; columna=col) hasta la
	 *         casilla de destino; null, si no hay camino.
	 */
	/*
	 * Otra alternativa:
	 * 
	 * Usar otro campo: private Coordinates[][] parent;
	 * 
	 * para memorizar desde donde llega el DFS a cada casilla alcanzable.
	 * 
	 * Si parent[i][j] != null, entonces el recorrido en profundidad descubrio la casilla (fila=i,
	 * columna=j) desde la casilla de coordenadas parent[i][j].
	 */
	private CoordinatesSinglyLinkedListNode searchFrom(int row, int column) {

		assert !marked[row][column];

		marked[row][column] = true;

		if (row == endingBox.row && column == endingBox.column) {
			return new CoordinatesSinglyLinkedListNode(endingBox);
		} else {
			/*
			 * Procesar cada arista que sale del nodo...
			 * 
			 * O sea: cada casilla a la cual pueda saltar el caballo desde (fila=row; columna=col).
			 */
			for (int deltaRow = -2; deltaRow <= 2; deltaRow++) {
				for (int deltaCol = -2; deltaCol <= 2; deltaCol++) {
					boolean isLegalMove = ChessBoard.isLegalMove(row, column, deltaRow, deltaCol);
					if (isLegalMove) {
						int rowDest = row + deltaRow;
						int colDest = column + deltaCol;

						/*
						 * La arista en curso:
						 * 
						 * (fila=row; columna=col) <-> (fila=rowDest; columna=colDest)
						 */

						if (!marked[rowDest][colDest]) {
							/*
							 * El nodo "dest" es descubierto POR PRIMERA VEZ
							 */
							CoordinatesSinglyLinkedListNode path = searchFrom(rowDest, colDest);
							if (path != null) {
								Coordinates tmp = new Coordinates(row, column);
								return new CoordinatesSinglyLinkedListNode(tmp, path);
							}
						} else {
							/*
							 * El nodo "dest" ya ha sido visitado
							 */
						}
					}
				}
			}
			return null;
		}
	}
}
