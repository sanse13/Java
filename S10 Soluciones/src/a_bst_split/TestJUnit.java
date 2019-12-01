package a_bst_split;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
		chk("simple", new Integer[] { 15, 7, 23 });
	}

	@Test
	public void a1_ejemplo() {
		chk("ejemplo", new Integer[] { 15, 7, 23, 2, 11, 18, 24, 1, 4, 10, 12, 16, 20 });
	}

	void chk(String id, Integer[] binaryArray) {
		System.out.println("[CASO DE PRUEBA: " + id + "]");
		IntBinaryTreeNode root = IntBinaryTrees.toIntTree(BinaryTrees.buildFromBinaryArray(binaryArray));
		IntBinaryTrees.showPreOrder("BST en pre-orden", root);

		Integer[] values = Arrays.stream(binaryArray).filter(o -> o != null).sorted()
				.toArray(n -> new Integer[n]);
		Arrays.stream(values).forEach(x -> chkSplit(binaryArray, x, values));
	}

	private void chkSplit(Integer[] binaryArray, int x, Integer[] values) {
		System.out.println("[Test] split(X = " + x + ")...");

		IntBinaryTreeNode root = IntBinaryTrees.toIntTree(BinaryTrees.buildFromBinaryArray(binaryArray));
		Set<IntBinaryTreeNode> nodesOrig = nodesOf(root);
		IntBinaryTreeNode nodeX = find(root, x);

		SplitResult split = My_Code.split(root, x);

		Integer[] lesserExpected = Arrays.stream(values).filter(o -> o < x).sorted()
				.toArray(n -> new Integer[n]);
		System.out.println("Menores que x=" + x + "; esperados: " + Arrays.toString(lesserExpected));

		IntBinaryTrees.showPreOrder("Arbol con menores en pre-orden", split.lesser);
		Object[] lesserReal = BinaryTrees.toArrayInOrder(IntBinaryTrees.toIntegerTree(split.lesser));

		System.out.println("Menores que x=" + x + "; reales : " + Arrays.toString(lesserReal));
		assertArrayEquals(lesserExpected, lesserReal, "Error menores");

		System.out.println();

		Integer[] greaterExpected = Arrays.stream(values).filter(o -> o > x).sorted()
				.toArray(n -> new Integer[n]);
		System.out.println("Mayores que x=" + x + "; esperados: " + Arrays.toString(greaterExpected));

		IntBinaryTrees.showPreOrder("Arbol con mayores en pre-orden", split.greater);
		Object[] greaterReal = BinaryTrees.toArrayInOrder(IntBinaryTrees.toIntegerTree(split.greater));

		System.out.println("Mayores que x=" + x + "; reales: " + Arrays.toString(greaterReal));
		assertArrayEquals(greaterExpected, greaterReal, "Error mayores");

		System.out.println("Nodos reorganizados? ");
		nodeX.left = split.lesser;
		nodeX.right = split.greater;
		IntBinaryTrees.showPreOrder("Arbol con X como raiz en pre-orden", nodeX);

		Set<IntBinaryTreeNode> nodesNow = nodesOf(nodeX);
		nodesOrig.forEach(node -> assertTrue(nodesNow.contains(node), " Nodos perdidos"));
		nodesNow.forEach(node -> assertTrue(nodesOrig.contains(node), " Nodos nuevos"));

		System.out.println("Completado!");
		System.out.println();
	}

	private IntBinaryTreeNode find(IntBinaryTreeNode root, int x) {
		if (root == null || root.label == x) {
			return root;
		}
		if (root.label < x) {
			return find(root.right, x);
		}
		assert root.label > x;
		return find(root.left, x);
	}

	private Set<IntBinaryTreeNode> nodesOf(IntBinaryTreeNode root) {
		if (root == null) {
			return new HashSet<>();
		}
		Set<IntBinaryTreeNode> left = nodesOf(root.left);
		Set<IntBinaryTreeNode> right = nodesOf(root.right);
		left.add(root);
		left.addAll(right);
		return left;
	}
}
