package bfs.bipartite_test;

import base.IntGraph;
import base.IntGraphs;
import hr.AbstractChallengeApp;
import hr.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "bfs_bipartite";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";
	private static final String	RESULTS_FOLDER		= "./resultados/";

	private IntGraph g;

	private BFS		bfs;
	private boolean	result;

	@Override
	public void printResult() throws Exception {
		println("Es bipartito? ", result);
	}

	@Override
	public void readArgs() throws Exception {
		g = IntGraphs.read(getReader());
		g.setName(getTestName());
		IntGraphs.show("[App] Grafo leido", g);

		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		bfs = new BFS(g);
		result = bfs.call();
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
