package test_junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import a.int_binary_search_tree.IntBinarySearchTree;
import a.int_binary_search_tree.IntBinarySearchTree.IntBinaryNode;
import util.IntArrays;
import util.IntPermutationsIterator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class a1_BST_resto {
	static {
		printInfo();
	}

	@Test
	public void a00_remove() {
		int[] elems = { 1, 2, 3, 4, 5, 6, 7 };
		int[] items = { -100, 1, 2, 3, 4, 5, 6, 7, 100 };

		chkRemove(elems, items);
	}

	@Test
	public void aXX_remove() {
		int n = 7;
		int[] elems = IntArrays.ofRange(1, n);
		int[] items = IntArrays.ofRange(0, n + 1);
		new IntPermutationsIterator(elems).forEachRemaining(p -> chkRemove(p, items));
	}

	@Test
	public void b00_inOrderNext() {
		int[] elems = { 1, 2, 3, 4, 5, 6, 7 };
		int[] items = { 1, 2, 3, 4, 5, 6, 7 };

		chkInorderNext(elems, items);
	}

	@Test
	public void bXX_inOrderNext() {
		int n = 7;
		int[] elems = IntArrays.ofRange(1, n);
		int[] items = IntArrays.ofRange(1, n);
		new IntPermutationsIterator(elems).forEachRemaining(p -> chkInorderNext(p, items));
	}

	private void chkInorderNext(int[] elems, int... nodeIDs) {
		System.out.println("[Test: chkInorderNext] new(); elems " + Arrays.toString(elems));
		IntBinarySearchTree tree = new IntBinarySearchTree();
		IntArrays.asIterable(elems).forEach(tree::insert);

		for (int i = 0; i < nodeIDs.length; i++) {
			IntBinaryNode node = findNode(nodeIDs[i], tree.root);
			if (node != null) {
				System.out.println("inOrderNext(" + nodeIDs[i] + ")...");
				IntBinaryNode expectedNext = chkInOrderNext(node, tree);
				IntBinaryNode realNext = tree.inOrderNext(node);

				assertSame(expectedNext, realNext, "inOrderNext(" + nodeIDs[i] + ")");
			}
		}

		System.out.println();
		System.out.println("[Test: inOrderNext] hecho");
		System.out.println();
	}

	private IntBinaryNode chkInOrderNext(IntBinaryNode node, IntBinarySearchTree tree) {
		assertNotNull(node);
		MyTreeIterator it = new MyTreeIterator(tree);
		while (it.hasNext()) {
			IntBinaryNode tmp = it.next();
			if (tmp == node) {
				if (it.hasNext()) {
					IntBinaryNode rs = it.next();
					return rs;
				}
			}
		}
		return null;
	}

	private void chkRemove(int[] elems, int... remove) {
		System.out.println("[Test: remove] new(); elems " + Arrays.toString(elems));
		IntBinarySearchTree tree = new IntBinarySearchTree();
		IntArrays.asIterable(elems).forEach(tree::insert);

		for (int i = 0; i < remove.length; i++) {
			System.out.println("remove(" + remove[i] + ")...");
			tree.remove(remove[i]);

			System.out.println("contains(" + remove[i] + ") == false?...");
			boolean b = tree.contains(remove[i]);
			assertFalse(b, "contains(" + remove[i] + ")");

			System.out.println("parent?...");
			a0_BST_OBLIGATORIO.chkParent(tree.root, null, false);
		}

		System.out.println();
		System.out.println("[Test: remove] elems " + Arrays.toString(elems) + " hecho!");
		System.out.println();
	}

	private int compare(int x, int y) {
		return x - y;
	}

	private IntBinaryNode findNode(int x, IntBinaryNode t) {
		if (t == null) { return null; }
		int compareResult = compare(x, t.element);
		if (compareResult < 0) {
			return findNode(x, t.left);
		} else if (compareResult > 0) {
			return findNode(x, t.right);
		} else {
			return t; // Match
		}
	}

	private static void printInfo() {
		System.out.println("[INFO] " + System.getProperty("user.home"));
		System.out.println("[INFO] " + System.getProperty("user.dir"));
		System.out.println("[INFO] " + LocalDateTime.now());
		System.out.println();
	}
}

/**
 * Iterators for inorder traversal of a tree.
 */
class MyTreeIterator implements Iterator<IntBinaryNode> {
	/*
	 * path to current tree node.
	 */
	PathNode current;

	public MyTreeIterator(IntBinarySearchTree tree) {
		if (tree.root != null) {
			current = PathNode.path2Min(new PathNode(tree.root));
		}
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public IntBinaryNode next() {
		if (!hasNext()) { throw new NoSuchElementException(); }

		assert current != null;
		IntBinaryNode oldCurrentNode = current.lastBinaryNode;

		if (oldCurrentNode.right != null) {
			PathNode right = new PathNode(oldCurrentNode.right, current);
			current = PathNode.path2Min(right);
		} else {
			while (current != null && current.previous != null
					&& current.previous.lastBinaryNode.right == current.lastBinaryNode) {
				current = current.previous;
			}
			assert current == null || current.previous == null
					|| current.previous.lastBinaryNode.left == current.lastBinaryNode;
			if (current != null) {
				current = current.previous;
			}
		}
		return oldCurrentNode;
	}
}

/**
 * Paths to nodes in the tree.
 */
class PathNode {
	IntBinaryNode	lastBinaryNode;
	PathNode		previous;

	PathNode(IntBinaryNode node) {
		lastBinaryNode = node;
	}

	PathNode(IntBinaryNode node, PathNode previous) {
		lastBinaryNode = node;
		this.previous = previous;
	}

	@Override
	public String toString() {
		return "Path[" + lastBinaryNode.element + " " + previous + "]";
	}

	static PathNode path2Min(PathNode path) {
		assert path != null;
		if (path.lastBinaryNode.left == null) { return path; }
		return path2Min(new PathNode(path.lastBinaryNode.left, path));
	}
}
