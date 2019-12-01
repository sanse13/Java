package a.OBLIGATORIO.es_bst;

import base.IntBinaryTreeNode;
import base.IntBinaryTrees;
import hr.AbstractChallengeApp;
import hr.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "a_es_bst";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";
	private static final String	RESULTS_FOLDER		= "./resultados/";

	private IntBinaryTreeNode arg;

	private boolean rs;

	@Override
	public void printResult() throws Exception {
		println(rs);
	}

	@Override
	public void readArgs() throws Exception {
		arg = IntBinaryTrees.read(getReader());
		IntBinaryTrees.showPreOrder("Arbol:", arg);
	}

	@Override
	public void solve() throws Exception {
		rs = My_Code.solve(arg);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
