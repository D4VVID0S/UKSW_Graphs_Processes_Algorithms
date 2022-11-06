// SecondGraph.java
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

public class Example {
    public static void main(String args[]) {
        SingleGraph graph = new SingleGraph("Relations");
        graph.display();
        graph.addNode("Lina");
        graph.addNode("Lea");
        graph.addNode("Louise");
        graph.addNode("Ali");
        graph.addNode("Theo");
        graph.addNode("Victor");
        graph.addEdge("Lina-Lea", "Lina", "Lea");
        graph.addEdge("Lina-Louise", "Lina", "Louise");
        graph.addEdge("Lea-Louise", "Lea", "Louise");
        graph.addEdge("Lea-Theo", "Lea", "Theo");
        graph.addEdge("Lea-Ali", "Lea", "Ali");
        graph.addEdge("Ali-Theo", "Ali", "Theo");
        graph.addEdge("Victor-Theo", "Victor", "Theo");
        graph.addEdge("Victor", "Victor", "Louise");

        // We iterate on the set of nodes of the graph
        for(Node n:graph.getNodeSet()) {
            n.setAttribute("ui.style", "fill-color:red;");
            n.setAttribute("ui.label", n.getId());
        }

        // We iterate on the set of edges on the graph
        for(Edge e:graph.getEdgeSet()) {
            e.setAttribute("ui.style", "fill-color:blue;");
            e.setAttribute("ui.label", e.getId());
        }
    }
}