import org.graphstream.graph.implementations.SingleGraph;

public class moore {
    
    static SingleGraph graph;

    /*
     * Creation of a basic Moore graph of size 20
     */
    public static void main(String[] args) {
        graph = Tools.GridAndTorus(20, Tools.MOORE, false);
        graph.display(false);
    }   
}