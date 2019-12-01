package x_ejemplo_por__niveles;

import java.io.BufferedWriter;
import java.io.IOException;

import base.FIFOQueue;
import base.IntBinaryTreeNode;

class My_Code {

	/**
	 * Escribe en "printer" una linea por cada nivel del arbol, del primero al ultimo.
	 *
	 * Por cada nivel se escribe: el indice del nivel y los valores de los nodos que estan
	 * en ese nivel.
	 *
	 * @throws IOException
	 */
	public static void solve(IntBinaryTreeNode r, BufferedWriter writer) throws IOException {
		/*
		 * Solucion
		 *
		 * Una variacion del recorrido en anchura de un grafo: no hay necesidad de marcar nodos
		 * visitados.
		 *
		 * Se procesan los nodos en el nivel cero, uno, dos, etc.
		 */
		if (r != null) {
			int level = 0;

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
				writer.write(String.valueOf(level));
				writer.write(' ');

				/*
				 * Informar de los nodos en el nivel en curso y
				 * copiar sus hijos en nextLevel.
				 */
				while (!queue.isEmpty()) {
					IntBinaryTreeNode node = queue.rmv();

					if (node.left != null) {
						nextLevel.add(node.left);
					}
					if (node.right != null) {
						nextLevel.add(node.right);
					}

					writer.write(String.valueOf(node.label));
					if (!queue.isEmpty()) {
						writer.write(' ');
					}
				}
				writer.newLine();

				queue = nextLevel;
				nextLevel = new FIFOQueue<>();

				level = level + 1;
			} while (!queue.isEmpty());
		}
	}
}