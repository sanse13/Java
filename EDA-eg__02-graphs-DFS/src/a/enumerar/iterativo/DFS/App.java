package a.enumerar.iterativo.DFS;

import hr.base.AbstractChallengeApp;
import hr.base.IntGraph;
import hr.base.IntGraphs;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "enumerar_dfs_stack";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + "enumerar" + ".txt";
	private static final String	RESULTS_FOLDER		= "./resultados/";

	private int			startNodeID;
	private IntGraph	g;

	@Override
	public void readArgs() throws Exception {
		startNodeID = readInt("[App] start node:");

		g = IntGraphs.read(getReader());
		g.setName(getTestName());

		IntGraphs.show("[App] Grafo leido", g);
		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		new DFS<>(g, getWriter()).from(startNodeID);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
