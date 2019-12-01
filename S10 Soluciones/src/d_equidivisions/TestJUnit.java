package d_equidivisions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.IntArrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit {

	@Test
	public void a0_enunciado_si() {
		int[][] matriz = { { 1, 1, 1, 5, 5 }, { 2, 1, 5, 5, 4 }, { 2, 1, 5, 4, 4 },
				{ 2, 2, 4, 4, 3 }, { 2, 3, 3, 3, 3 } };
		chk("enunciado_si", matriz, true);
	}

	@Test
	public void a1_enunciado_no() {
		int[][] matriz = { { 1, 1, 1, 4, 5 }, { 2, 1, 5, 4, 5 }, { 2, 1, 5, 5, 4 },
				{ 2, 2, 4, 4, 3 }, { 2, 3, 3, 3, 3 } };
		chk("enunciado_no", matriz, false);
	}

	void chk(String id, int[][] matriz, boolean expected) {
		System.out.println("[CASO DE PRUEBA@" + id + "]");
		IntArrays.show("Matriz", matriz);

		System.out.println("[Test] es equidivision? esperado: " + expected + ". Ejecutando...");
		boolean b = My_Code.esEquidivision(matriz, matriz.length);

		System.out.println("[Test] es equidivision? calculado: " + b);
		assertEquals(expected, b);

		System.out.println("Completado!");
		System.out.println();
	}
}
