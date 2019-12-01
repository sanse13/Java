package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IntBinaryTrees {
	private static final int DEFAULT_PRINT_TRUNCATED_AT = 4;

	private IntBinaryTrees() {
	}

	public static void main(String[] args) throws IOException {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
		IntBinaryTreeNode root = toTree(array);

		showPreOrder("Recorrido en preorden", root);
	}

	public static void printPreOrder(IntBinaryTreeNode root, String margin,
			BufferedWriter bufferedWriter) throws IOException {
		printPreOrder(root, margin, "", bufferedWriter);
	}

	public static void printPreOrder(IntBinaryTreeNode root, String margin, String mark,
			BufferedWriter bufferedWriter) throws IOException {
		if (root == null) { return; }
		bufferedWriter.write(margin);
		bufferedWriter.write(mark);
		bufferedWriter.write(String.valueOf(root.label));
		bufferedWriter.newLine();
		printPreOrder(root.left, margin + margin, "[L] ", bufferedWriter);
		printPreOrder(root.right, margin + margin, "[R] ", bufferedWriter);
	}

	public static IntBinaryTreeNode read(BufferedReader bufferedReader)
			throws IOException {
		int[] array = IntArrays.read(bufferedReader);
		IntBinaryTreeNode root = toTree(array);
		return root;
	}

	public static void showPreOrder(IntBinaryTreeNode root, String margin)
			throws IOException {
		showPreOrder(root, margin, "");
	}

	public static void showPreOrder(IntBinaryTreeNode root, String margin,
			String rootMark) throws IOException {
		showPreOrder(root, margin, rootMark, DEFAULT_PRINT_TRUNCATED_AT, 0);
	}

	public static void showPreOrder(String header, IntBinaryTreeNode root)
			throws IOException {
		System.out.println(header);
		showPreOrder(root, "    ");
	}

	/**
	 * Devuelve un array con la representacion por niveles del arbol.
	 */
	public static int[] toArray(IntBinaryTreeNode root) {
		ArrayList<Integer> array = new ArrayList<>();
		toArray(root, array, 0);
		int[] tmp = new int[array.size()];
		for (int index = 0; index < array.size(); index++) {
			if (array.get(index) == null) {
				tmp[index] = -1;
			} else {
				tmp[index] = array.get(index);
			}
		}
		return tmp;
	}

	/**
	 * Devuelve el arbol de nodos representado a.
	 */
	public static IntBinaryTreeNode toTree(int[] a) {
		return toTree(a, 0);
	}

	/**
	 * Amplia el array con null hasta que su tamanyo es n.
	 */
	private static void ensureSize(ArrayList<Integer> array, int n) {
		for (int i = array.size(); i <= n; i++) {
			array.add(null);
		}
	}

	private static void showPreOrder(IntBinaryTreeNode root, String margin,
			String rootMark, int truncAtLevel, int level) throws IOException {
		if (root == null) { return; }
		for (int i = 0; i < level; i++) {
			System.out.print(margin);
		}
		System.out.print(rootMark);
		System.out.print(String.valueOf(root.label));
		if (truncAtLevel == 0) {
			System.out.println(" *** Truncado ***");
		} else {
			System.out.println();
			showPreOrder(root.left, margin, "[H. Izdo] ", truncAtLevel - 1, level + 1);
			showPreOrder(root.right, margin, "[H. Dcho] ", truncAtLevel - 1, level + 1);
		}
	}

	private static void toArray(IntBinaryTreeNode root, ArrayList<Integer> array,
			int rootIndex) {
		if (root == null) { return; }
		ensureSize(array, rootIndex);
		array.set(rootIndex, root.label);
		toArray(root.left, array, rootIndex * 2 + 1);
		toArray(root.right, array, rootIndex * 2 + 2);
	}

	private static IntBinaryTreeNode toTree(int[] a, int from) {
		if (from >= a.length || a[from] == -1) { return null; }
		int v = a[from];
		IntBinaryTreeNode l = toTree(a, 2 * from + 1);
		IntBinaryTreeNode r = toTree(a, 2 * from + 2);
		IntBinaryTreeNode root = new IntBinaryTreeNode();
		root.label = v;
		root.left = l;
		root.right = r;
		return root;
	}
}
