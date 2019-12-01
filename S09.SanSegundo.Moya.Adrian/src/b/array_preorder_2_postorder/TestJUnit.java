package b.array_preorder_2_postorder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDateTime;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.IntArrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit {
	static {
		printInfo();
	}

	@Test
	public void a0_base() {
		int[] preorder = { 50 };
		int[] postorder = { 50 };
		chk("a0_base", preorder, postorder);
	}

	@Test
	public void a1_izdo() {
		int[] preorder = { 50, 24, 12 };
		int[] postorder = { 12, 24, 50 };
		chk("a1_izdo", preorder, postorder);
	}

	@Test
	public void a2_dcho() {
		int[] preorder = { 50, 60, 70 };
		int[] postorder = { 70, 60, 50 };
		chk("a2_dcho", preorder, postorder);
	}

	@Test
	public void a3_simple() {
		int[] preorder = { 50, 24, 70 };
		int[] postorder = { 24, 70, 50 };
		chk("a3_simple", preorder, postorder);
	}

	@Test
	public void b1_enunciado() {
		int[] preorder = { 50, 30, 24, 5, 28, 45, 98, 52, 60 };
		int[] postorder = { 5, 28, 24, 45, 30, 60, 52, 98, 50 };
		chk("b1_enunciado", preorder, postorder);
	}

	void chk(String id, int[] preorder, int[] expected) {
		IntArrays.show("[CASO DE PRUEBA@" + id + "] Array en PRE-orden", preorder);

		int[] postorder = My_Code.solve(preorder);

		IntArrays.show("[CASO DE PRUEBA@" + id + "] Array en POST-orden esperado:", expected);
		IntArrays.show("[CASO DE PRUEBA@" + id + "] Array en POST-orden calculado:", postorder);
		assertArrayEquals(expected, postorder);
		System.out.println("Completado!");
		System.out.println();
	}

	private static void printInfo() {
		System.out.println("[INFO] " + System.getProperty("user.home"));
		System.out.println("[INFO] " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}
}
