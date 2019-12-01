package e.alcanzables_a_distancia;

import hr.base.AbstractChallengeApp;
import hr.base.IntGraph;
import hr.base.IntGraphs;
import hr.base.TestRunner;

public class App extends AbstractChallengeApp {

	private static final String	TEST_CASES_FOLDER	= "./test_cases/e.alcanzables_a_distancia/";
	private static final String	RESULTS_FOLDER		= "./resultados/";
	private static final String	LOG					= "./LOG-e.alcanzables_a_distancia.txt";

	private int			start;
	private int			distanciaMaxima;
	private IntGraph	g;

	private int[] rs;

	@Override
	public void printResult() throws Exception {
		println(rs);
	}

	@Override
	public void readArgs() throws Exception {
		start = readInt();
		System.out.println("[App] start node:" + start);

		distanciaMaxima = readInt();
		System.out.println("[App] distancia maxima:" + distanciaMaxima);

		g = IntGraphs.read(getReader());
		g.setName(getTestName());

		IntGraphs.show("[App] Grafo leido", g);
		IntGraphs.draw(g);
	}

	@Override
	public void solve() throws Exception {
		rs = Student_Code.solve(g, start, distanciaMaxima);
	}

	public static void main(String[] args) throws Exception {
		new TestRunner().runAllTests(TEST_CASES_FOLDER, RESULTS_FOLDER, LOG, new App());
	}
}
