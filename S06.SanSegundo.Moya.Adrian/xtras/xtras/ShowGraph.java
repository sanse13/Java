package xtras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import hr.base.IntGraph;
import hr.base.IntGraphs;

public class ShowGraph {

	// Nombre del archivo de entrada
	static final String INPUT_FILE_NAME = "random-" + !true + "-" + "50" + ".txt";

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

			IntGraphs.draw(g);
		}
	}
}
