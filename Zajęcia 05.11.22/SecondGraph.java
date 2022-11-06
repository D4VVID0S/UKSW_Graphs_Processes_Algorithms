// SecondGraph.java
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import static org.graphstream.algorithm.Toolkit.randomNode;
import java.util.Iterator;

public class SecondGraph {
    public static void main(String args[]) {
        SingleGraph graph = new SingleGraph("Second Graph");
        graph.display();
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addEdge("ab", "a", "b");
        graph.addEdge("ac", "a", "c");
        graph.addEdge("cd", "c", "d");
        graph.addEdge("bd", "b", "d");

        // We iterate on the set of nodes of the graph
        for(Node n:graph.getNodeSet()) {
            n.setAttribute("ui.style", "fill-color:red;");
            n.setAttribute("ui.label", "vertex " + n.getId());
        }

        // We iterate on the set of edges on the graph
        for(Edge e:graph.getEdgeSet()) {
            e.setAttribute("ui.style", "fill-color:blue;");
            e.setAttribute("ui.label", "edge " + e.getId());
        }

        // 4.1 Average degree of the graph
        float sum = 0;
        for(Node n:graph.getNodeSet()) {
            sum = sum + n.getDegree();
        }
        float avg = sum / graph.getNodeCount();
        System.out.println("Average degree of Second Graph is: " + avg);

        // 4.2 Neighbors
        Node nodeRandomlyChosen = randomNode(graph);
        nodeRandomlyChosen.setAttribute("ui.style", "fill-color:red;");

        Iterator<Node> theNeighbors = nodeRandomlyChosen.getNeighborNodeIterator();
        while(theNeighbors.hasNext()) {
            Node oneNeighbor = theNeighbors.next();
            oneNeighbor.setAttribute("ui.style", "fill-color:blue;");
            System.out.println(nodeRandomlyChosen + "--->" + "Neighbor ");
        }
    }
}