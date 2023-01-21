import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
// import static org.graphstream.algorithm.Toolkit.randomNode;
// import java.util.Iterator;

public class Exercise_1 {
    public static void main(String[] args) {
        SingleGraph graph = new SingleGraph("Graph 1.1");
        graph.display();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addNode("I");
        graph.addNode("J");

        graph.addEdge("AB", "A", "B", false);
        graph.addEdge("BC", "B", "C", false);
        graph.addEdge("CD", "C", "D", false);
        graph.addEdge("DA", "D", "A", false);
        graph.addEdge("AC", "A", "C", false);
        graph.addEdge("BD", "B", "D", false);

        graph.addEdge("EB", "E", "B", false);
        graph.addEdge("FB", "F", "B", false);

        graph.addEdge("FG", "F", "G", false);
        graph.addEdge("FH", "F", "H", false);

        graph.addEdge("HI", "H", "I", false);
        graph.addEdge("HJ", "H", "J", false);
        graph.addEdge("IJ", "I", "J", false);

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
    }
}
