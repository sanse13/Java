package a.int_binary_search_tree;

/**
 * Los objetos de esta clase son conjuntos de enteros, representados por arboles binarios
 * de busqueda.
 *
 * En un conjunto de enteros ninguno de sus elementos puede estar repetido.
 *
 * Deben adaptarse las soluciones de Weiss - cap. 4 (clase): implementar ITERATIVAMENTE...
 * pero aprovechando la existencia del campo "parent" en los nodos!
 *
 * ATENCION a la adecuada actualizacion del campo "parent" de cada nodo!
 *
 * Pueden definirse los metodos auxiliares que se desee (publicos, privados, etc)
 */
public class IntBinarySearchTree {
	public IntBinaryNode root = null;

	public IntBinarySearchTree() {
		
	}

	
	public boolean contains(int x) {
		if (root == null)
			return false;
		else {
			IntBinaryNode res = findNode(root, x);
			return res != null;
		}
	}

	
	public static boolean esVacio(IntBinaryNode root) {
		return root == null;
	}
	
	
	/**
	 * si existe un nodo con esa x, devuelve true
	 * @param root
	 * @param x
	 * @return
	 */
	public static IntBinaryNode findNode(IntBinaryNode root, int x) {
		if (root == null)
			return null;
		if (root.element == x)
			return root;
		IntBinaryNode aux = root;
		while (aux != null && aux.element != x) {
			if (x < aux.element) 
				aux = aux.left;
			
			else 
				aux = aux.right;
		
		}
		return aux;		
	}
	
	
	public int findMax() {
		if (root.right == null)
			return root.element;
		//hay nodo derecho
		IntBinaryNode prev = root;
		IntBinaryNode current = root.right;
		while (current != null) {
			current = current.right;
			prev = prev.right;
		}
		return prev.element;
	}
	
	//el menor pero del subárbol derecho
	private int findMinRight(IntBinaryNode node) {

			IntBinaryNode prev = node.right;
			IntBinaryNode current = node.right.left;
			while (current != null) {
				current = current.left;
				prev = prev.left;
			}
			return prev.element;
	}

	/**
	 * Implementar para probar estilo "hacker-rank" (ejecutando App.)
	 *
	 * NO usar el ITERADOR de ejercicios anteriores o algo parecido (fraude de ley).
	 */
	public int inOrderNext(int x) {
		IntBinaryNode node = findNode(root, x);
		int valor = 0;
		if (node == null) return -1;
		else {
			if (node.right != null) {
				valor = findMinRight(node);
				return valor;
			}
			if (node.element < root.element) {
				if (node.left == null && node.right == null && node.parent.element > node.element)
					valor = node.parent.element;
				if (node.right != null)
					valor = node.right.element;
				if (node.left != null && node.right == null)
					valor = node.parent.element;
				if (node.right == null && node.parent.element < node.element) {
					while (node.element > node.parent.element)
						node = node.parent;
					valor = node.parent.element;
				}
			}
			if (node.element > root.element) {
				if (node.left == null && node.right == null && node.parent.element > node.element)
					valor = node.parent.element;
				if (node.right != null)
					valor = node.right.element;
				if (node.right == null && node.parent.element > node.element)
					valor = node.parent.element;
				if (node.parent.element < node.element) {
					while (node.parent != null && node.parent.element < node.element)
						node = node.parent;
					if (node.parent != null)
						valor = node.parent.element;
				}
			}
		}
		return valor;
	}

	/**
	 * NO usar el ITERADOR de ejercicios anteriores o algo parecido (fraude de ley).
	 */
	public IntBinaryNode inOrderNext(IntBinaryNode node) {

		int nodo = inOrderNext(node.element);
		return findNode(root, nodo);
	}

	public void insert(int x) {
		IntBinaryNode nuevo = new IntBinaryNode();
		nuevo.element = x;
		nuevo.left = nuevo.right = null;
		if (root == null) {
			root = nuevo;
		} else {
			IntBinaryNode current = root;
			while (current != null) {
				nuevo.parent = current;
				if (x < current.element) {
					current = current.left;
				} else if (x > current.element) {
					current = current.right;
				}
			}
			
			if (nuevo.element < nuevo.parent.element)
				nuevo.parent.left = nuevo;
			else
				nuevo.parent.right = nuevo;
			

	}
}

	public void remove(int x) {
		IntBinaryNode node = findNode(root, x);
		
		if (node == null)
			return;
		
		//casos para la raiz

			//si solo queda la raiz
			if (node == root && node.left == null && node.right == null) {
				node = null;
				return;
			}
			
			if (node == root && node.left != null && node.right == null)
				node = node.left;
			
			if (node.left != null && node.right == null) {
				node.parent.left = node.left;
			}
			
			
			if (node.right != null && node.parent != null) {
				int minimoDerecho = findMinRight(node);
				IntBinaryNode encontrado = findNode(root, minimoDerecho);
				if (node.left == null) {
					node.parent.right = node.right;
				} else {
					encontrado.parent.left = null;
					node.element = minimoDerecho;
				}
			}
		
		if (esHoja(node)) {
			if (node.parent.element > node.element)
				node.parent.left = null;
			if (node.parent.element < node.element)
				node.parent.right = null;
		}
	}
	
	public static boolean esHoja(IntBinaryNode node) {
		return node.left == null && node.right == null;
	}

	public static class IntBinaryNode {
		/*
		 * NO ANYADIR mas campos a un nodo
		 */

		public int element;

		public IntBinaryNode	left;
		public IntBinaryNode	right;

		public IntBinaryNode 	parent;

		public IntBinaryNode(int theElement, IntBinaryNode lt, IntBinaryNode rt) {
			element = theElement;
			left = lt;
			right = rt;
		}
		
		public IntBinaryNode() {
			
		}

		@Override
		public String toString() {
			return "IntBinaryNode [element=" + element + "; parent=" + (parent != null ? parent.element : "")
					+ "]";
		}
	}
}
