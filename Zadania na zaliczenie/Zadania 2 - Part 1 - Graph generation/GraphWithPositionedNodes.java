import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import java.io.IOException;
import java.util.Random;

public class GraphWithPositionedNodes {

	public String defaultStyleSheet = "graph { fill-color: white; } "
			+" node { fill-color:black; size:10px;text-alignment:above; }"
			+" edge { fill-color:grey;size:2px;text-size:10; } ";
	
	SingleGraph graph;
	Random alea; 
	
	public GraphWithPositionedNodes() {
		init();
		graph.display();
		createGraph();
		hitakey("positions");
		addPositions();
	}
	
	public void createGraph() {
		for(int i = 0; i < 12; i++) {
			Node v = graph.addNode("v_" + i);
		}
	}
	
	public void addPositions() {
		for(Node v: graph.getNodeSet()) {
			double x = alea.nextDouble() * 1000;
			double y = alea.nextDouble() * 1000;
			v.addAttribute("x", x);
			v.addAttribute("y", y);
			v.addAttribute("ui.label", v.getId());
		}
	}
	
	// ========================= INIT =======================
	
	public void init() {
		graph = new SingleGraph("simple graph");
		graph.addAttribute("ui.stylesheet", defaultStyleSheet);
		alea = new Random(System.currentTimeMillis());
	}

	// ========================= MAIN =======================

	public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new GraphWithPositionedNodes();
	}

	// ======================== TOOLS =======================

	/**
	 * this method stops the execution of the code until the user 
	 * hit a key on the keyboard
	 * @param message
	 */
	public void hitakey(String message) {
		try {
			System.out.println(message);
			System.in.read();
		} catch(IOException ioe) {}
	}
}