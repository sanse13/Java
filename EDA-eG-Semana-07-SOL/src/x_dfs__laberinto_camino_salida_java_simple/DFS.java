package x_dfs__laberinto_camino_salida_java_simple;

import base.SinglyLinkedListNode;
import util.GridCoordinates;

/**
 * Una clase de objetos para encontrar un camino entre dos casillas de un laberinto.
 *
 * </br>
 * Las casillas de inicio y fin se establecen en la constructora.
 */
public class DFS {

	private boolean[][] marked;

	boolean[][]	maze;
	int			mazeRows;
	int			mazeCols;

	final GridCoordinates	startingBox;
	final GridCoordinates	endingBox;

	public DFS(boolean[][] maze, GridCoordinates start, GridCoordinates end) {
		assert isMatrix(maze);

		startingBox = start;
		endingBox = end;

		this.maze = maze;
		mazeRows = maze.length;
		mazeCols = maze[0].length;

		marked = new boolean[mazeRows][mazeCols];
	}

	/**
	 * @return un camino dede la casilla de origen a la de destino, si lo hay; null, si
	 *         no.
	 */
	SinglyLinkedListNode<GridCoordinates> call() {
		return searchFrom(startingBox.row, startingBox.column);
	}

	/**
	 * Recorrido en profundidad desde la casilla especificada hasta la casilla de destino.
	 *
	 * @return un camino si lo hay; null, si no.
	 */
	private SinglyLinkedListNode<GridCoordinates> searchFrom(int row, int column) {
		assert !marked[row][column];

		marked[row][column] = true;

		if (row == endingBox.row && column == endingBox.column) {
			return new SinglyLinkedListNode<>(endingBox);
		} else {
			for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
				for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
					/*
					 * Evitar desplazamientos en diagonal
					 */
					if (Math.abs(deltaCol) + Math.abs(deltaRow) == 1) {
						int r = row + deltaRow;
						int c = column + deltaCol;
						/*
						 * Evitar coordenadas ilegales
						 */
						boolean legalCoords = r >= 0 && c >= 0 && r < mazeRows && c < mazeCols;
						if (legalCoords) {
							/*
							 * Si la casilla no esta libre, no hacer nada
							 */
							if (maze[r][c]) {
								/*
								 * La arista en curso es...
								 */
								int rowDest = row + deltaRow;
								int colDest = column + deltaCol;
								if (!marked[rowDest][colDest]) {
									/*
									 * El nodo "dest" es descubierto POR PRIMERA VEZ
									 */
									SinglyLinkedListNode<GridCoordinates> path = searchFrom(rowDest,
											colDest);
									if (path != null) {
										GridCoordinates tmp = new GridCoordinates(row, column);
										return new SinglyLinkedListNode<>(tmp, path);
									}
								} else {
									/*
									 * El nodo "dest" ya ha sido visitado
									 */
								}
							}
						}
					}
				}
			}
			return null;
		}
	}

	private static boolean isMatrix(boolean[][] array2D) {
		for (int r = 0; r < array2D.length; r++) {
			boolean[] row = array2D[r];
			assert row != null && row.length == array2D[0].length;
		}
		return true;
	}
}