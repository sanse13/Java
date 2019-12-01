package a.int_binary_search_tree;

import hr.AbstractChallengeApp;
import hr.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "int_binary_search_tree";

	private static final String	TEST_CASES_FOLDER	= "./test_cases/" + ID + "/";
	private static final String	RESULTS_FOLDER		= "./resultados/";
	private static final String	LOG					= "./LOG-" + ID + ".txt";

	@Override
	public void readArgs() throws Exception {
	}

	@Override
	public void solve() throws Exception {
		IntBinarySearchTree tree = new IntBinarySearchTree();

		for (int lineCounter = 0;; lineCounter++) {
			String line = getReader().readLine();
			if (line == null) {
				break;
			}
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}
			if (line.startsWith("#")) {
				continue;
			}
			String[] aTemp = line.replaceAll("\\s+$", "").split(" ");

			String cmd = aTemp[0].toLowerCase();
			System.out.println("[linea: " + lineCounter + "] " + line);

			if ("findmax".equals(cmd)) {
				int tmp = tree.findMax();
				println("findmax " + tmp);
			} else if ("contains".equals(cmd)) {
				int x;
				try {
					x = Integer.parseInt(aTemp[1]);
				} catch (Exception e) {
					String msg = "Linea con formato ilegal [contains ENTERO!]\n\t " + line;
					throw new Exception(msg);
				}
				println("contains " + x + " " + tree.contains(x));
			} else if ("inordernext".equals(cmd)) {
				int x;
				try {
					x = Integer.parseInt(aTemp[1]);
				} catch (Exception e) {
					String msg = "Linea con formato ilegal [inordernext ENTERO!]\n\t " + line;
					throw new Exception(msg);
				}
				println("inordernext " + x + " " + tree.inOrderNext(x));
			} else if ("remove".equals(cmd)) {
				int x;
				try {
					x = Integer.parseInt(aTemp[1]);
				} catch (Exception e) {
					String msg = "Linea con formato ilegal [remove ENTERO!]\n\t " + line;
					throw new Exception(msg);
				}
				tree.remove(x);
				println("remove " + x);
			} else if ("insert".equals(cmd)) {
				int x;
				try {
					x = Integer.parseInt(aTemp[1]);
				} catch (Exception e) {
					String msg = "Linea con formato ilegal [insert ENTERO!]\n\t " + line;
					throw new Exception(msg);
				}
				tree.insert(x);
				println("insert " + x);
			} else {
				String s = "Error en archivo de entrada: orden  debe ser [findmax|contains|remove|insert]";
				throw new RuntimeException(s);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
