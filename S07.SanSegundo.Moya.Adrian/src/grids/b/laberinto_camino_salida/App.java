package grids.b.laberinto_camino_salida;

import hr.base.AbstractChallengeApp;
import hr.base.GridCoordinates;
import hr.base.IntArrays;
import hr.base.SinglyLinkedListNode;
import hr.base.SinglyLinkedLists;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "laberinto_camino_salida";

	private static final String TEST_CASES_FOLDER = "./test_cases/" + ID + "/";
	private static final String LOG = "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	private int enterColumn;
	private int exitColumn;
	private boolean[][] laberinto;

	private SinglyLinkedListNode<GridCoordinates> rs;

	@Override
	public void printResult() throws Exception {
		SinglyLinkedLists.println(rs, "\n", getWriter(), g -> "" + g.column + " " + g.row);
	}


	@Override
	public void readArgs() throws Exception {
		enterColumn = readInt();
		System.out.println("[App] Columna de entrada: " + enterColumn);
		exitColumn = readInt();
		System.out.println("[App] Columna de salida: " + exitColumn);

		int n = readInt();
		System.out.println("[App] Filas/columnas: " + n);
		int libre = readInt();
		System.out.println("[App] Marca de casilla libre: " + n);
		int[][] tmp = IntArrays.read2D(getReader(), n, n);
		IntArrays.show("Matriz leida: ", tmp);

		laberinto = IntArrays.toBoolean(tmp, libre);
	}

	@Override
	public void solve() throws Exception {
		rs = Student_Code.solve(enterColumn, exitColumn, laberinto);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
