import java.io.IOException;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import java.util.Random;
import org.graphstream.graph.implementations.SingleGraph;

public class Tools {

	// for grids, the neighborhood has to be defined: 
	// von Neumann: a node has 4 neighbors located at North, South, East and West
	// moore: von Neumann + 4 new neighbors located at NE, NW, SW and SE.
	public final static String VON_NEUMANN = "von neumann";
	public final static String MOORE = "moore";
	
	// ============ GRAPH GENERATORS TOOLS ===================

	/**
	 * this method generates random geometric graphs.
	 * Each node is created with (x,y) coordinates
	 * the position of each node is randomly chosen in envSize x envSize square
	 * For the edges: if dist(u,v) < d => edge {u,v} is created
	 * @param n number of nodes
	 * @param d distance threshold 
	 * @param envSize size of the environment (a square) in which nodes are located
	 * @return the created graph
	 */
	public final static SingleGraph geometricGraph(int n, double d, int envSize) {
		Random alea = new Random(System.currentTimeMillis());
		SingleGraph graph = new SingleGraph("euclidean graph");
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias"); 
        // creation of nodes with their coordinates
        for(int i=0;i<n;i++) {
        	Node u = graph.addNode("v_"+i);
        	u.addAttribute("x",alea.nextDouble()*envSize);
        	u.addAttribute("y",alea.nextDouble()*envSize);
        }
        // computation and creation of edges, an edge is created between
        // two nodes if and only if their distance is lower than d
        for(Node u:graph.getNodeSet()) {
        	for(Node v: graph.getNodeSet()) {
        		if((!u.hasEdgeBetween(v) && (distance(u,v) < d)) && (u.getId() != v.getId())) {
        			graph.addEdge(u.getId()+"-"+v.getId(),u.getId(),v.getId());
        		}
        	}
        }
        return graph;
	}
	
	/**
	 * this generic method enable the production of simple von neumann grids or 
	 * moore-based grids, but also torus, whatever the chosen neighborhood
	 * @param size
	 * @param neighborhood
	 * @param torus
	 * @return
	 */
	public static SingleGraph GridAndTorus(int size, String neighborhood, boolean torus) {
		String graphName = new String(neighborhood+" grid of size "+size);
		SingleGraph myGraph = new SingleGraph(graphName);
		// nodes
		for(int line = 0;line<size;line++) {
			for(int col = 0;col<size;col++) {
				Node node = myGraph.addNode("("+line+","+col+")");
				node.addAttribute("x",col);
				node.addAttribute("y",line);
			}
		}
		// edges
		for(int line=0;line<size;line++) {
			int lineplusone = line+1;
			for(int col = 0;col<size;col++) {
				Node current = myGraph.getNode("("+line+","+col+")");
				int colplusone = col+1;
				int colminusone = col-1;
				if(col < size - 1) {
					Node rightNeighbor = myGraph.getNode("("+line+","+colplusone+")");
					myGraph.addEdge(current.getId()+"--"+rightNeighbor.getId(),current,rightNeighbor); 
				}
				if(line < size -1) {
					Node upNeighbor = myGraph.getNode("("+lineplusone+","+col+")");
					myGraph.addEdge(current.getId()+"--"+upNeighbor.getId(),current,upNeighbor); 
				}
				// moore neighborhood
				if(neighborhood == MOORE) {
					if((col < size - 1) && (line < size - 1)) {
						Node rightUpNeighbor = myGraph.getNode("("+lineplusone+","+colplusone+")");
						myGraph.addEdge(current.getId()+"--"+rightUpNeighbor.getId(),current,rightUpNeighbor); 
					}
					if((col > 0) && (line < size - 1)) {
						Node leftUpNeighbor = myGraph.getNode("("+lineplusone+","+colminusone+")");
						myGraph.addEdge(current.getId()+"--"+leftUpNeighbor.getId(),current,leftUpNeighbor); 
					}
				}
				if(torus) {
					if(line == size-1) {
						Node upNeighbor = myGraph.getNode("("+0+","+col+")");
						myGraph.addEdge(current.getId()+"--"+upNeighbor.getId(),current,upNeighbor); 
					}
					if(col == size -1) {
						Node rightNeighbor = myGraph.getNode("("+line+","+0+")");
						myGraph.addEdge(current.getId()+"--"+rightNeighbor.getId(),current,rightNeighbor); 
					}
				}
			}
		}		
		return myGraph;
	}
	
	// ============ EXECUTION MANAGEMENT TOOLS ===================
	
	/**
	 * this method stops the execution of the code until the user 
	 * hit a key on the keyboard
	 * @param message
	 */
	public final static void hitakey(String message) {
		try {
			System.out.println(message);
			System.in.read();
		} catch(IOException ioe) {}
	}
	
	/**
	 * this method slows down the execution process
	 * @param delay
	 */
	public final static void pause(long delay) {
		try {
			Thread.sleep(delay);
		} catch(InterruptedException ie) {}
	}
	
	// ============ OTHER TOOLS ===================

	/**
	 * this method add integer attributes to edges
	 * 
	 * @param graph
	 * @param min   value of the attribute
	 * @param max   value of the attribute
	 * @param attName   name of the attribute
	 * @param label   boolean for displaying the value of the attribute in the label
	 */
	public final static void allocationValuesToEdges(SingleGraph graph, 
			int min, int max, String attName, boolean label) {
		Random alea = new Random(System.currentTimeMillis());
		for(Edge e: graph.getEdgeSet()) {
			int value = min+alea.nextInt(max-min);
			e.addAttribute(attName,value);
			e.addAttribute("ui.label",""+value);
		}
	}
	
	/**
	 * this method computes the euclidean distance 
	 * between two nodes
	 */
	public final static double distance(Node u, Node v) {
		double ux = u.getAttribute("x");
		double uy = u.getAttribute("y");
		double vx = v.getAttribute("x");
		double vy = v.getAttribute("y");
		return Math.sqrt((ux-vx)*(ux-vx)+(uy-vy)*(uy-vy));
	}
}