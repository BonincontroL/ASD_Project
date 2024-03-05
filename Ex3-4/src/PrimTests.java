import org.junit.*;
import java.util.*;

public class PrimTests {
    private Graph<String, Double> graph;

    @Before
    public void setup() {
        graph = new Graph<>(false, true);
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

        graph.addEdge("A", "B", 10.0);
        graph.addEdge("A", "C", 15.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("B", "E", 8.0);
        graph.addEdge("C", "F", 12.0);
        graph.addEdge("C", "G", 7.0);
        graph.addEdge("D", "H", 9.0);
        graph.addEdge("E", "H", 4.0);
        graph.addEdge("F", "I", 6.0);
        graph.addEdge("G", "J", 11.0);
    }

    @Test
    public void testMinimumSpanningForest() {
        Collection<AbstractEdge<String, Double>> primForest = Prim.minimumSpanningForest(graph);

        Assert.assertEquals(9, primForest.size());
        Assert.assertTrue(containsEdge(primForest, "A", "B", 10.0));
        Assert.assertTrue(containsEdge(primForest, "A", "C", 15.0));
        Assert.assertTrue(containsEdge(primForest, "B", "D", 5.0));
        Assert.assertTrue(containsEdge(primForest, "B", "E", 8.0));
        Assert.assertTrue(containsEdge(primForest, "C", "F", 12.0));
        Assert.assertTrue(containsEdge(primForest, "C", "G", 7.0));
        Assert.assertTrue(containsEdge(primForest, "E", "H", 4.0));
        Assert.assertTrue(containsEdge(primForest, "F", "I", 6.0));
        Assert.assertTrue(containsEdge(primForest, "G", "J", 11.0));
    }

    private boolean containsEdge(Collection<AbstractEdge<String, Double>> edges, String start, String end, double label) {
        for (AbstractEdge<String, Double> edge : edges) {
            String edgeStart = edge.getStart();
            String edgeEnd = edge.getEnd();
            double edgeLabel = edge.getLabel();
    
            if ((edgeStart.equals(start) && edgeEnd.equals(end)) ||
                    (edgeStart.equals(end) && edgeEnd.equals(start))) {
                Assert.assertEquals(label, edgeLabel, 0.001);
                return true;
            }
        }
        return false;
    }
    

    @Test
    public void testTotalConnectedWeight() {
        Collection<AbstractEdge<String, Double>> primForest = Prim.minimumSpanningForest(graph);
        double totalConnectedWeight = 0.0;
        Set<AbstractEdge<String, Double>> visitedEdges = new HashSet<>();

        for (AbstractEdge<String, Double> edge : primForest) {
            if (!visitedEdges.contains(edge)) {
                totalConnectedWeight += edge.getLabel();
                visitedEdges.add(edge);

                if (!graph.isDirected()) {
                    AbstractEdge<String, Double> reverseEdge = new Edge<>(edge.getEnd(), edge.getStart(),
                            edge.getLabel());
                    visitedEdges.add(reverseEdge);
                }
            }
        }

        Assert.assertEquals(78.0, totalConnectedWeight, 0.001);
    }

}
