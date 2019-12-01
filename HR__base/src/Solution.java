

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static final String INPUT_FILE_NAME = null; // (HR)
	// static final String INPUT_FILE_NAME = "INPUT.txt"; // (Archivo local)

	/*
	 * Si al ejecutar en Eclipse, se quiere leer de un archivo de entrada,
	 * comentar la linea (HR), y descomentar la linea (Archivo local)
	 */

	static final String OUTPUT_FILE_NAME = "OUTPUT.txt";
	// Nombre del archivo de salida cuando se ejecuta en Eclipse

	public static void main(String[] args) throws IOException {
		String output_path = System.getenv("OUTPUT_PATH");
		if (output_path == null) {
			output_path = OUTPUT_FILE_NAME;
			File tmp = new File(output_path);
			System.out.println("Archivo de salida: " + tmp.getAbsolutePath());
		}
		if (INPUT_FILE_NAME != null) {
			File inputFile = new File(INPUT_FILE_NAME);
			System.setIn(new FileInputStream(inputFile));
		} else {
			System.err.println("Inicio...");
		}

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output_path));

		My_Code.solve();

		bufferedReader.close();
		bufferedWriter.close();
	}

}

class My_Code {

	public static void solve() {
		// ESCRIBA AQUI SU CODIGO
	}

}