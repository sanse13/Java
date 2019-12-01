package a.OBLIGATORIO.es_bst;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.IntBinaryTreeNode;
import base.IntBinaryTrees;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnit {
	private static final int N = 1000_000;

	static {
		printInfo();
	}

	@Test
	public void a0_simple_true() {
		chk("a_simple_true", new int[] { 100, 50, 200, 25, 75, 150, 300 }, true);
	}

	@Test
	public void a1_simple_false() {
		chk("a_simple_false", new int[] { 100, 50, 200, 25, 750, 150, 300 }, false);
	}

	@Test
	public void a2_simple_false() {
		chk("a_simple_true", new int[] { 100, 50, 200, 25, 75, 15, 300 }, false);
	}

	//@Test
	public void b_grande_true() {
		IntBinaryTreeNode root = new IntBinaryTreeNode();
		root.left = createLeft(N);
		root.right = createRight(N);
	}

	void chk(String id, int[] binaryArray, boolean expected) {
		System.out.println("[CASO DE PRUEBA@" + id + "] Arbol");
		IntBinaryTreeNode root = IntBinaryTrees.buildFromBinaryArray(binaryArray);
		IntBinaryTrees.showPreOrder(root);

		System.out.println("[Test] es BST? esperado: " + expected);
		boolean real = My_Code.solve(root);

		System.out.println("[Test] es BST? esperado: " + expected + "; real: " + real);
		assertEquals(expected, real);
		System.out.println("Completado!");
		System.out.println();
	}

	private IntBinaryTreeNode createLeft(int n) {
		IntBinaryTreeNode root = new IntBinaryTreeNode();
		root.label = n;
		root.left = createLeft(n - 1);
		return root;
	}

	private IntBinaryTreeNode createRight(int n) {
		IntBinaryTreeNode root = new IntBinaryTreeNode();
		root.label = n;
		root.right = createLeft(n + 1);
		return root;
	}

	private static void printInfo() {
		System.out.println("[INFO] " + System.getProperty("user.home"));
		System.out.println("[INFO] " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}
}
