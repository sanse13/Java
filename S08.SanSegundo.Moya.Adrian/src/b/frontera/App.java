package b.frontera;

import base.IntGraph;
import base.IntGraphs;
import base.IntSinglyLinkedListNode;
import hr.AbstractChallengeApp;
import hr.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "frontera";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	private int			x;
	private int			d;
	private IntGraph	g;

	private IntSinglyLinkedListNode rs;

	@Override
	public void printResult() throws Exception {
		println(rs);
		System.out.println("[App] Resultado: " + rs);
	}

	@Override
	public void readArgs() throws Exception {
		x = readInt("[App] x: ");
		d = readInt("[App] d: ");
		g = IntGraphs.read(getReader());
		IntGraphs.show("Grafo leido: ", g);
		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		rs = Student_Code.solve(x, d, g);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
