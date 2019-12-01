package iterators.b.preorder;

import java.util.Iterator;
import java.util.NoSuchElementException;

import base.IntBinaryTreeNode;

/**
 * Iterators for PREORDER traversal of a tree.
 *
 * Una implementacion siguiendo las mismas pautas que al construir el iterador en inorden.
 *
 * Tambien seria posible adaptar la version recursiva del recorrido en preorden, simulando
 * la pila de la recursion.
 */
public class MyTreeIterator implements Iterator<IntBinaryTreeNode> {

	/*
	 * path to current tree node.
	 */
	PathNode current;

	public MyTreeIterator(IntBinaryTreeNode root) {
		if (root != null) {
			current = new PathNode(root);
		}
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public IntBinaryTreeNode next() {
		if (current == null) { throw new NoSuchElementException(); }

		assert current != null;
		IntBinaryTreeNode oldTreeNode = current.lastBinaryNode;

		if (oldTreeNode.left != null) {
			current = new PathNode(oldTreeNode.left, current);
		} else if (oldTreeNode.right != null) {
			current = new PathNode(oldTreeNode.right, current);
		} else {
			assert oldTreeNode.left == null && oldTreeNode.right == null;

			while (isRightSon(current)) {
				current = current.previous;
			}
			assert current != null && !isRightSon(current);

			IntBinaryTreeNode son = current.lastBinaryNode;
			current = current.previous;

			assert current == null || current.lastBinaryNode.left == son;
			if (current != null && current.lastBinaryNode.right != null) {
				current = new PathNode(current.lastBinaryNode.right, current);
			} else {
				current = null;
			}
		}
		return oldTreeNode;
	}

	private boolean isRightSon(PathNode pathNode) {
		boolean b = pathNode != null && pathNode.previous != null
				&& pathNode.lastBinaryNode == pathNode.previous.lastBinaryNode.right;
		return b;
	}
}

/**
 * Paths to nodes in the tree.
 */
class PathNode {
	IntBinaryTreeNode	lastBinaryNode;
	PathNode			previous;

	PathNode(IntBinaryTreeNode node) {
		lastBinaryNode = node;
	}

	PathNode(IntBinaryTreeNode node, PathNode previous) {
		lastBinaryNode = node;
		this.previous = previous;
	}

	@Override
	public String toString() {
		return "Path[" + lastBinaryNode.label + " " + previous + "]";
	}

	/**
	 * Internal method to find the path to the node with the smallest item in a subtree.
	 *
	 * @param t
	 *            path to the node that roots the subtree.
	 * @return the path to the node with the smallest item in a subtree.
	 */
	static PathNode path2Min(PathNode path) {
		assert path != null;
		if (path.lastBinaryNode.left == null) { return path; }
		return path2Min(new PathNode(path.lastBinaryNode.left, path));
	}
}
