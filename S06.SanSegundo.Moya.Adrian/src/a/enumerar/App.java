package a.enumerar;

import hr.base.AbstractChallengeApp;
import hr.base.IntGraph;
import hr.base.IntGraphs;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String	TEST_CASES_FOLDER	= "./test_cases/a.enumerar/";
	private static final String	RESULTS_FOLDER		= "./resultados/";
	private static final String	LOG					= "./LOG-a.enumerar.txt";

	private int			startNodeID;
	private IntGraph	g;

	@Override
	public void readArgs() throws Exception {
		startNodeID = readInt();
		System.out.println("[App] start node:" + startNodeID);

		g = IntGraphs.read(getReader());
		g.setName(getTestName());
		IntGraphs.show("[App] Grafo leido", g);

		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		Student_Code.solve(g, startNodeID, getWriter());
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
