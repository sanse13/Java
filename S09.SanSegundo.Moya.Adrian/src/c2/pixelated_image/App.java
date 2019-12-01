package c2.pixelated_image;

import hr.AbstractChallengeApp;
import hr.TestRunner;
import util.IntArrays;

public class App extends AbstractChallengeApp {

	private static final String ID = "c_pixelated_image_medio";

	private static final String TEST_CASES_FOLDER = "./test_cases/" + ID + "/";
	private static final String LOG = "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	private int[][] arg;

	@Override
	public void readArgs() throws Exception {
		arg = IntArrays.read2D(getReader());
		IntArrays.show("Imagen", arg);
	}

	@Override
	public void solve() throws Exception {
		Student_Code.solve(arg, getWriter());
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
