package a_bst_split;

import base.IntBinaryTreeNode;

class My_Code {

	/**
	 * Dado un BST y un entero x reorganiza los nodos del arbol para formar con ellos dos BST's: uno
	 * con los valores del arbol menores que x, y otro con los mayores.
	 *
	 * @param r
	 *            el nodo raiz de un BST
	 *
	 * @param x
	 *            uno de los enteros del arbol
	 * 
	 * @return una tupla con las raices de los arboles referidos.
	 * 
	 *         ES IMPRESCINDIBLE REORGANIZAR LOS NODOS DEL ARBOL ORIGINAL.
	 * 
	 *         Es decir, cambiar los valores de sus campos "left"/"right" como corresponda. Y solo
	 *         esos campos.
	 */
	public static SplitResult split(IntBinaryTreeNode r, int x) {
		/*
		 * La solucion recursiva es casi una traduccion directa del enunciado.
		 */
		if (r == null) {
			/*
			 * Trivial
			 */
			return new SplitResult(null, null);
		}
		if (r.label == x) {
			/*
			 * Obvio
			 */
			return new SplitResult(r.left, r.right);
		}
		if (x < r.label) {
			/*
			 * r.label y todo su hijo derecho son > x...
			 */

			// falta reorganizar el hijo izquierdo: es donde estara x
			SplitResult rs = split(r.left, x);
			// lo que hay en rs.lesser es todo lo que es < x
			// todo lo que hay en rs.greater es > x

			r.left = rs.greater; // ahora todo lo que es > x esta en r

			return new SplitResult(rs.lesser, r);
		} else {
			assert r.label < x;
			/*
			 * r.label y todo su hijo izquierdo son < x...
			 */

			// falta reorganizar el hijo derecho: es donde estara x
			SplitResult rs = split(r.right, x);
			// lo que hay en rs.greater es todo lo que es > x
			// todo lo que hay en rs.lesser es < x

			r.right = rs.lesser; // ahora todo lo que es < x esta en r
			return new SplitResult(r, rs.greater);
		}
	}
}