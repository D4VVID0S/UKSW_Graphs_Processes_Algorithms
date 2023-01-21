import org.graphstream.graph.Node;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Random;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Stack;

public class TraversalAlgorithms {

	public String defaultStyleSheet = "graph { fill-color: white; } "
			+" node { fill-color:black; size:10px;text-alignment:above; }"
			+" edge { fill-color:grey;size:2px;text-size:10; } ";
	
	public String visitedNodeStyle = "fill-color:red;size:15px;";
	public String inMemoryNodeStyle = "fill-color:green;size:15px;";
	
	// -------------- CHOICE OF THE ALGORITHM -------------
	public final static int BFS = 1;
	public final static int DFS = 2;
	public final static int DIJKSTRA = 3;
	int algorithm = DFS; 

	
	SingleGraph graph;
	Random alea; 
	boolean executionTrace = true;
	ArrayList<Node> priorityQueue; // for Dijkstra
	long delay = 20;
	
	
	public TraversalAlgorithms() {
		init();
		switch(algorithm) {
		case BFS: 
			graph = Tools.GridAndTorus(20,Tools.MOORE,false);
			graph.display(false);
			traversalBFS();
			break;
		case DFS: 
			graph = Tools.GridAndTorus(30,Tools.VON_NEUMANN,false);
			graph.display(false);
			traversalDFS();
			break;
		case DIJKSTRA:
			graph = Tools.geometricGraph(20,300.0,1000);
			graph.display(false);
			Tools.allocationValuesToEdges(graph,1,10,"value",true);
			Node v = Toolkit.randomNode(graph);
			dijkstra(v);
			break;
		}
		Tools.hitakey("Close and Finish");
		System.exit(0);
	}

	/**
	 * transcription of the breadth first search algorithm of the lecture
	 * the data structure is of the type FIFO: First In First Out
	 */
	public void traversalBFS() {
		Node v = Toolkit.randomNode(graph);
		ArrayList<Node> queue = new ArrayList<>();
		queue.add(v);
		Tools.hitakey("graph traversal BFS");
		while(!queue.isEmpty()) {
			Node u = queue.remove(0);
			// mark u as visited 
			u.addAttribute("ui.style",visitedNodeStyle);
			u.addAttribute("visited",true);
			Iterator<Node> neighbors = u.getNeighborNodeIterator();
			while(neighbors.hasNext()) {
				Node w = neighbors.next();
				if(!w.hasAttribute("visited") && !queue.contains(w)) {
					queue.add(w);
					w.addAttribute("ui.style",inMemoryNodeStyle);
				}
			}
			if(executionTrace) Tools.pause(delay); 
		}
	}
	

	/**
	 * transcription of the depth first search algorithm of the lecture
	 * the data structure is of the type LIFO: Last In First Out
	 */
	public void traversalDFS() {
		Node v = Toolkit.randomNode(graph);
		Stack<Node> lifo = new Stack<>();
		lifo.push(v);
		Tools.hitakey("graph traversal DFS");
		while(!lifo.isEmpty()) {
			Node u = lifo.pop();
			// mark u as visited 
			u.addAttribute("ui.style",visitedNodeStyle);
			u.addAttribute("visited",true);
			Iterator<Node> neighbors = u.getNeighborNodeIterator();
			while(neighbors.hasNext()) {
				Node w = neighbors.next();
				if(!w.hasAttribute("visited") && !lifo.contains(w)) {
					lifo.push(w);
					w.addAttribute("ui.style",inMemoryNodeStyle);
				}
			}
			if(executionTrace) Tools.pause(delay); 
		}
	}
	
	

	/**
	 * transcription of Dijkstra algorithm of the lecture
	 * the data structure is a Priority Queue
	 */
	public void dijkstra(Node source) {
		source.addAttribute("distance",0);
		priorityQueue = new ArrayList<>();
		priorityQueue.add(source);
		Tools.hitakey("Dijstra");
		while(!priorityQueue.isEmpty()) {
			Node u = priorityQueue.remove(0);
			// mark u as visited and add a label to display the distance to the source node
			u.addAttribute("ui.style",visitedNodeStyle);
			u.addAttribute("visited",true);
			u.addAttribute("ui.label",(int)u.getAttribute("distance")+"");
			Iterator<Node> neighbors = u.getNeighborNodeIterator();
			while(neighbors.hasNext()) {
				Node w = neighbors.next();
				if(!w.hasAttribute("visited")) {
					// the distance between the source node and w, through u is computed
					Edge e = u.getEdgeBetween(w);
					int edgeValue = e.getAttribute("value");
					int distanceToSource = (int)u.getAttribute("distance")+edgeValue;
					// if w was not present, it is inserted in the priority queue with this 
					// value of the distance 
					if(!priorityQueue.contains(w)) {
						w.addAttribute("distance",distanceToSource);
						insert(w);
					} else {
						// if w is already present, a comparison is done and if the new
						// value is better, w has to be re-inserted at the right position
						if((int)w.getAttribute("distance") > distanceToSource) {
							priorityQueue.remove(w);
							w.addAttribute("distance",distanceToSource);
							insert(w);
						} 
					}
				}
			}
			if(executionTrace) Tools.hitakey("nastepny wezel"); 
		}
	}
	
	
	/** 
	 * insertion of the node at the right position within 
	 * the priority queue with respect to its distance to 
	 * the source node. 
	 * @param w
	 * @param distance
	 */
	public void insert(Node w) {
		int position = 0;
		boolean found = false;
		int distance = (int)w.getAttribute("distance");
		while(!found && (position < priorityQueue.size())) {
			Node z = priorityQueue.get(position);
			if((int)z.getAttribute("distance") < distance) position++;
			else {
				found = true;
				priorityQueue.add(position,w);
			}
		}
		if(!found) {
			priorityQueue.add(w);
		}
		// for the visualization
		w.addAttribute("ui.style",inMemoryNodeStyle);
		w.addAttribute("ui.label",distance+"");
	}
	
	
	
	// ========================= INIT =======================
	
	public void init() {
		graph = new SingleGraph("simple graph");
		graph.addAttribute("ui.stylesheet",defaultStyleSheet);
		alea = new Random(System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new TraversalAlgorithms();
	}
	

	

}