import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

public class PolskieAutostrady {
    public static void main(String[] args) {
        SingleGraph graph = new SingleGraph("Autostrady");
        graph.display();
        graph.addNode("Gdańsk");
        graph.addNode("Szczecin");
        graph.addNode("Olsztyn");
        graph.addNode("Bydgoszcz");
        graph.addNode("Toruń");
        graph.addNode("Białystok");
        graph.addNode("Gorzów Wielkopolski");
        graph.addNode("Poznań");
        graph.addNode("Warszawa");
        graph.addNode("Zielona Góra");
        graph.addNode("Łódź");
        graph.addNode("Lublin");
        graph.addNode("Wrocław");
        graph.addNode("Opole");
        graph.addNode("Katowice");
        graph.addNode("Kraków");
        graph.addNode("Rzeszów");
        graph.addNode("Kielce");

        graph.addEdge("Gdańsk-Szczecin", "Gdańsk", "Szczecin", false);
        graph.addEdge("Gdańsk-Olsztyn", "Gdańsk", "Olsztyn", false);
        graph.addEdge("Gdańsk-Toruń", "Gdańsk", "Toruń", false);

        graph.addEdge("Szczecin-Bydgoszcz", "Szczecin", "Bydgoszcz", false);
        graph.addEdge("Szczecin-Gorzów Wielkopolski", "Szczecin", "Gorzów Wielkopolski", false);

        graph.addEdge("Bydgoszcz-Toruń", "Bydgoszcz", "Toruń", false);
        graph.addEdge("Bydgoszcz-Poznań", "Bydgoszcz", "Poznań", false);

        graph.addEdge("Poznań-Gorzów Wielkopolski", "Poznań", "Gorzów Wielkopolski", false);
        graph.addEdge("Poznań-Wrocław", "Poznań", "Wrocław", false);
        graph.addEdge("Poznań-Łódź", "Poznań", "Łódź", false);

        graph.addEdge("Zielona Góra-Gorzów Wielkopolski", "Zielona Góra", "Gorzów Wielkopolski", false);
        graph.addEdge("Zielona Góra-Poznań", "Zielona Góra", "Poznań", false);
        graph.addEdge("Zielona Góra-Wrocław", "Zielona Góra", "Wrocław", false);

        graph.addEdge("Wrocław-Łódź", "Wrocław", "Łódź", false);
        graph.addEdge("Wrocław-Opole", "Wrocław", "Opole", false);

        graph.addEdge("Katowice-Opole", "Katowice", "Opole", false);
        graph.addEdge("Katowice-Łódź", "Katowice", "Łódź", false);
        graph.addEdge("Katowice-Kraków", "Katowice", "Kraków", false);

        graph.addEdge("Kraków-Kielce", "Kraków", "Kielce", false);
        graph.addEdge("Kraków-Rzeszów", "Kraków", "Rzeszów", false);

        graph.addEdge("Kielce-Warszawa", "Kielce", "Warszawa", false);
        graph.addEdge("Kielce-Lublin", "Kielce", "Lublin", false);
        graph.addEdge("Kielce-Rzeszów", "Kielce", "Rzeszów", false);

        graph.addEdge("Lublin-Warszawa", "Lublin", "Warszawa", false);
        graph.addEdge("Lublin-Rzeszów", "Lublin", "Rzeszów", false);
        graph.addEdge("Lublin-Białystok", "Lublin", "Białystok", false);
        graph.addEdge("Lublin-Łódź", "Lublin", "Łódź", false);
        graph.addEdge("Lublin-Katowice", "Lublin", "Katowice", false);

        graph.addEdge("Warszawa-Łódź", "Warszawa", "Łódź", false);
        graph.addEdge("Warszawa-Toruń", "Warszawa", "Toruń", false);
        graph.addEdge("Warszawa-Olsztyn", "Warszawa", "Olsztyn", false);
        graph.addEdge("Warszawa-Białystok", "Warszawa", "Białystok", false);

        graph.addEdge("Łódź-Toruń", "Łódź", "Toruń", false);

        // We iterate on the set of nodes of the graph
        for(Node n:graph.getNodeSet()) {
        n.setAttribute("ui.style", "fill-color:red;");
        n.setAttribute("ui.label", n.getId());
        }

        // We iterate on the set of edges on the graph
        for(Edge e:graph.getEdgeSet()) {
            e.setAttribute("ui.style", "fill-color:green;");
            e.setAttribute("ui.label", e.getId());
        }

        float sum = 0;
        for(Node n:graph.getNodeSet()) {
            sum = sum + n.getDegree();
        }
        float avg = sum / graph.getNodeCount();
        System.out.println("Average degree of graph " + graph + " is: " + avg);
    }
}