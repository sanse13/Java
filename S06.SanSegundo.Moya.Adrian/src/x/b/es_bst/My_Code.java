package x.b.es_bst;

import hr.base.IntBinaryTreeNode;
import hr.base.StructMinMaxEsBst;

class My_Code {

	/**
	 * Devuelve un StructMinMaxEsBst con los valores menor, mayor del arbol enraizado en r, asi como un valor
	 * logico true o false segun ese arbol sea "BST" (true) o no (false).
	 *
	 * @param r
	 *            el nodo raiz de un arbol binario.
	 *
	 * @return un StructMinMaxEsBst con los valores menor y mayor del arbol enraizado en r.
	 *
	 *         Si r == null, entonces el minimo es MAX_VALUE, el maximo es Integer.MIN_VALUE, y el arbol es
	 *         BST.
	 *
	 */
	public static StructMinMaxEsBst solve(IntBinaryTreeNode r) {
		if (r == null) { return new StructMinMaxEsBst(Integer.MAX_VALUE, Integer.MIN_VALUE, true); }
		StructMinMaxEsBst left = solve(r.left);
		StructMinMaxEsBst right = solve(r.right);

		int x = r.label;
		int min = Math.min(left.min, right.min);
		int max = Math.max(left.max, right.max);

		boolean ok = left.esBST && right.esBST & x > left.max & x < right.min;

		min = Math.min(x, min);
		max = Math.max(x, max);
		return new StructMinMaxEsBst(min, max, ok);
	}
}