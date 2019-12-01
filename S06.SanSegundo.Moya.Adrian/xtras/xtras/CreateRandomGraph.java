package xtras;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class CreateRandomGraph {

	static final int	N	= 50;	/* cantidad de nodos */
	static final int	M	= 5;	/* cantidad minima de aristas de cada nodo */

	static final boolean ES_DIGRAPH = !true;/* !true: no dirigido */

	// Nombre del archivo de salida
	static final String OUTPUT_FILE_NAME = "random-" + ES_DIGRAPH + "-" + N + ".txt";

	static final Random random = new Random();

	public static void main(String[] args) throws IOException {
		File tmp = new File(OUTPUT_FILE_NAME);
		System.out.println("Archivo de salida: " + tmp.getAbsolutePath());

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));) {
			long t0 = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			Set<Integer>[] adjacents = new Set[N];
			for (int index = 0; index < N; index++) {
				adjacents[index] = new TreeSet<>();
			}
			int lines = 0;
			for (int node = 0; node < N; node++) {
				while (adjacents[node].size() < M) {
					int to = random.nextInt(N);
					if (node != to && !adjacents[node].contains(to)) {
						assert !adjacents[to].contains(node);
						adjacents[node].add(to);
						if (!ES_DIGRAPH) {
							adjacents[to].add(node);
						}
						lines++;
					}
				}
			}
			println(bufferedWriter, ES_DIGRAPH);
			println(bufferedWriter, N);
			println(bufferedWriter, lines);

			int edgeLines = 0;
			int countMenos = 0;
			int countMas = 0;
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int node = 0; node < N; node++) {
				Set<Integer> set = adjacents[node];
				for (Integer e : adjacents[node]) {
					if (ES_DIGRAPH || e > node) {
						println(bufferedWriter, node + " " + e);
						edgeLines++;
					}
				}
				if (set.size() < M) {
					countMenos++;
				}
				if (set.size() > M) {
					countMas++;
				}
				if (set.size() < min) {
					min = adjacents[node].size();
				}
				if (set.size() > max) {
					max = adjacents[node].size();
				}
			}
			long tf = System.currentTimeMillis();
			long elapsedTime = tf - t0;
			System.out.println("Elapsed time: " + elapsedTime + " millis.");
			System.out.println("Lines: " + lines + "; N*M=" + N * M);
			System.out.println("Edge Lines: " + edgeLines);
			System.out.println("Menos de " + M + ": " + countMenos);
			System.out.println("Mas de " + M + ": " + countMas);
			System.out.println("Minimo de aristas " + min);
			System.out.println("Maximo de aristas " + max);
		}
	}

	private static void println(BufferedWriter bufferedWriter, boolean x) throws IOException {
		println(bufferedWriter, String.valueOf(x));
	}

	private static void println(BufferedWriter bufferedWriter, int x) throws IOException {
		println(bufferedWriter, String.valueOf(x));
	}

	private static void println(BufferedWriter bufferedWriter, String x) throws IOException {
		bufferedWriter.write(x);
		bufferedWriter.newLine();
	}
}
