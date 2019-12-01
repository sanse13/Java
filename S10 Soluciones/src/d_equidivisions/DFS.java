package d_equidivisions;

import java.util.Arrays;

/**
 * Una clase de objetos para determinar si una matriz representa una equidivision de una cuadricula.
 *
 * </br>
 * La matriz y sus dimensiones se establecen en la constructora.
 *
 * </br>
 * Observacion: Debe resolverse mediante un esquema de recorrido de un grafo.
 *
 * Ver My_Code.
 *
 * </br>
 * Ejemplo de uso: ver My_Code.
 */
public class DFS {

	private int[][] matriz;
	private int dimension;

	private boolean[][] marked;

	/*
	 * usedLabels[i] != 0 si y solo si "i" ha sido usado como etiqueta de alguna region
	 */
	private int[] usedLabels;

	public DFS(int[][] matriz, int n) {
		/*
		 * matriz tiene n filas...
		 */
		assert matriz.length == n : "Numero de filas de la cuadricula != " + n;

		/*
		 * ...y todas las filas tienen n columnas
		 */
		assert Arrays.stream(matriz).filter(r -> r.length != n)
				.count() == 0 : "Filas con numero de columnas != " + n;

		this.matriz = matriz;
		dimension = n;

		marked = new boolean[n][n];

		usedLabels = new int[dimension];
	}

	/**
	 * Recorrido en profundidad de toda la cuadricula, vista como un grafo.
	 *
	 * @return true si representa una equidivision; false, si no.
	 */
	public boolean call() {
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				if (!marked[row][col]) {
					int label = matriz[row][col];
					int labelMenosUno = label - 1;

					if (usedLabels[labelMenosUno] != 0) {
						/*
						 * Etiqueta ya usada en otra region
						 */
						return false;
					}

					searchFrom(row, col);

					if (usedLabels[labelMenosUno] != dimension) {
						/*
						 * Region descubierta con numero de casillas erroneo
						 */
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * @return true si las coordenadas especificadas quedan fuera de la cuadricula.
	 */
	private boolean isOutOfBounds(int row, int col) {
		return 0 > row || 0 > col || row >= dimension || col >= dimension;
	}

	/**
	 * Recorrido en profundidad de los nodos (casillas) alcanzables desde (fila=row; columna=col)
	 * que no estan marcados.
	 */
	private void searchFrom(int row, int col) {
		assert !marked[row][col];

		marked[row][col] = true;

		int label = matriz[row][col];
		int labelMenosUno = label - 1;

		usedLabels[labelMenosUno] = usedLabels[labelMenosUno] + 1;
		if (usedLabels[labelMenosUno] > dimension) {
			// region con mas casillas de las permitidas.
			return;
		}

		/*
		 * Procesar cada arista que sale del nodo...
		 * 
		 * O sea: cada casilla que tenga un lado comun con (fila=row; columna=col) y la misma
		 * etiqueta.
		 */
		for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
			for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
				/*
				 * excluir movimientos en diagonal...
				 */
				boolean validMove = Math.abs(deltaRow) + Math.abs(deltaCol) == 1;

				int rowDest = row + deltaRow;
				int colDest = col + deltaCol;

				/*
				 * ...y coordenadas fuera de la cuadricula
				 */
				boolean b = validMove && !isOutOfBounds(row + deltaRow, col + deltaCol);
				b = b && label == matriz[rowDest][colDest];
				if (b) {
					/*
					 * ... y casillas contiguas pero con etiqueta distintas
					 */

					/*
					 * La arista en curso:
					 * 
					 * (fila=row; columna=col) <-> (fila=rowDest; columna=colDest)
					 */

					if (!marked[rowDest][colDest]) {
						/*
						 * El nodo "dest" es descubierto POR PRIMERA VEZ
						 */
						searchFrom(rowDest, colDest);
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
