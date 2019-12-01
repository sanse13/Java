package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class IntArrays {
	private static final int	DEFAULT_PRINT_COLS_TRUNCATED_AT	= 5;
	private static final int	DEFAULT_PRINT_ROWS_TRUNCATED_AT	= 5;

	private IntArrays() {
	}

	/**
	 * Devuelve true si el array esta ordenado.
	 *
	 * @param array
	 *            el array a examinar.
	 */
	public static boolean isSorted(int[] array) {
		return isSorted(array, 0, array.length);
	}

	/**
	 * Devuelve true si la seccion especificada del array esta ordenada.
	 *
	 * EL rango de posiciones a examinar en el array es: fromInclusive..toExclusive-1
	 *
	 * @param array
	 *            el array a examinar.
	 * @param fromInclusive
	 *            primera posicion de la seccion a examinar (INCLUSIVE).
	 * @param toExclusive
	 *            ultima posicion de la seccion a examinar (EXCLUSIVE).
	 */
	public static boolean isSorted(int[] array, int fromInclusive, int toExclusive) {
		if (fromInclusive >= toExclusive) { return true; }
		if (0 > fromInclusive) {
			fromInclusive = 0;
		}
		if (toExclusive > array.length) {
			toExclusive = array.length;
		}
		for (int index = fromInclusive; index < toExclusive - 1; index++) {
			int a = array[index];
			int b = array[index + 1];
			if (a > b) { return false; }
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("...");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int[] array =
				// { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
				IntArrays.read(reader);
		show(" Array 1D", array);

		int[][] array2D =
				// { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				// { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, {
				// 1, 2,
				// 3, 4, 5, 6, 7, 8, 9, 10 },
				// { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, {
				// 1, 2,
				// 3, 4, 5, 6, 7, 8, 9, 10 },
				// { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } };
				IntArrays.read2D(reader);
		show(" Array 2D", array2D);
	}

	public static void println(int[] array, String sep, BufferedWriter bufferedWriter)
			throws IOException {
		for (int i = 0; i < array.length; i++) {
			bufferedWriter.write(String.valueOf(array[i]));
			if (i < array.length - 1) {
				bufferedWriter.write(sep);
			}
		}
		bufferedWriter.newLine();
	}

	public static void println(int[][] array, String sep, BufferedWriter bufferedWriter)
			throws IOException {
		int rows = array.length;
		int cols = array.length == 0 ? 0 : array[0].length;
		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			for (int colIndex = 0; colIndex < cols; colIndex++) {
				bufferedWriter.write(String.valueOf(array[rowIndex][colIndex]));
				if (colIndex < cols - 1) {
					bufferedWriter.write(sep);
				}
			}
			bufferedWriter.newLine();
		}
	}

	public static int[] read(BufferedReader bufferedReader) throws IOException {
		int dim = ReadPrimitiveTypes.readInt(bufferedReader);

		String line = bufferedReader.readLine();
		Objects.requireNonNull(line, "ERROR no hay linea en archivo de entrada!!!");

		String[] tmp = line.replaceAll("\\s+", " ").split(" ");
		if (dim > 0 && tmp.length != dim || dim == 0 && !"".equals(line)) {
			String s = "Error en archivo de entrada: formato incorrecto[items: "
					+ tmp.length + "!=" + dim + "]";
			throw new RuntimeException(s);
		}

		int[] array = new int[dim];
		for (int i = 0; i < dim; i++) {
			int aItem = Integer.parseInt(tmp[i]);
			array[i] = aItem;
		}
		return array;
	}

	public static int[][] read2D(BufferedReader bufferedReader) throws IOException {
		int rows = ReadPrimitiveTypes.readInt(bufferedReader);
		int cols = ReadPrimitiveTypes.readInt(bufferedReader);
		int[][] array = IntArrays.read2D(bufferedReader, rows, cols);
		return array;
	}

	public static int[][] read2D(BufferedReader bufferedReader, int rows, int cols)
			throws IOException {
		int[][] array = new int[rows][cols];

		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			String line = bufferedReader.readLine();
			Objects.requireNonNull(line, "ERROR no hay linea en archivo de entrada!!!");

			String[] tmp = line.replaceAll("\\s+", " ").split(" ");

			if (cols > 0 && tmp.length != cols || cols == 0 && !"".equals(line)) {
				String s = "Error en archivo de entrada: formato incorrecto[line: "
						+ rowIndex + "]";
				throw new RuntimeException(s);
			}

			for (int colIndex = 0; colIndex < cols; colIndex++) {
				int dgt = Integer.parseInt(tmp[colIndex]);
				array[rowIndex][colIndex] = dgt;
			}
		}
		return array;
	}

	public static void show(String infoMsg, int[] array) {
		show(infoMsg, array, DEFAULT_PRINT_COLS_TRUNCATED_AT);
	}

	public static void show(String infoMsg, int[] array, int truncAt) {
		printInfoMsg(infoMsg);
		int printed = 0;
		for (int i = 0; i < array.length; i++) {
			if (i < truncAt) {
				System.out.print(String.valueOf(array[i]));
				if (i < array.length - 1) {
					System.out.print(' ');
				}
				printed++;
			}
		}
		if (printed < array.length - 1) {
			System.out.println("... faltan: " + (array.length - printed));
		}
		System.out.println();
	}

	public static void show(String infoMsg, int[][] array) {
		show(infoMsg, array, DEFAULT_PRINT_ROWS_TRUNCATED_AT,
				DEFAULT_PRINT_COLS_TRUNCATED_AT);
	}

	public static void show(String infoMsg, int[][] array, int truncRowsAt,
			int truncColsAt) {
		printInfoMsg(infoMsg);
		int rows = array.length;
		int cols = array.length == 0 ? 0 : array[0].length;

		int printedRows = 0;
		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			if (printedRows < truncRowsAt) {
				int printedCols = 0;
				for (int colIndex = 0; colIndex < cols; colIndex++) {
					if (printedCols < truncColsAt) {
						System.out.print(String.valueOf(array[rowIndex][colIndex]));
						if (colIndex < cols - 1) {
							System.out.print(' ');
						}
						printedCols++;
					}
				}
				if (printedCols < cols) {
					System.out.print("... faltan: " + (cols - printedCols) + " columnas");
				}
				System.out.println();
				printedRows++;
			}
		}
		if (printedRows < rows) {
			System.out.print("... faltan: " + (rows - printedRows) + " filas");
		}
		System.out.println();
		printedRows++;

	}

	private static void printInfoMsg(String infoMsg) {
		if (infoMsg != null) {
			System.out.println("[INFO] " + infoMsg);
		}
	}
}