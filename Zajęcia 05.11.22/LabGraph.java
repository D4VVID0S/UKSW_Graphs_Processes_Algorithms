package y2022_2023.uksw.graphs;

import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;
import java.util.Random;
import java.util.Iterator;

public class LabGraph {
	
	SingleGraph graph;
	Random alea; 
	
	public String graphStyleSheet = "graph { fill-color: white; } "
			+" node { fill-color:black; size:10px;text-alignment:above; }"
			+" edge { fill-color:grey;size:2px;text-size:10; } ";
	
	public LabGraph() {
		init();
		graph.setAttribute("ui.stylesheet",graphStyleSheet);
		//buildGraph();
		buildOtherGraph(20);
		//setCostsAndLabels();
		//setStyle();
		//hitakey("detect big costs");
		//highlightBigCosts();
		System.out.println("average  degree: "+computeAverageDegree());
		hitakey("visit neighbors of 0");
		visitNeighbors(graph.getNode("v_0"));
	}
	
	
	/**
	 * this method visits all the neighbors of the node
	 * given as parameter and changes the color of 
	 * the node and of its neighbors. 
	 * @param u
	 */
	public void visitNeighbors(Node u) {
		u.setAttribute("ui.style","size:20px;fill-color:red;");
		hitakey("visit first neighbor");
		Iterator<Node> neighbors = u.getNeighborNodeIterator();
		while(neighbors.hasNext()) {
			Node v = neighbors.next();
			v.setAttribute("ui.style","size:15px;fill-color:blue;");
			hitakey("visit next");
		}
	}
	
	
	
	/**
	 * for computing an average value for nodes 
	 * we just have to iterate on the set of nodes. 
	 * @return
	 */
	public float computeAverageDegree() {
		float avgDeg = 0f;
		float sumOfDegrees = 0f;
		for(Node u: graph.getNodeSet()) {
			sumOfDegrees+= u.getDegree();
		}
		avgDeg = sumOfDegrees/graph.getNodeCount();
		return avgDeg;
	}
	
		
	public void setCostsAndLabels() {
		for(Node u:graph.getNodeSet()) {
			u.setAttribute("cost",alea.nextInt(100));
			u.setAttribute("ui.label",u.getId()+":"+u.getAttribute("cost"));
		}
	}
	

	public void highlightBigCosts() {
		for(Node u: graph.getNodeSet()) {
			if((int)(u.getAttribute("cost")) > 50) {
				u.setAttribute("ui.style","fill-color:red;shape:cross;");
			}
		}
	}

	
	public void setStyle() {
		// changing the style of the nodes 
		// Be careful, the syntax is really important
		// for ui.style attributes, a ; should be added after every
		// property: fill-color:...; shape:...; size:...;
		for(Node u:graph.getNodeSet()) {
			u.setAttribute("ui.style","fill-color:green;size:50px;text-background-mode:plain;");
		}
		
		for(Edge e:graph.getEdgeSet()) {
			e.setAttribute("ui.style","fill-color:grey;size:2px;text-size:20;");
			e.setAttribute("ui.label","Edge: "+e.getId());
		}
	}
	
	/**
	 * be careful, when you are creating graphs with many nodes,
	 * each node has to have its own identifier
	 * @param n
	 */
	public void buildOtherGraph(int n) {
		for(int i=0;i<n;i++) {
			graph.addNode("v_"+i); // the identifier is v_i
		}
		// create some edges 
		for(int i=0;i<n/2;i++) {
			int iplusone = i+1;
			graph.addEdge(i+"-"+iplusone,"v_"+i,"v_"+iplusone);
		}
		
		for(int i=n/2-1;i<n;i++) {
			graph.addEdge("0-"+i,"v_0","v_"+i);
			graph.addEdge("1-"+i,"v_1","v_"+i);
		}
		
	}
	
	
	public void buildGraph() {
		graph.addNode("a");
		graph.addNode("b");
		graph.addNode("c"); 
		graph.addNode("d"); 
		graph.addEdge("a>b","a","b",true); 
		graph.addEdge("a-c", "a","c");
		graph.addEdge("cd","c","d"); 
		graph.addEdge("bd","b","d");
	}
	
	public void init() {
		graph = new SingleGraph("First Graph"); 
		graph.display();
		alea = new Random(System.currentTimeMillis()); // random seed based on time
	}

	public void hitakey(String message) {
		try {
			System.out.println(message);
			System.in.read();
		} catch(IOException ioe) {}
	}
	
	public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new LabGraph();
	}

}
