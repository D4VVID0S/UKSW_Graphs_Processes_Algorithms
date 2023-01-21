package GraphGeneration;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.algorithm.Toolkit;

public class CompleteTrees {
    public static void main(String[] args) {
        
    	int numbNodes = 30;

        SingleGraph tree = new SingleGraph("TreeGraph");
        tree.display();

        Node startingNode = tree.addNode("V_0");

        for (int index = 1; index <= numbNodes; index++) {
            Node srcNode = Toolkit.randomNode(tree);
            Node destNode = tree.addNode("V_" + index);
            tree.addEdge(srcNode.getId() + "-" + destNode.getId(), srcNode.getId(), destNode.getId());
        }

        // We iterate on the set of nodes of the graph
        for(Node n:tree.getNodeSet()) {
            n.setAttribute("ui.style", "fill-color:green;");
            n.setAttribute("ui.label", n.getId());
        }

        // We iterate on the set of edges on the graph
        for(Edge e:tree.getEdgeSet()) {
            e.setAttribute("ui.style", "fill-color:purple;");
            e.setAttribute("ui.label", e.getId());
        }
    }
}
