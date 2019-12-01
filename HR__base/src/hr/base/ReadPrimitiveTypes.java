package hr.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class ReadPrimitiveTypes {

	public static void main(String[] args) throws IOException {
		System.out.println("...");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = readInt(reader);
		boolean b = readBoolean(reader);
		System.out.println("n:" + n + "; b:" + b);
		reader.close();
	}

	public static boolean readBoolean(BufferedReader reader) throws IOException {
		String line = readLine(reader, "boolean");
		boolean n = Boolean.parseBoolean(line);
		return n;
	}

	public static int readInt(BufferedReader reader) throws IOException {
		String line = readLine(reader, " int");
		int n = Integer.parseInt(line);
		return n;
	}

	private static String readLine(BufferedReader reader, String primitiveType) throws IOException {
		String line;
		do {
			line = reader.readLine();
			if (line == null) {
				String msg = "Encontrado fin de archivo leyendo " + primitiveType;
				throw new NoSuchElementException(msg);
			}
		} while (line.startsWith("#") || line.trim().equals(""));
		System.out.println("[INFO] Leida linea: @" + line + "@");
		int commentIndex = line.indexOf('#');
		if (commentIndex != -1) {
			line = line.substring(0, commentIndex);
		}
		line = line.trim();
		return line;
	}
}
