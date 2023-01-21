package GraphGeneration;

import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;
import java.util.Random;
import java.util.Iterator;
import java.util.ArrayList;

/*
 * 1. create an array of vertices of size gridSize
 * 2. connect vertives according to Moore's neighborhood
 * 3. remove vertices without edges
 */
public class honeycombGrid {

	// static int numNodes = 10;
	static int gridSize = 5;
	boolean torus = true;

	public String defaultStyleSheet = "graph { fill-color: white; } "
			+" node { fill-color:black; size:10px;text-alignment:above; }"
			+" edge { fill-color:grey;size:2px;text-size:10; } ";
	
	SingleGraph graph;
	Random alea; 
	
	
	public honeycombGrid() {
		init();
		graph.display(false); // false if you don't want automatic layout

		for (int vLine = 1; vLine <= gridSize; vLine++) {
			for (int vCol = 1; vCol <= gridSize; vCol++) {
				// hitakey("add nodes");
				Node u = graph.addNode(vLine + ", " + vCol);
				u.addAttribute("x", vLine);
				u.addAttribute("y", vCol);
				u.addAttribute("ui.label", u.getId());
			}
		}

		for (int eLine = 1; eLine <= gridSize; eLine++) {
			for (int eCol = 1; eCol <= gridSize; eCol++) {
				String idfSrc = eLine + ", " + (eCol + 1);
                Node src = graph.getNode(idfSrc);
				String idfDest = (eLine + 1) + ", " + eCol;
				Node dest = graph.getNode(idfDest);

				
				// Connect C -> NW
				graph.addEdge("C->NW", src, dest, torus);
				


                // int lineplusone = eLine + 1;

                // if(torus) lineplusone = lineplusone%gridSize;
                // int colplusone = eCol + 1;

                // if(torus) colplusone = colplusone % gridSize;
                // String idfEast = lineplusone + "," + eCol;
                // String idfNotrth = eLine + "," + colplusone;
                
                // if (lineplusone < gridSize)
                //     graph.addEdge(idfSrc + "-" + idfEast, idfSrc, idfEast);
                
                // if (colplusone < gridSize)
                //     graph.addEdge(idfSrc + "-" +  idfNotrth, idfSrc, idfNotrth);
			}
		}
	}
	
	// ========================= INIT =======================
	
	public void init() {
		graph = new SingleGraph("simple graph");
		graph.addAttribute("ui.stylesheet",defaultStyleSheet);
		alea = new Random(System.currentTimeMillis());
	}
	
	// ========================= MAIN =======================

	public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new honeycombGrid();
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