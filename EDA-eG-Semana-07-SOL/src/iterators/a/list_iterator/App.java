package iterators.a.list_iterator;

import java.util.Iterator;

import base.IntSinglyLinkedLists;
import hr.AbstractChallengeApp;
import hr.TestRunner;
import util.IntArrays;

public class App extends AbstractChallengeApp {

	private static final String ID = "list_iterator";

	private static final String TEST_CASES_FOLDER = "./test_cases/" + ID + "/";
	private static final String LOG = "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	int[] array;

	@Override
	public void printResult() throws Exception {
	}

	@Override
	public void readArgs() throws Exception {
		array = IntArrays.read(getReader());
		IntArrays.show("Lista leida: ", array);
	}

	@Override
	public void solve() throws Exception {
		Iterator<Integer> it = My_Code.solve(IntSinglyLinkedLists.buildFrom(array));

		for (;;) {
			String line = getReader().readLine();
			if (line == null) {
				break;
			}
			line = line.trim();
			if (line.length() == 0 || line.startsWith("#")) {
				continue;
			}
			System.out.println("[INFO] Linea leida: " + line);

			String[] aTemp = line.replaceAll("\\s+$", "").split(" ");
			String cmd = aTemp[0].toLowerCase();
			if ("new".equals(cmd)) {
				it = My_Code.solve(IntSinglyLinkedLists.buildFrom(array));
			} else if ("hasNext".toLowerCase().equals(cmd)) {
				println(it.hasNext());
			} else if ("next".equals(cmd)) {
				try {
					println(it.next());
				} catch (Exception e) {
					println(e.getClass().getCanonicalName());
				}
			} else if ("rmv".equals(cmd)) {
				it.remove();
			} else {
				String s = "Error en archivo de entrada: nombre incorrecto";
				throw new RuntimeException(s);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
