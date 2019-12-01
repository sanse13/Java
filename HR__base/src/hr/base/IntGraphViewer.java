package hr.base;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.view.Viewer;

/**
 * Clase simple para visualizar grafos en ventanas.
 *
 * Un GraphViewer visualiza el grafo especificado en su constructora.
 */
public class IntGraphViewer<E> {

	private static final String STYLE_SHEET = "graph { padding: 40px; }"
			+ "node { fill-color: red; text-size: 14px; }";

	private static int anonymousCounter = 0;

	private String				ID;
	private GenericIntGraph<E>	g;

	private JFrame frame;

	/**
	 * Crea un nuevo visor de grafos.
	 *
	 * @param g
	 *            el grafo que visualiza este visor.
	 */
	public IntGraphViewer(GenericIntGraph<E> g) {
		this.g = g;
		ID = g.getName() == null ? String.valueOf(anonymousCounter++) : g.getName();
	}

	public JFrame draw() {
		return draw(800, 600);
	}

	public JFrame draw(int width, int height) {
		org.graphstream.graph.Graph gs = toGSGraph(g);
		Viewer viewer = new Viewer(gs, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();

		DefaultView view = new DefaultView(viewer, ID, Viewer.newGraphRenderer());
		viewer.addView(view);

		if (frame == null) {
			frame = new JFrame(ID);
			frame.setLayout(new BorderLayout());
			frame.add(view, BorderLayout.CENTER);
			frame.setSize(width, height);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		return frame;
	}

	public JFrame getFrame() {
		if (frame == null) { throw new NullPointerException(); }
		return frame;
	}

	public String getID() {
		return ID;
	}

	/**
	 * Ejemplo de uso.
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File tmp1 = new File("input.txt");
		System.out.println("Archivo: " + tmp1.getAbsolutePath());
		BufferedReader bufferedReader1 = new BufferedReader(
				new InputStreamReader(new FileInputStream(tmp1)));
		IntGraph g1 = IntGraphs.read(bufferedReader1);
		IntGraphs.draw(g1);

		File tmp2 = new File("input2.txt");
		BufferedReader bufferedReader2 = new BufferedReader(
				new InputStreamReader(new FileInputStream(tmp2)));
		IntGraph g2 = IntGraphs.read(bufferedReader2);

		IntGraphs.draw(g2);
	}

	/**
	 * La visualización se hace mediante clases de librerías adicionales (pero libres).
	 */
	private static <E> org.graphstream.graph.Graph toGSGraph(GenericIntGraph<E> graph) {
		org.graphstream.graph.Graph gsg = new MultiGraph("GSGraph");
		for (Integer i : graph.vertexIterator()) {
			String id = graph.nodeLabel(i);
			org.graphstream.graph.Node n = gsg.addNode(id);
			n.addAttribute("ui.label", id);
		}

		boolean isDirected = graph.isDirected();

		int edgeID = 0;
		for (Integer aNode : graph.vertexIterator()) {
			String from = graph.nodeLabel(aNode);
			for (E edge : graph.edgesOf(aNode)) {
				String e = String.valueOf(edgeID);
				String to = graph.nodeLabel(graph.destinationOf(edge));
				gsg.addEdge(e, from, to, isDirected);
				edgeID++;
			}
		}
		gsg.addAttribute("ui.antialias");
		gsg.addAttribute("ui.quality");
		gsg.addAttribute("ui.stylesheet", STYLE_SHEET);
		return gsg;
	}
}
