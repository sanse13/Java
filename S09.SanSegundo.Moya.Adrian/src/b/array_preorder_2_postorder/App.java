package b.array_preorder_2_postorder;

import hr.AbstractChallengeApp;
import hr.TestRunner;
import util.IntArrays;

public class App extends AbstractChallengeApp {

	private static final String ID = "b_array_preorder_2_postorder";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";
	private static final String	RESULTS_FOLDER		= "./resultados/";

	private int[] arg;

	private int[] rs;

	@Override
	public void printResult() throws Exception {
		IntArrays.println(rs, " ", getWriter());
	}

	@Override
	public void readArgs() throws Exception {
		arg = IntArrays.read(getReader());
		IntArrays.show("Array:", arg);
	}

	@Override
	public void solve() throws Exception {

		
		rs = My_Code.solve(arg);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
