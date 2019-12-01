package grids.b.laberinto_camino_salida_java_simple;

import base.FIFOQueue;
import base.SinglyLinkedListNode;
import util.GridCoordinates;

public class Student_Code {

	/**
	 * Dados dos enteros "A" y "B", y una cuadricula con igual numero de filas que de
	 * columnas, y cuyas casillas pueden estar bloqueadas o no, hay que encontrar el
	 * camino mas corto (si existe) para ir desde la (0,A) hasta la casilla B de la ultima
	 * fila, sin pasar por ninguna casilla bloqueada.
	 *
	 * @param enterColumn
	 *            el indice de columna de la casilla de entrada (arriba, "A")
	 *
	 * @param exitColumn
	 *            el indice de columna de la casilla de salida (arriba, "B")
	 *
	 * @param laberinto
	 *            un array de boolean. Si es N = laberinto.length, la cuadricula tiene N
	 *            filas y N columnas. Una casilla de coordenadas (fila, columna) esta
	 *            bloqueada si (y solo si) laberinto[fila][columna] es FALSE.
	 *
	 * @return una lista de nodos con las coordenadas de las casillas a recorrer para ir
	 *         desde la casilla de entrada hasta la de salida. Esas dos casillas deben
	 *         estar en la lista devuelta, siendo el primer nodo de la lista la casilla de
	 *         entrada. Si no hay camino, devuelve null.
	 *
	 * @Observaciones Dibujar la cuadricula de forma que la casilla superior izquierda sea
	 *                la casilla (fila=0, columna=0), y la inferior derecha la (fila=N-1,
	 *                columna=N-)
	 *
	 * @Observaciones El problema se resolvera viendo la cuadricula como si fuera un grafo
	 *                no dirigido, con tantos nodos como casillas. Dos nodos del grafo
	 *                estan conectados por una arista si las dos casillas correspondientes
	 *                estan una al lado de la otra y ninguna de las dos esta bloqueada.
	 *                Las casillas que estan al lado de una casilla son las que estan:
	 *                encima, debajo, a la izquierda, y a la derecha. Pero no las que
	 *                estan a su lado en diagonal.
	 *
	 *                El problema se resolvera viendo la cuadricula como si fuera un grafo
	 *                no dirigido. Pero eso no quiere decir que haya que construir ningun
	 *                grafo: simplemente hay que tenerlo en mente.
	 *
	 * @Observaciones DEBE usarse el iterador del primer ejercicio para recorrer las
	 *                casillas que estan alrededor de una casilla.
	 */
	public static SinglyLinkedListNode<GridCoordinates> solve(int enterColumn, int exitColumn,
			boolean[][] laberinto) {
		assert isMatrix(laberinto);

		final int rows = laberinto.length;
		final int cols = laberinto[0].length;

		/*
		 * Al visitar una casilla hay que memorizar desde donde se ha llegado a ella,
		 * para poder reconstruir el camino entero desde la casilla de inicio.
		 */
		GridCoordinates[][] predecessor = new GridCoordinates[laberinto.length][laberinto.length];
		/*
		 * Por brevedad, llamese A a predecessor. Entonces, para todo 0 <= i < N (=g.nodes)
		 *
		 * a) si A[i] = -1 si no se ha encontrado un camino desde "x" hasta "i"
		 *
		 * b) si A[i] >= 0 se ha encontrado un camino desde el nodo "x" hasta el nodo "i", y la ultima
		 * arista de ese camino es la arista (A[i], i).
		 */

		boolean[][] marked = new boolean[laberinto.length][laberinto.length];

		/*
		 * Los nodos del grafo son las casillas LIBRES del laberinto.
		 */
		FIFOQueue<GridCoordinates> Q = new FIFOQueue<>();

		GridCoordinates start = new GridCoordinates(enterColumn, 0);
		marked[0][enterColumn] = true;
		Q.addTail(start);

		while (!Q.isEmpty()) {
			GridCoordinates orig = Q.rmvHead();

			/*
			 * Procesar cada una de las aristas que salen/tocan el nodo "orig"
			 */

			// GridIterator itr = new GridIterator(laberinto.length, orig);
			// while (itr.hasNext()) {
			// GridCoordinates dest = itr.next();
			//
			// int r = dest.row;
			// int c = dest.column;

			/*
			 * Para hacer eso en el mismo orden que el iterador, hay que ir columna por columna.
			 */
			boolean exit = false;
			for (int colDelta = -1; colDelta <= 1 && !exit; colDelta++) {
				for (int rowDelta = -1; rowDelta <= 1 && !exit; rowDelta++) {
					/*
					 * Evitar desplazamientos en diagonal
					 */
					if (Math.abs(colDelta) + Math.abs(rowDelta) == 1) {
						int r = orig.row + rowDelta;
						int c = orig.column + colDelta;
						/*
						 * Evitar coordenadas ilegales
						 */
						boolean legalCoords = r >= 0 && c >= 0 && r < rows && c < cols;
						if (legalCoords) {
							/*
							 * Si la casilla no esta libre, no hacer nada
							 */
							if (laberinto[r][c]) {
								/*
								 * Los nodos de la arista en curso son: (orig, dest)
								 */
								if (!marked[r][c]) {
									/*
									 * El nodo "dest" es descubierto POR PRIMERA VEZ
									 */
									predecessor[r][c] = orig;
									exit = r == laberinto.length - 1 && c == exitColumn;
									if (!exit) {
										Q.addTail(new GridCoordinates(c, r));
										marked[r][c] = true;
									}
								} else {
									/*
									 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
									 */
								}
							}
						}
					}
				}
			}
		}

		/*
		 * Construir lista representando el camino
		 */
		SinglyLinkedListNode<GridCoordinates> path = null;
		if (predecessor[laberinto.length - 1][exitColumn] != null) {
			/*
			 * Se consiguio llegar a la casilla de salida: (fila:ultima fila; columna: exitColumn)
			 */
			GridCoordinates current = new GridCoordinates(exitColumn, laberinto.length - 1);
			while (current != null) {
				SinglyLinkedListNode<GridCoordinates> newNode = new SinglyLinkedListNode<>(current);
				newNode.next = path;
				path = newNode;
				current = predecessor[current.row][current.column];
			}
		}
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
