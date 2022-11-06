// FirstGraph.java
import org.graphstream.graph.implementations.SingleGraph;
public class FirstGraph {
    public static void main(String args[]) {
        SingleGraph graph = new SingleGraph("First Graph");
        graph.display();
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addEdge("ab","a","b",true);
        graph.addEdge("ac", "a","c");
    }
}