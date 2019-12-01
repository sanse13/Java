package c1.pixelated_image;

import java.io.BufferedWriter;
import java.io.IOException;

import c.pixelated_image.BFS;

public class Student_Code {

	/**
	 * Escribe en el "printer" indicado el numero de objetos reconocido en una
	 * imagen y, para cada uno de ellos, de izquierda a derecha, y de arriba abajo,
	 * el area del rectangulo que los encierra (Ver enunciado en archivo adjunto.)
	 *
	 * @param imagen
	 *            un array 2D de enteros que representa la imagen referida.
	 *
	 *            Si imagen[fila][columna] es 1 el pixel es negro; si no, blanco.
	 *
	 * @param printer
	 *            el objeto a usar para escribir los resultados calculados.
	 * @throws IOException
	 *
	 * @Observaciones El problema se resolvera viendo la imagen como si fuera un
	 *                grafo no dirigido, con tantos nodos como pixeles.
	 */
	public static void solve(int[][] imagen, BufferedWriter printer) throws IOException {
		/*
		 * Ejemplos de uso de "printer" (comentar al resolver)
		 */
		// printer.write("Texto: ");
		// printer.write(String.valueOf(6));
		// printer.newLine();
		BFS bfs = new BFS(imagen);
		int objects = bfs.call();
		printer.write(String.valueOf(objects));
	}
}
