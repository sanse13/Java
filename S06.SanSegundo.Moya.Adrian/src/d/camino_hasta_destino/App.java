package d.camino_hasta_destino;

import hr.base.AbstractChallengeApp;
import hr.base.IntGraph;
import hr.base.IntGraphs;
import hr.base.IntSinglyLinkedListNode;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String	TEST_CASES_FOLDER	= "./test_cases/d.camino_hasta_destino/";
	private static final String	RESULTS_FOLDER		= "./resultados/";
	private static final String	LOG					= "./LOG-d.camino_hasta_destino.txt";

	private int			start;
	private int			end;
	private IntGraph	g;

	private IntSinglyLinkedListNode result;

	@Override
	public void printResult() throws Exception {
		println(result);
	}

	@Override
	public void readArgs() throws Exception {
		start = readInt();
		System.out.println("[App] start node:" + start);

		end = readInt();
		System.out.println("[App] end node:" + start);

		g = IntGraphs.read(getReader());
		g.setName(getTestName());

		IntGraphs.show("[App] Grafo leido", g);
		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		result = Student_Code.solve(g, start, end);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
