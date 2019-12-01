package c_caballo_ajedrez;

/**
 * Clase de utilidad publica con metodos referentes a tableros de ajedrez y caballos.
 */
public class ChessBoard {
	public static final int DIMENSION = 8;

	private ChessBoard() {
	}

	/**
	 * @return true si un caballo situado en la casilla a puede desplazarse legalmente a las
	 *         coordenadas b.
	 */
	public static boolean isLegalMove(Coordinates a, Coordinates b) {
		assert !isOutOfBounds(a.row, a.column);
		int deltaRow = b.row - a.row;
		int deltaColumn = b.column - a.column;
		boolean tmp = isLegalMove(a.row, a.column, deltaRow, deltaColumn);
		return tmp;
	}

	/**
	 * @return true si un caballo situado en la casilla (fila=row, columna=col) puede desplazarse
	 *         legalmente a las coordenadas:
	 *
	 *         <pre>
	 *         (fila=row+deltaRow, 	columna=col+deltaCol)
	 *         </pre>
	 */
	public static boolean isLegalMove(int row, int column, int deltaRow, int deltaColumn) {
		boolean isLegalMove = Math.abs(deltaRow) + Math.abs(deltaColumn) == 3
				&& Math.abs(deltaRow) * Math.abs(deltaColumn) == 2;
		boolean b = isLegalMove && !isOutOfBounds(row + deltaRow, column + deltaColumn);
		return b;
	}

	/**
	 * @return true si las coordenadas especificadas quedan fuera del tablero.
	 */
	public static boolean isOutOfBounds(int row, int col) {
		return 0 > row || 0 > col || row >= DIMENSION || col >= DIMENSION;
	}

	public static void main(String[] args) { // Ejemplo de uso
		Coordinates a = new Coordinates(0, 0);
		Coordinates b = new Coordinates(2, 1);

		boolean isLegalMove = isLegalMove(a, b);

		System.out.println(a + " -> " + b + "; legal? " + isLegalMove);
	}
}
