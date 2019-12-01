package b_es_heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.BinaryTrees;
import base.IntBinaryTreeNode;
import base.IntBinaryTrees;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit {

	@Test
	public void a0_simple() {
		chk("a0_simple", new Integer[] { 2, null, 7 }, false);
	}

	@Test
	public void a1_ejemplo() {
		chk("a1_ejemplo", new Integer[] { 2, 4, 7, 6, 5, 10, 12, 9, 8 }, true);
	}

	@Test
	public void a2_ejemplo() {
		chk("a2_ejemplo", new Integer[] { 2, 4, 7, 6, 5, 10 }, true);
	}

	@Test
	public void a3_ejemplo() {
		chk("a3_ejemplo", new Integer[] { 2, 4, 7, 6, 5, null, 12 }, false);
	}

	@Test
	public void a4_ejemplo() {
		chk("a4_ejemplo", new Integer[] { 2, 4, 7, 6, 5, 10, null, 9, 8 }, false);
	}

	@Test
	public void b0_heap() {
		chk("b0_heap", new Integer[] { 8, null, 7 }, false);
	}

	@Test
	public void b1_heap() {
		chk("b1_heap", new Integer[] { 2, 4, 7, 6, 5, 1 }, false);
	}

	void chk(String id, Integer[] binaryArray, boolean expected) {
		System.out.println("[CASO DE PRUEBA: " + id + "]");
		IntBinaryTreeNode root = IntBinaryTrees
				.toIntTree(BinaryTrees.buildFromBinaryArray(binaryArray));
		IntBinaryTrees.showPreOrder("Arbol en pre-orden", root);

		System.out.println("Es monton?...");
		boolean ok = My_Code.isHeap(root);

		System.out.println("Es monton? esperado: " + expected + "; resultado: " + ok);
		assertEquals(ok, expected);

		System.out.println("Completado!");
		System.out.println();
	}
}
