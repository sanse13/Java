package a.BinarySearchTrees.weiss;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntBinarySearchTree implements Iterable<Integer> {
	private IntBinaryNode root;

	public IntBinarySearchTree() {
	}

	public boolean contains(int x) {
		return contains(x, root);
	}

	public int findMax() {
		if (isEmpty()) { throw new IllegalStateException(); }
		return findMax(root).element;
	}

	public int findMin() {
		if (isEmpty()) { throw new IllegalStateException(); }
		return findMin(root).element;
	}

	public void insert(int x) {
		root = insert(x, root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Iterator<Integer> iterator() {
		Iterator<Integer> iterator = new MyTreeIterator(this);
		return iterator;
	}

	public void makeEmpty() {
		root = null;
	}

	public void remove(int x) {
		root = remove(x, root);
	}

	public int size() {
		return size(root);
	}

	private int compare(int x, int y) {
		return x - y;
	}

	/**
	 * Internal method to find an item in a subtree.
	 *
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the subtree.
	 * @return true if the item is found; false otherwise.
	 */
	private boolean contains(int x, IntBinaryNode t) {
		/* Figure 4.18 */
		if (t == null) { return false; }
		int compareResult = compare(x, t.element);
		if (compareResult < 0) {
			return contains(x, t.left);
		} else if (compareResult > 0) {
			return contains(x, t.right);
		} else {
			return true; // Match
		}
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 *
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private IntBinaryNode findMax(IntBinaryNode t) {
		/* Figure 4.20 */
		if (t != null) {
			while (t.right != null) {
				t = t.right;
			}
		}
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 *
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private IntBinaryNode findMin(IntBinaryNode t) {
		/* Figure 4.20 */
		if (t == null) {
			return null;
		} else if (t.left == null) { return t; }
		return findMin(t.left);
	}

	/**
	 * Internal method to insert into a subtree.
	 *
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private IntBinaryNode insert(int x, IntBinaryNode t) {
		/* Figure 4.22 */
		if (t == null) { return new IntBinaryNode(x, null, null); }
		int compareResult = compare(x, t.element);
		if (compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {
			// Duplicate; do nothing
		}
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 *
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private IntBinaryNode remove(int x, IntBinaryNode t) {
		/* Figure 4.25 */
		if (t == null) { return t; // Item not found; do nothing
		}
		int compareResult = compare(x, t.element);
		if (compareResult < 0) {
			t.left = remove(x, t.left);
		} else if (compareResult > 0) {
			t.right = remove(x, t.right);
		} else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else {
			t = t.left != null ? t.left : t.right;
		}
		return t;
	}

	// TODO cambiar implementacion para asegurar tiempo en O(1)
	private int size(IntBinaryNode t) {
		if (t == null) { return 0; }
		return size(t.left) + size(t.right) + 1;
	}

	private static class IntBinaryNode {
		/* Figure 4.16 */

		int element;

		IntBinaryNode	left;
		IntBinaryNode	right;

		IntBinaryNode(int theElement, IntBinaryNode lt, IntBinaryNode rt) {
			element = theElement;
			left = lt;
			right = rt;
		}
	}

	/**
	 * Iterators for inorder traversal of a tree.
	 */
	private static class MyTreeIterator implements Iterator<Integer> {
		/*
		 * path to current tree node.
		 */
		PathNode current;

		public MyTreeIterator(IntBinarySearchTree tree) {
			if (!tree.isEmpty()) {
				current = PathNode.path2Min(new PathNode(tree.root));
			}
		}

		@Override
		public boolean hasNext() {
			System.out.println("TreeIterator.hasNext@" + current);
			return current != null;
		}

		@Override
		public Integer next() {
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
			return oldCurrentNode.element;
		}
	}

	/**
	 * Paths to nodes in the tree.
	 */
	private static class PathNode {
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

		/**
		 * Internal method to find the path to the node with the smallest item in a
		 * subtree.
		 *
		 * @param t
		 *            path to the node that roots the subtree.
		 * @return the path to the node with the smallest item in a subtree.
		 */
		static private PathNode path2Min(PathNode path) {
			assert path != null;
			if (path.lastBinaryNode.left == null) { return path; }
			return path2Min(new PathNode(path.lastBinaryNode.left, path));
		}
	}
}