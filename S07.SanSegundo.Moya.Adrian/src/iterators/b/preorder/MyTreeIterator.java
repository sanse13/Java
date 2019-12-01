package iterators.b.preorder;

import java.util.*;

import hr.base.IntBinaryTreeNode;

/**
 * Iterators for PREORDER traversal of a tree.
 */
public class MyTreeIterator implements Iterator<IntBinaryTreeNode> {
	
	Stack<IntBinaryTreeNode> pila = new Stack<IntBinaryTreeNode>();
	
	public MyTreeIterator(IntBinaryTreeNode root) {
		if (root != null)
			pila.push(root);
	}

	@Override
	public boolean hasNext() {
		return !pila.isEmpty();
	}

	@Override
	public IntBinaryTreeNode next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		IntBinaryTreeNode res = pila.pop();
		//prmero right y luego left, o lo imprime en orden inverso
		if (res.right != null)
			pila.push(res.right);
		if (res.left != null)
			pila.push(res.left);
		
		return res;
	}
}

// Del aula!

/**
 * Paths to nodes in the tree.
 */
class PathNode {
	IntBinaryTreeNode lastBinaryNode;
	PathNode previous;

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
	 * Internal method to find the path to the node with the smallest item in a
	 * subtree.
	 *
	 * @param t
	 *            path to the node that roots the subtree.
	 * @return the path to the node with the smallest item in a subtree.
	 */
	static PathNode path2Min(PathNode path) {
		assert path != null;
		if (path.lastBinaryNode.left == null) {
			return path;
		}
		return path2Min(new PathNode(path.lastBinaryNode.left, path));
	}
}
