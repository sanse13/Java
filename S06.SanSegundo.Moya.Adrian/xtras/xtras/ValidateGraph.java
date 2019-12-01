package xtras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import hr.base.IntGraph;
import hr.base.IntGraphs;

public class ValidateGraph {

	// Nombre del archivo de entrada
	static final String INPUT_FILE_NAME = "random-" + !true + "-" + "500000" + ".txt";

	public static void main(String[] args) throws IOException {
		File tmp = new File(INPUT_FILE_NAME);
		System.out.println("Archivo de salida: " + tmp.getAbsolutePath());

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(INPUT_FILE_NAME)));) {
			long t0 = System.currentTimeMillis();

			IntGraph g = IntGraphs.read(reader);

			long tf = System.currentTimeMillis();
			long elapsedTime = tf - t0;
			System.out.println("Elapsed time[read] " + elapsedTime + " millis.");

			long t0x = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			HashSet<Integer>[] set = new HashSet[g.nodes];
			for (int n : g.vertexIterator()) {
				set[n] = new HashSet<>();
				for (Integer e : g.edgesOf(n)) {
					if (e < 0 || e >= g.nodes) {
						String s = "Node: " + n + "; dest: " + e;
						throw new IndexOutOfBoundsException(s);
					}
					if (set[n].contains(e)) {
						String s = "Node: " + n + "; dest: " + e;
						throw new IllegalStateException(s);
					}
					set[n].add(e);
				}
			}
			if (!g.isDirectedGraph) {
				for (int n : g.vertexIterator()) {
					for (Integer e : g.edgesOf(n)) {
						if (!set[e].contains(n)) {
							String s = "Arista(" + n + ", " + e + ") Falta (" + e + ", " + n + ")";
							throw new IllegalStateException(s);
						}
					}
				}
			}
			long tfx = System.currentTimeMillis();
			long elapsedTimex = tfx - t0x;
			System.out.println("Elapsed time[validate]: " + elapsedTimex + " millis.");
		}
	}
}
