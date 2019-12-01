package x.a.eval_min_max;

import hr.base.IntBinaryTreeNode;
import hr.base.StructMinMax;

class My_Code {

	/**
	 * Devuelve un StructMinMax con los valores menor y mayor del arbol enraizado en r.
	 *
	 * @param r
	 *            el nodo raiz de un arbol binario.
	 *
	 * @return un StructMinMax con los valores menor y mayor del arbol enraizado en r.
	 *
	 *         Si r == null, entonces el minimo es MAX_VALUE y el maximo es Integer.MIN_VALUE.
	 *
	 *         ATENCION! Debe considerarse inaceptable cualquier solucion que haga mas de un recorrido del
	 *         arbol.
	 *
	 *         Es decir: no vale calcular primero el minimo y despues el maximo!
	 */
	public static StructMinMax solve(IntBinaryTreeNode r) {
		if (r == null) return new StructMinMax(Integer.MAX_VALUE, Integer.MIN_VALUE);
		
		StructMinMax left = solve(r.left);
		StructMinMax right = solve(r.right);
		
		int min = Math.min(r.label, Math.min(left.min, right.min));
		int max = Math.max(r.label, Math.max(left.max, right.max));
		
		return new StructMinMax(min, max);
	}
}