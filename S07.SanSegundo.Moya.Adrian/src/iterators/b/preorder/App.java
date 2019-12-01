package iterators.b.preorder;

import hr.base.AbstractChallengeApp;
import hr.base.IntBinaryTreeNode;
import hr.base.IntBinaryTrees;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String ID = "tree_iterator";

	private static final String TEST_CASES_FOLDER = "./test_cases/" + ID + "/";
	private static final String LOG = "./LOG-" + ID + ".txt";

	private static final String RESULTS_FOLDER = "./resultados/";

	IntBinaryTreeNode root;

	@Override
	public void readArgs() throws Exception {
		root = IntBinaryTrees.read(getReader());
		IntBinaryTrees.showPreOrder("Arbol en preorden: ", root);
	}

	@Override
	public void solve() throws Exception {
		MyTreeIterator it = new MyTreeIterator(root);

		for (;;) {
			String line = getReader().readLine();
			if (line == null) {
				break;
			}
			line = line.trim();
			if (line.length() == 0 || line.startsWith("#")) {
				continue;
			}
			System.out.println("[App] Linea leida: " + line);

			String[] aTemp = line.replaceAll("\\s+$", "").split(" ");
			String cmd = aTemp[0].toLowerCase();
			if ("new".equals(cmd)) {
				it = new MyTreeIterator(root);
			} else if ("hasNext".toLowerCase().equals(cmd)) {
				println(it.hasNext());
			} else if ("next".equals(cmd)) {
				try {
					println(it.next().label);
				} catch (Exception e) {
					println(e.getClass().getCanonicalName());
				}
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
