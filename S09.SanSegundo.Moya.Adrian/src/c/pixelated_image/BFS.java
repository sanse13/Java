package c.pixelated_image;

import java.util.ArrayList;

import base.FIFOQueue;
import util.GridCoordinates;
import util.GridIterator;

/**
 * Una clase de objetos para contar el numero de objetos en una imagen y el area
 * del rectangulo que encierra a cada uno de ellos.
 * 
 * </br>
 * La imagen se establece en la constructora.
 * 
 * </br>
 * Observacion: es lo mismo que contar el numero de componentes conexas de un
 * grafo.
 *
 * </br>
 * Ejemplo de uso
 *
 * <pre>
 * public static void procesar(IntGraph g) {
 * 	BFS bfs = new BFS(g);
 * 	int componentesConexas = bfs.call();
 *
 * 	// resto...
 * }
 * </pre>
 */
public class BFS {
	
	public int[][] g;
	
	public ArrayList<Integer> areas = new ArrayList<>();

	private int rows;
	private int cols;
	private int componentesConexas;
	
	private boolean[][] marked ;
	private FIFOQueue<GridCoordinates> q = new FIFOQueue<>();
	
	public BFS(int[][] imagen) {
		rows = imagen.length;
		cols = imagen[0].length;
		marked = new boolean[rows][cols];
		g = imagen;
	}
	
	

	/**
	 * Calcula y devuelve el numero de objetos de la imagen;
	 */
	public int call() {
		/*
		 * SUGERENCIA: hacer un recorrido completo del grafo (es decir: lo dicho el
		 * Miercoles)
		 */
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (g[i][j] == 1 && !marked[i][j]) {
					GridCoordinates gr = new GridCoordinates(j, i);
					searchFrom(gr);
					componentesConexas++;
				}
			}
		}
		return componentesConexas;
	}
	
	
	
	private void searchFrom(GridCoordinates start) {


		marked[start.row][start.column] = true;
		q.addTail(start);
		
		int minRow = start.row;
		int maxRow = start.row;
		int minCol = start.column;
		int maxCol = start.column;

		while (!q.isEmpty()) {
			GridCoordinates orig = q.rmvHead();

			GridIterator itr = new GridIterator(rows, cols, orig);
			while (itr.hasNext()) {
				GridCoordinates dest = itr.next();
				int r = dest.row;
				int c = dest.column;
				
				

					if (!marked[r][c] && g[r][c] == 1) {
						q.addTail(dest);
						marked[r][c] = true;
						if (r < minRow)
							minRow = r;
						if (r > maxRow)
							maxRow = r;
						if (c < minCol)
							minCol = c;
						if (c > maxCol)
							maxCol = c;
					
				} else {
					/*
					 * El nodo "dest" ya ha sido visitado en alguna iteracion anterior
					 */
				}
			}
		}

		int base = (maxCol - minCol)+1;
		System.out.println("base "+base);
		int altura = (maxRow - minRow)+1;
		System.out.println("altura "+altura);
		int area = base*altura;
		System.out.println(area);
		areas.add(area);
	}


	/**
	 * @return un array con el area de cada uno de los objetos de la imagen.
	 * 
	 * @PRECONDICION call ya ha sido ejecutado
	 * 
	 * @OBSERVACION esto debe implementarse en tiempo constante. Es decir, que este
	 *              objeto ya posee esa informacion.
	 */
	public int[] getAreas() {
		int[] res = new int[areas.size()];
		for (int i = 0; i < areas.size(); i++) {
			res[i] = areas.get(i);
		}
		return res;
	}
}
