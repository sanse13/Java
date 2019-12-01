package d_equidivisions;

public class My_Code {

	/**
	 * @return true si la cuadricula representa una equidivision (Ver enunciado).
	 *
	 * @precondicion cuadricula es una matriz de n filas y n columnas.
	 *
	 *               Resolver mediante un recorrido de un grafo.
	 *
	 *               Considerar que la matriz es un grafo: las casillas son los nodos, y
	 *               hay una arista entre dos casillas si comparten un lado (no vale
	 *               tocarse en una esquina) y tienen el mismo valor.
	 *
	 *               La solucion debe tener un tiempo de ejecucion lineal en el numero de
	 *               nodos y aristas del grafo.
	 */
	public static boolean esEquidivision(int[][] cuadricula, int n) {
		DFS dfs = new DFS(cuadricula, n);
		boolean b = dfs.call();

		return b;
	}
}
