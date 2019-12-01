package dfs.topological_sort;

import base.IntGraph;
import base.IntGraphs;
import base.IntSinglyLinkedListNode;
import base.IntSinglyLinkedLists;
import hr.AbstractChallengeApp;
import hr.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "dfs_topological";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";
	private static final String	RESULTS_FOLDER		= "./resultados/";

	private IntGraph g;

	private DFS		dfs;
	private boolean	isDAG;

	@Override
	public void printResult() throws Exception {
		println("Es aciclico? ", isDAG);
		if (isDAG) {
			IntSinglyLinkedListNode lst = dfs.topologicalSort();
			IntSinglyLinkedLists.println(lst, getWriter());
		} else {
			for (int n : dfs.cycleNodes()) {
				println("Nodo de bucle: ", n);
			}
		}
	}

	@Override
	public void readArgs() throws Exception {
		g = IntGraphs.read(getReader());
		assert g.isDirected();

		g.setName(getTestName());
		IntGraphs.show("[App] Grafo leido", g);

		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		dfs = new DFS(g);
		isDAG = dfs.call();
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
