package test_junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import a.int_binary_search_tree.IntBinarySearchTree;
import a.int_binary_search_tree.IntBinarySearchTree.IntBinaryNode;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class a0_BST_OBLIGATORIO {
	static {
		printInfo();
	}

	@Test
	public void a_contains_insert_findMin() {
		int[] elems = { 1, 2, 3, 4, 5, 6, 7 };

		System.out.println("[Test: contains_insert_findMin] new IntBinarySearchTree()...");
		IntBinarySearchTree tree = new IntBinarySearchTree();

		for (int i = 0; i < elems.length; i++) {
			System.out.println("contains(" + elems[i] + ") esperado: false...");
			boolean b0 = tree.contains(elems[i]);
			assertFalse("contains(" + elems[i] + ")", b0);

			System.out.println("insert(" + elems[i] + ")...");
			tree.insert(elems[i]);

			System.out.println("contains(" + elems[i] + ") esperado: false...");
			boolean b1 = tree.contains(elems[i]);
			assertTrue(b1, "contains(" + elems[i] + ")");

			System.out.println("findMax() esperado: " + elems[i] + "...");
			int x = tree.findMax();
			assertEquals("findMax(" + elems[i] + ")", elems[i], x);
		}
		System.out.println();
		System.out.println("[Test: contains_insert_findMin] hecho!");
		System.out.println();
	}

	@Test
	public void b_parent() {
		int[] elems = { 1, 2, 3, 4, 5, 6, 7 };

		System.out.println("[Test: parent] new(); elems " + Arrays.toString(elems));
		IntBinarySearchTree tree = new IntBinarySearchTree();
		IntStream.rangeClosed(1, elems.length).forEach(tree::insert);

		chkParent(tree.root, null);
		System.out.println();
		System.out.println("[Test: parent] hecho");
		System.out.println();
	}

	static void chkParent(IntBinaryNode node, IntBinaryNode parent) {
		chkParent(node, parent, true);
	}

	static void chkParent(IntBinaryNode node, IntBinaryNode parent, boolean verbose) {
		if (node == null) {
			return;
		}
		String tmp = parent == null ? "NULL" : String.valueOf(parent.element);
		if (verbose) {
			System.out.println("parent(" + node.element + ") esperado: " + tmp + "...");
		}
		assertEquals("parent(" + node.element + ")", parent, node.parent);

		chkParent(node.left, node, verbose);
		chkParent(node.right, node, verbose);
	}

	private static void printInfo() {
		System.out.println("[INFO] " + System.getProperty("user.home"));
		System.out.println("[INFO] " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}
}
