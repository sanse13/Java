package A.laberinto__RESUELTO;

import base.FIFOQueue;
import util.GridCoordinates;
import util.GridIterator;

public class Student_Code {

	/**
	 * Dada una cuadricula con igual numero de filas que de columnas, y cuyas
	 * casillas pueden estar bloqueadas o no, hay que averiguar si es posible ir
	 * desde la casilla superior izquierda hasta la casilla inferior derecha, sin
	 * pasar por ninguna casilla bloqueada.
	 *
	 * @param laberinto
	 *            un array de boolean. Si es N = laberinto.length, la cuadricula
	 *            tiene N filas y N columnas. Una casilla de coordenadas (fila,
	 *            columna) esta bloqueada si (y solo si) laberinto[fila][columna] es
	 *            FALSE.
	 *
	 * @return true si es posible ir desde la casilla superior izquierda hasta la
	 *         casilla inferior derecha, sin pasar por ninguna casilla bloqueada.
	 *
	 * @Observaciones Dibujar la cuadricula de forma que la casilla superior
	 *                izquierda sea la casilla (fila=0, columna=0), y la inferior
	 *                derecha la (fila=N-1, columna=N-)
	 *
	 * @Observaciones El problema se resolvera viendo la cuadricula como si fuera un
	 *                grafo no dirigido, con tantos nodos como casillas.
	 * 
	 *                Dos nodos del grafo estan conectados por una arista si las dos
	 *                casillas correspondientes estan una al lado de la otra y
	 *                ninguna de las dos esta bloqueada.
	 * 
	 *                Las casillas que estan al lado de una casilla son las que
	 *                estan: encima, debajo, a la izquierda, y a la derecha. Pero no
	 *                las que estan a su lado en diagonal.
	 */
	public static boolean solve(boolean[][] laberinto) {
		boolean[][] marked = new boolean[laberinto.length][laberinto.length];

		/*
		 * Los nodos del grafo son las casillas del laberinto.
		 */
		FIFOQueue<GridCoordinates> Q = new FIFOQueue<>();

		GridCoordinates start = new GridCoordinates(0, 0);
		marked[start.row][start.column] = true;
		Q.addTail(start);

		while (!Q.isEmpty()) {
			GridCoordinates orig = Q.rmvHead();
			System.out.println("[SOLVE] Retirado: " + orig);

			/*
			 * Procesar cada una de las aristas que salen/tocan el nodo "orig"
			 */
			GridIterator itr = new GridIterator(laberinto.length, orig);
			while (itr.hasNext()) {
				GridCoordinates dest = itr.next();

				int r = dest.row;
				int c = dest.column;
				if (laberinto[r][c]) {
					System.out.println("[SOLVE] Casilla libre: " + dest);
					/*
					 * Los nodos de la arista en curso son: (orig, dest)
					 */
					if (!marked[r][c]) {
						/*
						 * El nodo "dest" es descubierto POR PRIMERA VEZ
						 */
						if (isExit(dest, laberinto)) {
							assert laberinto[r][c];
							return true;
						}

						Q.addTail(dest);
						marked[r][c] = true;
						System.out.println("[SOLVE] Puesto: " + dest);
					} else {
						/*
						 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
						 */
					}
				}
			}
		}
		/*
		 * No se ha podido llegar a la casilla de salida.
		 */
		return false;
	}

	private static boolean isExit(GridCoordinates c, boolean[][] laberinto) {
		boolean b = c.column == laberinto.length - 1 && c.row == laberinto.length - 1;
		return b;
	}
}
