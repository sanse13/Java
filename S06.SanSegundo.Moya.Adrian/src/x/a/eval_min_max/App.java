package x.a.eval_min_max;

import hr.base.AbstractChallengeApp;
import hr.base.IntBinaryTreeNode;
import hr.base.IntBinaryTrees;
import hr.base.StructMinMax;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String	TEST_CASES_FOLDER	= "./test_cases/eval_min_max/";
	private static final String	RESULTS_FOLDER		= "./resultados/";
	private static final String	LOG					= "./LOG-eval_min_max.txt";

	private IntBinaryTreeNode arg;

	private StructMinMax rs;

	@Override
	public void printResult() throws Exception {
		println(rs.min);
		println(rs.max);
	}

	@Override
	public void readArgs() throws Exception {
		arg = IntBinaryTrees.read(getReader());
		IntBinaryTrees.showPreOrder("[App] Arbol leido", arg);
	}

	@Override
	public void solve() throws Exception {
		rs = My_Code.solve(arg);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
