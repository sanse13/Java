package grids.b.laberinto_camino_salida;

import base.SinglyLinkedListNode;
import base.SinglyLinkedLists;
import hr.AbstractChallengeApp;
import hr.TestRunner;
import util.GridCoordinates;
import util.IntArrays;

public class App extends AbstractChallengeApp {

	private static final String ID = "laberinto_camino_salida";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	private int			enterColumn;
	private int			exitColumn;
	private boolean[][]	laberinto;

	private SinglyLinkedListNode<GridCoordinates> rs;

	@Override
	public void printResult() throws Exception {
		SinglyLinkedLists.show(
				"Camino[enterColumn: " + enterColumn + "; exitColumn: " + exitColumn + "]", rs,
				"\n");
		SinglyLinkedLists.println(rs, "\n", getWriter(), g -> "" + g.column + " " + g.row);
	}

	@Override
	public void readArgs() throws Exception {
		enterColumn = readInt("[App] Columna de entrada:");
		exitColumn = readInt("[App] Columna de salida:");

		int n = readInt("[App] Filas/columnas:");
		int libre = readInt("[App] Marca de casilla libre:");

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
