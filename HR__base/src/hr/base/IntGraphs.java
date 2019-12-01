package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import javax.swing.JFrame;

public class IntGraphs {
	private static final int DEFAULT_PRINT_TRUNCATED_AT = 30;

	private static final String INPUT_FILE_NAME = "input.txt";

	private IntGraphs() {
	}

	public static JFrame draw(GenericIntGraph<Integer> g) {
		if (g.getNumVertices() > 25) {
			System.out.println("[INFO] El grafo tiene demasiados nodos para ser dibujado");
			return null;
		} else {
			IntGraphViewer<Integer> v = new IntGraphViewer<>(g);
			JFrame frame = v.draw();
			return frame;
		}
	}

	public static void main(String[] args) throws IOException {
		String input_path = INPUT_FILE_NAME;
		File tmp = new File(input_path);
		System.out.println("Archivo: " + tmp.getAbsolutePath());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(tmp)));

		long t0 = System.currentTimeMillis();
		IntGraph g = IntGraphs.read(bufferedReader);
		long tf = System.currentTimeMillis();
		long elapsedTime = tf - t0;
		System.out.println("Elapsed time: " + elapsedTime + " millis.");

		for (int x : g.vertexIterator()) {
			System.out.print(x + " => ");
			for (Integer v : g.edgesOf(x)) {
				System.out.print(v + " ");
			}
			System.out.println();
		}

		IntGraphs.show("Grafo", g);
	}

	public static void println(IntGraph g, String sep, BufferedWriter bufferedWriter) throws IOException {
		for (int node = 0; node < g.nodes; node++) {
			if (g.adjacencyLists[node] != null) {
				bufferedWriter.write(String.valueOf(node));
				bufferedWriter.write(String.valueOf(' '));
				IntSinglyLinkedLists.println(g.adjacencyLists[node], sep, bufferedWriter);
			}
			bufferedWriter.newLine();
		}
	}

	public static IntGraph read(BufferedReader reader) throws IOException {
		boolean isDirected = ReadPrimitiveTypes.readBoolean(reader);
		int nodeCount = ReadPrimitiveTypes.readInt(reader);
		int edgeLines = ReadPrimitiveTypes.readInt(reader);

		IntFIFOQueue[] lists = new IntFIFOQueue[nodeCount];
		for (int i = 0; i < lists.length; i++) {
			lists[i] = new IntFIFOQueue();
		}

		for (int linesCount = 0, lineIndex = 0; linesCount < edgeLines; lineIndex++) {
			String line = reader.readLine();
			if (line == null) {
				String s = "Numero de lineas leido: " + linesCount + "; esperado: " + edgeLines;
				throw new NoSuchElementException(s);
			}
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}
			if (line.charAt(0) == '#') {
				continue;
			}
			String[] items = line.replaceAll("\\s+", " ").split(" ");

			if (items.length != 2) {
				String s = "Error en archivo de entrada: [line: " + lineIndex + "; " + "items: "
						+ items.length + "] " + line;
				throw new RuntimeException(s);
			}

			int source = Integer.parseInt(items[0]);
			int dest = Integer.parseInt(items[1]);

			IntFIFOQueues.addToEnd(lists[source], dest);
			if (!isDirected) {
				IntFIFOQueues.addToEnd(lists[dest], source);
			}
			linesCount++;
		}
		IntSinglyLinkedListNode[] adjacencyLists = new IntSinglyLinkedListNode[nodeCount];
		for (int i = 0; i < lists.length; i++) {
			adjacencyLists[i] = lists[i].head;
		}

		IntGraph g = new IntGraph(isDirected, nodeCount, adjacencyLists);
		return g;
	}

	public static void show(String infoMsg, IntGraph g) {
		show(infoMsg, g, " ", DEFAULT_PRINT_TRUNCATED_AT);
	}

	public static void show(String infoMsg, IntGraph g, String sep) {
		show(infoMsg, g, sep, DEFAULT_PRINT_TRUNCATED_AT);
	}

	public static void show(String infoMsg, IntGraph g, String sep, int truncAt) {
		printInfoMsg(infoMsg);
		System.out.println("\tNombre Grafo: " + g.getName());
		System.out.println("\tDirigido?: " + g.isDirectedGraph);
		System.out.println("\tNodos: " + g.nodes);
		int printed = 0;
		for (int node = 0; node < g.nodes; node++) {
			if (g.adjacencyLists[node] != null) {
				String s = "\tNodo origen: " + node + "; adyacentes: -> ";
				IntSinglyLinkedLists.show(s, g.adjacencyLists[node]);
				printed++;
			}
			if (printed == DEFAULT_PRINT_TRUNCATED_AT) {
				System.out.println("\t...Omitidos: " + (g.nodes - printed) + " nodos");
				break;
			}
		}
	}

	private static void printInfoMsg(String infoMsg) {
		if (infoMsg != null) {
			System.out.println(infoMsg);
		}
	}
}
