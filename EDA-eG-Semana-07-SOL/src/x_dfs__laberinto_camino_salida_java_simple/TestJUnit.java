package x_dfs__laberinto_camino_salida_java_simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.SinglyLinkedListNode;
import util.GridCoordinates;
import util.IntArrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit {

	@Test
	public void a1() {
		int[][] laberinto = { { 1, 1, 0, 0, 1 }, { 0, 1, 0, 0, 0 }, { 0, 1, 1, 0, 0 },
				{ 0, 0, 1, 1, 0 }, { 0, 0, 0, 1, 1 } };
		chk("a1", 0, 4, laberinto, 1, true);
	}

	@Test
	public void a2() {
		int[][] laberinto = { { 1, 1, 1, 1, 1 }, { 0, 1, 0, 1, 1 }, { 0, 1, 0, 0, 1 },
				{ 0, 1, 0, 1, 1 }, { 0, 1, 0, 1, 1 } };
		chk("a2", 0, 4, laberinto, 1, true);
	}

	@Test
	public void b1() {
		int[][] laberinto = { { 1, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
				{ 1, 1, 1, 1, 0 }, { 1, 1, 1, 0, 1 } };
		chk("a2", 0, 4, laberinto, 1, false);
	}

	void chk(String id, int colStart, int colEnd, int[][] laberinto, int freeCell,
			boolean expected) {
		assert isMatrix(laberinto);
		int rowStart = 0;
		int rowEnd = laberinto.length - 1;

		System.out.println("[CASO DE PRUEBA@" + id + "]");
		System.out.println("[Test] Inicio(row: " + 0 + "; column: " + colStart + ")");
		System.out.println("[Test] Destino(row: " + laberinto.length + "; column: " + colEnd + ")");
		System.out.println("[Test] hay camino? esperado: " + expected + "; ejecutando...");
		IntArrays.show("Laberinto[Casilla libre: " + freeCell + "]", laberinto);

		boolean[][] booleans = IntArrays.toBoolean(laberinto, freeCell);
		SinglyLinkedListNode<GridCoordinates> path = Student_Code.solve(colStart, colEnd, booleans);

		System.out.println("[Test] hay camino? calculado: " + (path != null));
		assertEquals(expected, path != null);

		if (expected) {
			assert path != null;
			System.out.println("[Test] Validando camino...");

			SinglyLinkedListNode<GridCoordinates> start = path;
			assertEquals(rowStart, start.data.row, "Fila inicio");
			assertEquals(colStart, start.data.column, "Columna inicio");

			while (path.next != null) {
				SinglyLinkedListNode<GridCoordinates> next = path.next;
				GridCoordinates a = path.data;
				GridCoordinates b = next.data;

				assert legalCoords(a.row, a.column, laberinto);

				boolean legalCoords = legalCoords(b.row, b.column, laberinto);
				boolean validMove = Math.abs(a.row - b.row) + Math.abs(a.column - b.column) == 1;
				if (!validMove || !legalCoords) {
					System.out.println("[ERROR] Paso ilegal: " + a + " -> " + b);
					fail(b.toString());
				}
				path = next;
			}
			assert path != null && path.next == null;

			SinglyLinkedListNode<GridCoordinates> end = path;
			assertEquals(rowEnd, end.data.row, "Fila llegada");
			assertEquals(colEnd, end.data.column, "Columna llegada");
		}

		System.out.println("Completado!");
		System.out.println();
	}

	private static boolean isMatrix(int[][] array2D) {
		for (int r = 0; r < array2D.length; r++) {
			int[] row = array2D[r];
			assert row != null && row.length == array2D[0].length;
		}
		return true;
	}

	private static boolean legalCoords(int r, int c, int[][] laberinto) {
		final int rows = laberinto.length;
		final int cols = laberinto[0].length;
		return r >= 0 && c >= 0 && r < rows && c < cols;
	}
}
