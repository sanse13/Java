package A.laberinto__RESUELTO;

import hr.base.FIFOQueue;
import hr.base.GridCoordinates;
import hr.base.GridIterator;
import hr.base.IntFIFOQueue;

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
		
		FIFOQueue<GridCoordinates> q = new FIFOQueue<>();
		
		GridCoordinates start = new GridCoordinates(0, 0);
		q.addTail(start);
		marked[start.row][start.column] = true;
		
		while (!q.isEmpty()) {
			GridCoordinates elMasViejo = q.rmvHead();
			
			GridIterator itr = new GridIterator(laberinto.length, elMasViejo);
		
			while (itr.hasNext()) {
				GridCoordinates dest = itr.next();
				int r = dest.row;
				int c = dest.column;
				
				if (!marked[r][c] && laberinto[r][c]) {
					if (r == laberinto.length-1 && c == laberinto.length-1) {
						return true;
					}
					q.addTail(dest);
					marked[r][c] = true;
				}
			}
		}
		return false;
	}
		
	
	

	
	
	

	private static boolean isExit(GridCoordinates c, boolean[][] laberinto) {
		boolean b = c.column == laberinto.length - 1 && c.row == laberinto.length - 1;
		return b;
	}
}
