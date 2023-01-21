import org.graphstream.graph.implementations.SingleGraph;

public class von_neumann {
    
    static SingleGraph graph;

    /*
     * Creation of a basic Moore graph of size 20
     */
    public static void main(String[] args) {
        graph = Tools.GridAndTorus(20, Tools.VON_NEUMANN, false);
        graph.display(false);
    }   
}