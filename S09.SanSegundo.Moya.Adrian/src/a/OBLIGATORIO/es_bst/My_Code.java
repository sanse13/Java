package a.OBLIGATORIO.es_bst;

import base.IntBinaryTreeNode;
import util.StructMinMaxEsBst;



class My_Code {

	/**
	 * Un arbol binario se dice <i>bst</i> si:
	 *
	 * <pre>
	 * a) es vacio o todos los enteros de su hijo izquierdo son menores que el de la raiz y
	 * los enteros de su hijo derecho son mayores que el de la raiz
	 *
	 * b) todos sus subarboles son <i>bst</i>
	 * </pre>
	 *
	 * @param r
	 *            el nodo raiz de un arbol binario.
	 *
	 * @return true si el subarbol enraizado en r es bst; false si no lo es.
	 */
	public static boolean solve(IntBinaryTreeNode root) {
		
		if (root == null)
			return true;	
		
		StructMinMaxEsBst es = solveAux(root);
		return  es.esBST;

	}
	
	
	public static StructMinMaxEsBst solveAux(IntBinaryTreeNode root) {
		if (root == null) return new StructMinMaxEsBst(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
		
		StructMinMaxEsBst left = solveAux(root.left);
		StructMinMaxEsBst right = solveAux(root.right);
		
		int min = Math.min(left.min, right.min);
		int max = Math.max(left.max, right.max);
		
		boolean ok = left.esBST && right.esBST & root.label > left.max & root.label < right.min;
		
		min = Math.min(root.label, min);
		max = Math.max(root.label, max);
		
		return new StructMinMaxEsBst(min, max, ok);
	}
	
}

