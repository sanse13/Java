package c_caballo_ajedrez;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit__TODO {

	@Test
	public void a1_move() {
		chk("1 movimiento", 0, 0, 2, 1, true);
	}

	@Test
	public void a1_move_all() {
		for (int row = 0; row < ChessBoard.DIMENSION; row++) {
			for (int column = 0; column < ChessBoard.DIMENSION; column++) {
				for (int deltaRow = -2; deltaRow <= 2; deltaRow++) {
					for (int deltaCol = -2; deltaCol <= 2; deltaCol++) {
						boolean b = deltaRow != 0 || deltaCol != 0;
						b = b && !ChessBoard.isOutOfBounds(row + deltaRow, column + deltaCol);
						b = b && ChessBoard.isLegalMove(row, column, deltaRow, deltaCol);
						if (b) {
							int r = row + deltaRow;
							int c = column + deltaCol;
							chk("1 movimiento desde", row, column, r, c, true);
						}
					}
				}
			}
		}
	}

	@Test
	public void a2_move() {
		chk("x movimientos", 0, 0, 7, 7, true);
	}

	void chk(String id, int rowStart, int colStart, int rowEnd, int colEnd, boolean expected) {
		System.out.println("[CASO DE PRUEBA@" + id + "]");
		System.out.println("[Test] Inicio(row: " + rowStart + "; column: " + colStart + ")");
		System.out.println("[Test] Destino(row: " + rowEnd + "; column: " + colEnd + ")");
		System.out.println("[Test] hay camino? esperado: " + expected + "; ejecutando...");

		CoordinatesSinglyLinkedListNode path = My_Code.findPath(rowStart, colStart, rowEnd, colEnd);

		System.out.println("[Test] hay camino? calculado: " + (path != null));
		assertEquals(expected, path != null);

		if (expected) {
			assert path != null;
			System.out.println("[Test] Validando camino...");

			CoordinatesSinglyLinkedListNode start = path;
			assertEquals(rowStart, start.data.row, "Fila inicio");
			assertEquals(colStart, start.data.column, "Columna inicio");

			while (path.next != null) {
				CoordinatesSinglyLinkedListNode next = path.next;
				Coordinates a = path.data;
				Coordinates b = next.data;

				boolean tmp = ChessBoard.isLegalMove(a, b);
				if (!tmp) {
					System.out.println("[ERROR] Paso ilegal: " + a + " -> " + b);
				}
				assertTrue(tmp);

				path = next;
			}
			assert path != null && path.next == null;

			CoordinatesSinglyLinkedListNode end = path;
			assertEquals(rowEnd, end.data.row, "Fila llegada");
			assertEquals(colEnd, end.data.column, "Columna llegada");
		}

		System.out.println("Completado!");
		System.out.println();
	}
}
