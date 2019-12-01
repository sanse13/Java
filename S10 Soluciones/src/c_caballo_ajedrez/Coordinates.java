package c_caballo_ajedrez;

/**
 * Los objetos de esta clase representan coordenadas de casillas en un tablero de ajedrez.
 */
public class Coordinates {
	public final int	column;
	public final int	row;

	public Coordinates(int row, int column) {
		this.column = column;
		this.row = row;
	}

	@Override
	public String toString() { // Para mejor uso del debugger.
		return "Coordinates [row=" + row + ", column=" + column + "]";
	};
}
