package a.BinarySearchTrees.weiss;

public class BinarySearchTree_Comparable<T extends Comparable<? super T>> {
	// T: Weiss: AnyType [Any Reference Type]

	private BinaryNode<T> root;

	public BinarySearchTree_Comparable() {
	}

	public boolean contains(T x) {
		return contains(x, root);
	}

	public T findMax() {
		if (isEmpty()) { throw new IllegalStateException(); }
		return findMax(root).element;
	}

	public T findMin() {
		if (isEmpty()) { throw new IllegalStateException(); }
		return findMin(root).element;
	}

	public void insert(T x) {
		root = insert(x, root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void makeEmpty() {
		root = null;
	}

	public void remove(T x) {
		root = remove(x, root);
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
	private boolean contains(T x, BinaryNode<T> t) {
		/* Figure 4.18 */
		if (t == null) { return false; }
		int compareResult = x.compareTo(t.element);
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
	private BinaryNode<T> findMax(BinaryNode<T> t) {
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
	private BinaryNode<T> findMin(BinaryNode<T> t) {
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
	private BinaryNode<T> insert(T x, BinaryNode<T> t) {
		/* Figure 4.22 */
		if (t == null) { return new BinaryNode<>(x, null, null); }
		int compareResult = x.compareTo(t.element);
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
	private BinaryNode<T> remove(T x, BinaryNode<T> t) {
		/* Figure 4.25 */
		if (t == null) { return t; // Item not found; do nothing
		}
		int compareResult = x.compareTo(t.element);
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

	private static class BinaryNode<NodeClazz> {
		/* Figure 4.16 */

		NodeClazz element;

		BinaryNode<NodeClazz>	left;
		BinaryNode<NodeClazz>	right;

		BinaryNode(NodeClazz theElement, BinaryNode<NodeClazz> lt, BinaryNode<NodeClazz> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}
	}
}
