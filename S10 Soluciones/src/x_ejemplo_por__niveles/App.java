package x_ejemplo_por__niveles;

import base.IntBinaryTreeNode;
import base.IntBinaryTrees;
import hr.AbstractChallengeApp;
import hr.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "x_recorrido_arbol";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";
	private static final String	RESULTS_FOLDER		= "./resultados/";

	private IntBinaryTreeNode arg;

	@Override
	public void readArgs() throws Exception {
		arg = IntBinaryTrees.read(getReader());
		IntBinaryTrees.showPreOrder("Arbol en preorden", arg);
	}

	@Override
	public void solve() throws Exception {
		My_Code.solve(arg, getWriter());
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
