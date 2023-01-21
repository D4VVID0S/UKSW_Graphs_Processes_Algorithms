package GraphGeneration;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import java.util.Random;

public class honeycombGrids {
    public static void main(String[] args) {

        // size of the environment (a square) in which nodes are located
        int envSize = 3;
        // number of nodes
        int n = 8;
        // distance threshold
        int d = 10;

		Random alea = new Random(System.currentTimeMillis());
        SingleGraph graph = new SingleGraph("euclidean graph");
        graph.display();
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
        // return graph;
    }

    public final static double distance(Node u, Node v) {
		double ux = u.getAttribute("x");
		double uy = u.getAttribute("y");
		double vx = v.getAttribute("x");
		double vy = v.getAttribute("y");
		return Math.sqrt((ux-vx)*(ux-vx)+(uy-vy)*(uy-vy));
	}
}
