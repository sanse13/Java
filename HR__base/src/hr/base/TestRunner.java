package hr.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class TestRunner {

	private static final int MAX_DISPLAYED_LINES = 10;

	private static final String[] ZERO = new String[0];

	private static final String COMMON_DIR =
			// "/home/x/Dropbox/EDA-EXAM-09-OCT" + "/a/1__swap_first_last/";
			null;

	public void runAllTests(String testCasesFolder, String resultsFolder,

			AbstractChallengeApp challenge) throws Exception {
		runAllTests(testCasesFolder, resultsFolder, resultsFolder + "../LOG.txt", challenge);
	}

	public void runAllTests(String testCasesFolder, String resultsFolder, String logPath,
			AbstractChallengeApp challenge) throws Exception {
		try (PrintStream tmp = new LoggedStdOut(logPath)) {
			if (COMMON_DIR != null) {
				System.out.println("[INFO] COMMON_DIR=" + COMMON_DIR);
				testCasesFolder = COMMON_DIR + "/test-cases/";
				resultsFolder = COMMON_DIR + "/results/";
			}
			File fileResultsFolder = tryOpenFolder(resultsFolder);
			deleteFilesIn(fileResultsFolder);

			File inputFolder = new File(testCasesFolder + "input");
			File outputFolder = new File(testCasesFolder + "output");
			File[] inputFiles = inputFolder.listFiles();
			File[] outputFiles = outputFolder.listFiles();

			if (inputFiles == null) { throw new RuntimeException(
					"No se pudo leer carpeta: " + inputFolder.getAbsolutePath()); }

			if (outputFiles == null) { throw new RuntimeException(
					"No se pudo leer carpeta: " + outputFolder.getAbsolutePath()); }

			if (inputFiles.length != outputFiles.length) { throw new RuntimeException(
					testCasesFolder + " " + inputFiles.length + "[archivos de entrada] != "
							+ outputFiles.length + "[archivos de salida]"); }

			int test_cases = inputFiles.length;
			int okCount = 0;
			for (int index = 1; index <= test_cases; index++) {
				File in = inputFiles[index - 1];
				File out = outputFiles[index - 1];

				System.out.println();
				System.out.println("*** Test: " + index + " de " + test_cases + " ***");

				System.out.println("\tEntrada: " + in.getAbsolutePath());
				System.out.println("\tSalida esperada: " + out.getAbsolutePath());

				String[] textInput = Files.readAllLines(in.toPath()).toArray(ZERO);
				String[] textExpected = Files.readAllLines(out.toPath()).toArray(ZERO);

				File tmpIn = createInputCopy(resultsFolder + in.getName(), textInput);
				System.out.println("[INFO] Entrada copiada en " + tmpIn.getAbsolutePath());

				try {
					String outputPath = resultsFolder + "OUTPUT__" + in.getName();
					runTest(in.getName(), tmpIn, outputPath, challenge);

					String[] textOutput = Files.readAllLines(Paths.get(outputPath)).toArray(ZERO);

					boolean match = Arrays.deepEquals(textExpected, textOutput);
					if (!match) {
						System.out.println("****************************");
						System.out.println("!!! Resultado INCORRECTO !!!\t" + in.getName());
						System.out.println("****************************");

						String msgIn = textInput.length > MAX_DISPLAYED_LINES
								? "[" + Math.min(MAX_DISPLAYED_LINES, textInput.length) + " lineas de "
										+ textInput.length + "]"
								: "";
						System.out.println("*** Entrada " + msgIn);
						Arrays.stream(textInput).limit(MAX_DISPLAYED_LINES).forEach(System.out::println);

						String msgOut = textOutput.length > MAX_DISPLAYED_LINES
								? "[" + Math.min(MAX_DISPLAYED_LINES, textOutput.length) + " lineas de "
										+ textOutput.length + "]"
								: "";
						System.out.println("*** Salida producida " + msgOut);
						Arrays.stream(textOutput).limit(MAX_DISPLAYED_LINES).forEach(System.out::println);

						String msgExpected = textExpected.length > MAX_DISPLAYED_LINES
								? "[" + Math.min(MAX_DISPLAYED_LINES, textExpected.length) + " lineas de "
										+ textExpected.length + "]"
								: "";
						System.out.println("*** Salida esperada " + msgExpected);
						Arrays.stream(textExpected).limit(MAX_DISPLAYED_LINES).forEach(System.out::println);
					} else {
						System.out.println("!!! Resultado correcto !!! con " + in.getName());
						okCount++;
					}
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}
			}
			System.out.println();
			System.out.println("***********");
			System.out.println("*** Exitos: " + okCount + " de " + test_cases);
			System.out.println("***********");
			System.out.println();
		}
	}

	public void runTest(String testName, File inputFile, String outputPath, AbstractChallengeApp challenge)
			throws Exception {
		InputStream oldIn = System.in;
		try (BufferedReader bufferedReader = cfgStdInput(inputFile)) {
			try (BufferedWriter bufferedWriter = cfgOutput(outputPath)) {

				challenge.config(testName, bufferedReader, bufferedWriter);

				long t0Read = System.currentTimeMillis();
				System.out.println("[INFO] Leyendo archivo " + inputFile.getAbsolutePath() + "...");
				challenge.readArgs();

				long tfRead = System.currentTimeMillis();
				long elapsedTimeRead = tfRead - t0Read;
				String msg = "[INFO] Elapsed time [reading args]: " + elapsedTimeRead + " millis.";
				System.out.println(msg);

				System.out.println("[INFO] Ejecutando solucion...");
				try {
					long t0Solve = System.currentTimeMillis();
					challenge.solve();
					long tfSolve = System.currentTimeMillis();
					long elapsedTime = tfSolve - t0Solve;
					String s = "[INFO] Elapsed time [solve]: " + elapsedTime + " millis.";
					System.out.println(s);
					try {
						challenge.printResult();
					} catch (Exception e) {
						System.out.println("*** ERROR procesando resultado!!!");
						e.printStackTrace(System.out);
					}
				} catch (Exception e) {
					System.out.println("*** ERROR ejecutando la solucion");
					e.printStackTrace(System.out);
				}
			}
		}
		System.out.println();
		System.out.println("[INFO] Solucion ejecutada con: " + testName);
		System.setIn(oldIn);
	}

	private BufferedWriter cfgOutput(String outputPath) throws IOException {
		File file = new File(outputPath);
		System.out.println("[INFO] Archivo de salida: " + file.getAbsolutePath());
		BufferedWriter tmp = new BufferedWriter(new FileWriter(outputPath));
		return tmp;
	}

	private BufferedReader cfgStdInput(File inputFile) throws FileNotFoundException {
		if (!inputFile.exists()) {
			String s = "No existe archivo: " + inputFile.getAbsolutePath();
			throw new RuntimeException(s);
		}
		if (!inputFile.canRead()) {
			String s = "No existe archivo: " + inputFile.getAbsolutePath();
			throw new RuntimeException(s);
		}
		String info = "[INFO] System.in redirigido a archivo: " + inputFile.getAbsolutePath();
		System.out.println(info);
		System.setIn(new FileInputStream(inputFile));
		BufferedReader tmp = new BufferedReader(new InputStreamReader(System.in));
		return tmp;
	}

	private File createInputCopy(String path, String[] lines) throws IOException {
		File tmp = new File(path);
		PrintStream ps = new PrintStream(tmp);
		Arrays.stream(lines).forEach(ps::println);
		ps.close();
		return tmp;
	}

	private void deleteFilesIn(File fileResultsFolder) {
		int totalFiles = fileResultsFolder.listFiles().length;
		int deleted = Arrays.stream(fileResultsFolder.listFiles()).mapToInt(file -> file.delete() ? 1 : 0)
				.sum();
		if (totalFiles != deleted) {
			String s = "No pudo vaciarse carpeta " + fileResultsFolder.getAbsolutePath() + "[" + deleted
					+ " borrados de " + totalFiles + "]";
			throw new RuntimeException(s);
		} else {
			String msg = "[INFO] " + deleted + " archivos borrados en " + fileResultsFolder.getAbsolutePath();
			System.out.println(msg);
		}
	}

	private File tryOpenFolder(String folderPath) {
		File folder = new File(folderPath);
		if (!folder.isDirectory()) {
			boolean b = folder.mkdir();
			if (b) {
				String s = "[INFO] Creada carpeta " + folder.getAbsolutePath()
						+ ". En Eclipse debera actualizarse la pestaña 'Package Explorer' [Pinchar en el proyecto y pulsar F5]";
				System.out.println(s);
			} else {
				String s = "No se pudo crear carpeta " + folder.getAbsolutePath();
				throw new RuntimeException(s);
			}
		} else {
			System.out.println("[INFO] Encontrada carpeta: " + folder.getAbsolutePath());
		}
		return folder;
	}
}
