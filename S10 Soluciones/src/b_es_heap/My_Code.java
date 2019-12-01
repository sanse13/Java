package b_es_heap;

import base.FIFOQueue;
import base.IntBinaryTreeNode;

class My_Code {

	/**
	 * @return true si el arbol enraizado en r es "monton"; false, si no lo es.
	 *
	 *         VER DEFINICION DE MONTON EN ENUNCIADO.
	 *
	 *         Todos los niveles deben estar completos, salvo quizas el ultimo.
	 *
	 *         En el ultimo nivel los nodos estan agrupados a la izquierda.
	 */
	public static boolean isHeap(IntBinaryTreeNode r) {
		/*
		 * Solucion
		 *
		 * Una variacion del recorrido en anchura de un grafo: no hay necesidad de marcar nodos
		 * visitados.
		 *
		 * Se procesan los nodos en el nivel cero, uno, dos, etc: ver "x_ejemplo_por_niveles"
		 * 
		 * Lo mas importante:
		 * 
		 * 1) Todos los niveles deben estar completos, salvo quizas el ultimo.
		 * 
		 * 2) Todas las hojas deben estar en el nivel ultimo o anteultimo.
		 * 
		 * 3) Si hay hojas en un nivel, el siguiente solo puede tener hojas.
		 */
		if (r == null) {
			return true;
		}

		/*
		 * Cola con los nodos del nivel en curso
		 */
		FIFOQueue<IntBinaryTreeNode> queue = new FIFOQueue<>();

		/*
		 * Cola con los nodos del nivel siguiente.
		 */
		FIFOQueue<IntBinaryTreeNode> nextLevel = new FIFOQueue<>();

		queue.add(r);

		do {
			/*
			 * Los nodos en la cola son los nodos del nivel en curso
			 * 
			 * El proceso se repite de nivel en nivel: se detiene en el primer nivel "no-monton"
			 */
			assert !queue.isEmpty();

			/*
			 * Copiar en nextLevel los hijos de los nodos en la cola, hasta el primero que no tenga
			 * dos hijos (excluido ese). Y comprobar que cada nodo es menor que sus hijos.
			 */
			boolean isHeap = copyNextLevelNodes(queue, nextLevel);
			if (!isHeap) {
				return false;
			}
			if (queue.isEmpty()) {
				/*
				 * todos los nodos de la cola tenian dos hijos.
				 */
				assert !nextLevel.isEmpty();
				queue = nextLevel;
				nextLevel = new FIFOQueue<>();
			} else {
				/*
				 * el primero de la cola tiene un hijo: debe ser izquiero...
				 */
				IntBinaryTreeNode next = queue.rmv();
				assert next.left == null || next.right == null;

				if (next.left != null && next.label > next.left.label || next.right != null) {
					return false;
				}

				/*
				 * ...Y los demas deben ser hojas...
				 */
				while (!queue.isEmpty()) {
					IntBinaryTreeNode node = queue.rmv();
					if (node.left != null || node.right != null) {
						return false;
					}
				}

				/*
				 * ...Y en el siguiente nivel solo puede haber hojas
				 */
				boolean rs = !hasInternalNodes(nextLevel);
				return rs;
			}
		} while (true);
	}

	private static boolean copyNextLevelNodes(FIFOQueue<IntBinaryTreeNode> queue,
			FIFOQueue<IntBinaryTreeNode> nextLevel) {
		while (!queue.isEmpty() && hasTwoSons(queue.peek())) {
			/*
			 * peek: devuelve el elemento que saldra de la cola, pero no lo quita
			 */
			IntBinaryTreeNode node = queue.rmv();
			if (node.label <= node.left.label && node.label <= node.right.label) {
				nextLevel.add(node.left);
				nextLevel.add(node.right);
			} else {
				return false;
			}
		}
		return true;
	}

	private static boolean hasInternalNodes(FIFOQueue<IntBinaryTreeNode> queue) {
		while (!queue.isEmpty()) {
			IntBinaryTreeNode node = queue.rmv();
			if (node.left != null || node.right != null) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasTwoSons(IntBinaryTreeNode node) {
		return node.left != null && node.right != null;
	}
}