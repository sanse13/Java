package A.laberinto__RESUELTO;

import hr.AbstractChallengeApp;
import hr.TestRunner;
import util.IntArrays;

public class App extends AbstractChallengeApp {

	private static final String ID = "laberinto";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	private boolean[][] laberinto;

	private boolean rs;

	@Override
	public void printResult() throws Exception {
		println("[App] Resultado", rs);
	}

	@Override
	public void readArgs() throws Exception {
		int n = readInt("[App] Filas/columnas");
		int libre = readInt("[App] Marca de celdas libres");

		int[][] tmp = IntArrays.read2D(getReader(), n, n);
		IntArrays.show("Cuadricula leida: ", tmp);

		laberinto = IntArrays.toBoolean(tmp, libre);
	}

	@Override
	public void solve() throws Exception {
		rs = Student_Code.solve(laberinto);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
