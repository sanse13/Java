package graphs.b.cercano_mas_alto;

import hr.base.AbstractChallengeApp;
import hr.base.IntArrays;
import hr.base.IntGraph;
import hr.base.IntGraphs;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "cercano_mas_alto";

	private static final String TEST_CASES_FOLDER = "./test_cases/" + ID + "/";
	private static final String LOG = "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	private int x;
	private int[] h;
	private IntGraph g;

	private int rs;

	@Override
	public void printResult() throws Exception {
		println(rs);
		System.out.println("[App] Resultado: " + rs);
	}

	@Override
	public void readArgs() throws Exception {
		x = readInt();
		System.out.println("[App] x: " + x);
		h = IntArrays.read(getReader());
		IntArrays.show("array H", h);
		g = IntGraphs.read(getReader(), false, h.length);
		IntGraphs.show("Grafo leido: ", g);
		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		rs = Student_Code.solve(x, h, g);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
