import java.io.IOException;
import java.util.Random;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class neumann_moore_torus_labs {

	public String defaultStyleSheet = "graph { fill-color: white; } "
			+" node { fill-color:black; size:5px;text-alignment:above; }"
			+" edge { fill-color:grey;size:2px;text-size:10; } ";
	
	SingleGraph graph;
	Random alea;
	int nbNodes = 100;
	int gridSize = 20;
	
	public neumann_moore_torus_labs() {
		init();
		boolean torus = true;
		//graph.display(torus);
		//ringGeneration(); 
		//fullConnected();$
		//tree();
		//grid(torus);
		//randomFromFullConnected((int)(0.85*nbNodes*(nbNodes-1)/2)); // 85% of edges will be removed
		//randomByAddingEdges((int)(0.15*nbNodes*(nbNodes-1)/2)); // 15% of all possible edges 
		for(int nb=20;nb<100;nb++) {
			for(int p=1;p<10;p++) {
				erdosRenyi(nb,(double)p/20);
				graph.clear();
			}
		}
	}
	
	// ====================== GENERATORS =====================
	
	/**
	 * Erdos Renyi procedure for creating random graphs
	 * It is based on a probability of creating each edge
	 * @param probaOfExistence
	 */
	public void erdosRenyi(int n, double probaOfExistence) {
		ConnectedComponents cc = new ConnectedComponents();
		cc.init(graph);
		for(int i=0;i<n;i++) {
			graph.addNode("v_"+i);
		}
		//hitakey("add edges");
		// test each possible edge (i,j) only once (j,i) will not be tested
 		for(int s=0;s<n-1;s++) {
			for(int d=s+1;d<n;d++) {
				if(alea.nextDouble() < probaOfExistence) {
					//System.out.println("edge: (v_"+s+",v_"+d+" created");
					graph.addEdge("v_"+s+"-v_"+d,"v_"+s,"v_"+d);
				} else {
					//System.out.println("edge: (v_"+s+",v_"+d+" NOT created");
				}
			}
		}
 		System.out.println(n+" "+probaOfExistence+" "+averageDegree()+" "+cc.getConnectedComponentsCount());
	}
	
	
	
	/**
	 * random graph creation from a set of isolated nodes
	 * @param nbEdgesToAdd
	 */
	public void randomByAddingEdges(int nbEdgesToAdd) {
		for(int i=0;i<nbNodes;i++) {
			graph.addNode("v_"+i);
		}
		hitakey("add edges");
		if(nbEdgesToAdd >= nbNodes*(nbNodes-1)/2) { // too many edges
			nbEdgesToAdd = 0;
		}
		for(int e=0;e<nbEdgesToAdd;e++) {
			boolean created = false;
			while(!created) {
				Node v1 = Toolkit.randomNode(graph);
				Node v2 = Toolkit.randomNode(graph);
				if((v1.getId() != v2.getId()) && (!v1.hasEdgeBetween(v2))) {
					graph.addEdge(v1.getId()+"-"+v2.getId(),v1.getId(),v2.getId());
					created = true;
				}
			}
		}
	}
	
	
	/**
	 * random graph creation from a full connected graph
	 * @param nbEdgesToRemove
	 */
	public void randomFromFullConnected(int nbEdgesToRemove) {
		fullConnected();
		hitakey("remove edges");
		for(int r=0;r<nbEdgesToRemove;r++) {
			graph.removeEdge(Toolkit.randomEdge(graph));
		}
	}

	/**
	 * for generating a grid we only need the number of nodes or the grid size. 
	 * The number of edges is given by construction (and by definition)
	 */
	public void grid(boolean torus) {
		hitakey("add nodes");
		for(int line=0;line<gridSize;line++) {
			for(int col=0;col<gridSize;col++) {
				Node u = graph.addNode(line+","+col);
				u.addAttribute("x",line);
				u.addAttribute("y",col);
				//u.addAttribute("ui.label",u.getId());
			}
		}
		// add edges link each node to two neighbors located at:
		// line,col+1 and line+1,col
		hitakey("add links");
		for(int line=0;line<gridSize;line++) {
			for(int col=0;col<gridSize;col++) {
				String idfSrc = line+","+col;
				Node src = graph.getNode(idfSrc);
				int lineplusone = line+1;
				if(torus) lineplusone = lineplusone%gridSize;
				int colplusone = col+1;
				if(torus) colplusone = colplusone%gridSize;
				String idfEast = lineplusone+","+col;
				String idfNorth = line+","+colplusone;
				if(lineplusone < gridSize)
					graph.addEdge(idfSrc+"-"+idfEast,idfSrc,idfEast);
				if(colplusone < gridSize)
					graph.addEdge(idfSrc+"-"+idfNorth,idfSrc,idfNorth);
			}	
		}
	}
	
	
	
	/**
	 * for generating a ring we only need the number of nodes. 
	 * The number of edges is given by definition: n nodes => n edges
	 */
	public void ringGeneration() {
		Node startingNode = graph.addNode("v_0");
		Node previousNode = startingNode;
		hitakey("generation");
		for(int index=1;index<nbNodes;index++) {
			Node v = graph.addNode("v_"+index);
			graph.addEdge(previousNode.getId()+"-"+v.getId(),previousNode.getId(),v.getId());
			previousNode = v;
		}
		graph.addEdge(previousNode.getId()+"-"+startingNode.getId(),
				previousNode.getId(),startingNode.getId());
	}
	

	/**
	 * for generating a full connected graph we only need the number of nodes. 
	 * The number of edges is given by definition: n nodes => (n(n-1))/2 edges
	 */
	public void fullConnected() {
		// creation of the nodes
		for(int index=0;index<nbNodes;index++) {
			graph.addNode("v_"+index);
		}
		//hitakey("addition of edges");
		// creation of the edges between nodes
		for(Node v:graph.getNodeSet()) {
			for(Node u:graph.getNodeSet()) {
				// verify that u != v (no loop) and that no edge already exist between the two nodes (1-graph)
				if((u.getId() != v.getId()) && (!u.hasEdgeBetween(v))) {
					graph.addEdge(u.getId()+"-"+v.getId(),u.getId(),v.getId());
				}
			}
			//hitakey("processing next node");
		}
	}
	

	/**
	 * for generating a tree graph we only need the number of nodes. 
	 * The number of edges is given by definition: n nodes => n-1 edges
	 */
	public void tree() {
		Node startingNode = graph.addNode("v_0");
		hitakey("add nodes");
		for(int index=1;index<nbNodes;index++) {
			Node srcNode = Toolkit.randomNode(graph);
			Node destNode = graph.addNode("v_"+index);
			graph.addEdge(srcNode.getId()+"-"+destNode.getId(),srcNode.getId(),destNode.getId());
			pause(100);
		}
	}
	
	
	
	// ========================= INIT and TOOLS =======================
	
	public double averageDegree() {
		int sum = 0;
		for(Node u:graph.getNodeSet()) sum+=u.getDegree();
		return (double)sum/graph.getNodeCount();
	}
	
	
	
	public void init() {
		graph = new SingleGraph("simple graph");
		graph.addAttribute("ui.stylesheet",defaultStyleSheet);
		graph.addAttribute("ui.antialias",true);
		alea = new Random(System.currentTimeMillis());
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
	
	
	public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new neumann_moore_torus_labs();
	}

}