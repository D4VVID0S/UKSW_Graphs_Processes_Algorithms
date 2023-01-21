import org.graphstream.graph.implementations.SingleGraph;
import java.util.Random;
import org.graphstream.graph.Node;

public class gridGraph {

    public String defaultStyleSheet = "graph { fill-color: white; } "
			+" node { fill-color:black; size:5px;text-alignment:above; }"
			+" edge { fill-color:grey;size:2px;text-size:10; } ";

	static SingleGraph graph;
    static int gridSize = 4;
    int numNodes = 1;
	Random alea;

    /**
	 * for generating a grid we only need the number of nodes or the grid size. 
	 * The number of edges is given by construction (and by definition)
	 */
    public static void main(String[] args) {

        // System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        SingleGraph graph = new SingleGraph("Grid");
    	boolean torus = true;
		graph.display(torus);

        for (int v_line = 0; v_line < gridSize; v_line++) {
            for (int col = 0; col < gridSize; col++) {
            Node u = graph.addNode(v_line + "," + col);
            u.addAttribute("x", v_line);
            u.addAttribute("y", col);
			u.addAttribute("ui.label",u.getId());
            }
        }
        // add edges link each node to two neighbors located at:
		// v_line, col + 1 & v_line + 1, col
        for (int eline = 0; eline < gridSize; eline++) {
            for (int ecol = 0; ecol < gridSize; ecol++) {
                String idfSrc = eline + "," + ecol;
                Node src = graph.getNode(idfSrc);
                int lineplusone = eline + 1;

                if(torus) lineplusone = lineplusone%gridSize;
                int colplusone = ecol + 1;

                if(torus) colplusone = colplusone % gridSize;
                String idfEast = lineplusone + "," + ecol;
                String idfNotrth = eline + "," + colplusone;
                
                if (lineplusone < gridSize)
                    graph.addEdge(idfSrc + "-" + idfEast, idfSrc, idfEast);
                
                if (colplusone < gridSize)
                    graph.addEdge(idfSrc + "-" +  idfNotrth, idfSrc, idfNotrth);
            }
        }
    }
}