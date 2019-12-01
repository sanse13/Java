package grids.b.laberinto_camino_salida;


import hr.base.FIFOQueue;
import hr.base.GridCoordinates;
import hr.base.GridIterator;
import hr.base.SinglyLinkedListNode;

//import hr.base.FIFOQueue;
//import hr.base.GridIterator;

public class Student_Code {

	/**
	 * Dados dos enteros "A" y "B", y una cuadricula con igual numero de filas que
	 * de columnas, y cuyas casillas pueden estar bloqueadas o no, hay que encontrar
	 * el camino mas corto (si existe) para ir desde la (0,A) hasta la casilla B de
	 * la ultima fila, sin pasar por ninguna casilla bloqueada.
	 *
	 * @param enterColumn
	 *            el indice de columna de la casilla de entrada (arriba, "A")
	 * 
	 * @param exitColumn
	 *            el indice de columna de la casilla de salida (arriba, "B")
	 * 
	 * @param laberinto
	 *            un array de boolean. Si es N = laberinto.length, la cuadricula
	 *            tiene N filas y N columnas. Una casilla de coordenadas (fila,
	 *            columna) esta bloqueada si (y solo si) laberinto[fila][columna] es
	 *            FALSE.
	 *
	 * @return una lista de nodos con las coordenadas de las casillas a recorrer
	 *         para ir desde la casilla de entrada hasta la de salida. Esas dos
	 *         casillas deben estar en la lista devuelta, siendo el primer nodo de
	 *         la lista la casilla de entrada. Si no hay camino, devuelve null.
	 *
	 * @Observaciones Dibujar la cuadricula de forma que la casilla superior
	 *                izquierda sea la casilla (fila=0, columna=0), y la inferior
	 *                derecha la (fila=N-1, columna=N-)
	 *
	 * @Observaciones El problema se resolvera viendo la cuadricula como si fuera un
	 *                grafo no dirigido, con tantos nodos como casillas. Dos nodos
	 *                del grafo estan conectados por una arista si las dos casillas
	 *                correspondientes estan una al lado de la otra y ninguna de las
	 *                dos esta bloqueada. Las casillas que estan al lado de una
	 *                casilla son las que estan: encima, debajo, a la izquierda, y a
	 *                la derecha. Pero no las que estan a su lado en diagonal.
	 *
	 * @Observaciones DEBE usarse el iterador del primer ejercicio para recorrer las
	 *                casillas que estan alrededor de una casilla.
	 */
	public static SinglyLinkedListNode<GridCoordinates> solve(int enterColumn, int exitColumn,
			boolean[][] laberinto)  {
	
		boolean[][] marked = new boolean[laberinto.length][laberinto.length];
		FIFOQueue<GridCoordinates> q = new FIFOQueue<>();
		GridCoordinates start = new GridCoordinates(enterColumn, 0);
		marked[start.row][start.column] = true;
		q.addTail(start);
		GridCoordinates[][] predecessors = new GridCoordinates[laberinto.length][laberinto.length];
		
		while (!q.isEmpty()) {
			GridCoordinates origen = q.rmvHead();
			
			GridIterator itr = new GridIterator(laberinto.length, origen);
			while (itr.hasNext()) {
				GridCoordinates dest = itr.next();
				
				int r = dest.row;
				int c = dest.column;
				
				if (laberinto[r][c] && !marked[r][c]) {
					predecessors[r][c] = origen; 
					if (r == laberinto.length-1 && c == exitColumn)
						break;
					q.addTail(dest);
					marked[r][c] = true;
					
				}
			}
		}
		SinglyLinkedListNode<GridCoordinates> path = null;
		if (predecessors[laberinto.length-1][exitColumn] != null) {
			GridCoordinates current = new GridCoordinates(exitColumn, laberinto.length-1);
			while (current != null) {
				SinglyLinkedListNode<GridCoordinates> newNode = new SinglyLinkedListNode<>(current);
				newNode.next = path;
				path = newNode;
				current = predecessors[current.row][current.column];
			}
		}
		return path;
	}
		
}
