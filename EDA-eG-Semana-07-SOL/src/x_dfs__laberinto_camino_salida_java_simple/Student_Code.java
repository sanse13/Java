package x_dfs__laberinto_camino_salida_java_simple;

import base.SinglyLinkedListNode;
import util.GridCoordinates;

public class Student_Code {

	/**
	 * Dados dos enteros "A" y "B", y una cuadricula con igual numero de filas que de
	 * columnas, y cuyas casillas pueden estar bloqueadas o no, hay que encontrar el
	 * camino mas corto (si existe) para ir desde la (0,A) hasta la casilla B de la ultima
	 * fila, sin pasar por ninguna casilla bloqueada.
	 *
	 * Solucion mediante un recorrido en profundidad implementado recursivamente.
	 *
	 * Sin limitaciones sobre el orden de recorrido de las casillas contiguas.
	 */
	public static SinglyLinkedListNode<GridCoordinates> solve(int enterColumn, int exitColumn,
			boolean[][] laberinto) {
		assert isMatrix(laberinto);

		GridCoordinates start = new GridCoordinates(enterColumn, 0);
		GridCoordinates end = new GridCoordinates(exitColumn, laberinto.length - 1);

		DFS dfs = new DFS(laberinto, start, end);
		SinglyLinkedListNode<GridCoordinates> path = dfs.call();

		return path;
	}

	private static boolean isMatrix(boolean[][] array2D) {
		for (int r = 0; r < array2D.length; r++) {
			boolean[] row = array2D[r];
			assert row != null && row.length == array2D[0].length;
		}
		return true;
	}
}
