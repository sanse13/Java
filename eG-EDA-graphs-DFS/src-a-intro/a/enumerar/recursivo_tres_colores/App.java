package a.enumerar.recursivo_tres_colores;

import base.IntGraph;
import base.IntGraphs;
import hr.AbstractChallengeApp;
import hr.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "enumerar_dfs_rec";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";
	private static final String	RESULTS_FOLDER		= "./resultados/";

	private int			startNodeID;
	private IntGraph	g;

	private DFS<Integer> dfs;

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
		dfs = new DFS<>(g, getWriter());
		dfs.from(startNodeID);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
