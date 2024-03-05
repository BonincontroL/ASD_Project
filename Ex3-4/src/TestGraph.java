import org.junit.*;
import java.util.*;


public class TestGraph {


    public static void testGraph() {
        Graph<String, Integer> graph = new Graph<>(true, true);

       
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

       
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 3);
        graph.addEdge("D", "E", 4);
        graph.addEdge("E", "A", 5);

       
        assert graph.isDirected();
        assert graph.isLabelled();
        assert graph.numNodes() == 5;
        assert graph.numEdges() == 10;

       
        assert graph.containsNode("A");
        assert graph.containsNode("B");
        assert graph.containsNode("C");
        assert graph.containsNode("D");
        assert graph.containsNode("E");
        assert graph.containsEdge("A", "B");
        assert graph.containsEdge("B", "C");
        assert graph.containsEdge("C", "D");
        assert graph.containsEdge("D", "E");
        assert graph.containsEdge("E", "A");
        assert !graph.containsEdge("A", "D");
        assert !graph.containsEdge("B", "A");

        
        Collection<String> neighborsA = graph.getNeighbours("A");
        assert neighborsA.size() == 1;
        assert neighborsA.contains("B");

        Collection<String> neighborsB = graph.getNeighbours("B");
        assert neighborsB.size() == 1;
        assert neighborsB.contains("C");

        Collection<String> neighborsC = graph.getNeighbours("C");
        assert neighborsC.size() == 1;
        assert neighborsC.contains("D");

        Collection<String> neighborsD = graph.getNeighbours("D");
        assert neighborsD.size() == 1;
        assert neighborsD.contains("E");

        Collection<String> neighborsE = graph.getNeighbours("E");
        assert neighborsE.size() == 1;
        assert neighborsE.contains("A");

       
        assert graph.removeEdge("A", "B");
        assert !graph.containsEdge("A", "B");
        assert !graph.containsEdge("B", "A");
        assert graph.numEdges() == 8;

        assert graph.removeNode("C");
        assert !graph.containsNode("C");
        assert !graph.containsEdge("B", "C");
        assert !graph.containsEdge("C", "D");
        assert graph.numNodes() == 4;
        assert graph.numEdges() == 6;

    }


    public static void testGraph2() {

        Graph<String, Integer> graph = new Graph<>(false, true);
      
       graph.addNode("A");
       graph.addNode("B");
       graph.addNode("C");
       graph.addNode("D");
       graph.addNode("E");

       
       graph.addEdge("A", "B", 1);
       graph.addEdge("B", "C", 2);
       graph.addEdge("C", "D", 3);
       graph.addEdge("D", "E", 4);
       graph.addEdge("E", "A", 5);

       // Test graph properties
       assert !graph.isDirected();
       assert graph.isLabelled();
       assert graph.numNodes() == 5;
       assert graph.numEdges() == 10;

       
       assert graph.containsNode("A");
       assert graph.containsNode("B");
       assert graph.containsNode("C");
       assert graph.containsNode("D");
       assert graph.containsNode("E");
       assert graph.containsEdge("A", "B");
       assert graph.containsEdge("B", "A");
       assert graph.containsEdge("B", "C");
       assert graph.containsEdge("C", "B");
       assert graph.containsEdge("C", "D");
       assert graph.containsEdge("D", "C");
       assert graph.containsEdge("D", "E");
       assert graph.containsEdge("E", "D");
       assert graph.containsEdge("E", "A");
       assert graph.containsEdge("A", "E");
       assert !graph.containsEdge("A", "D");
       assert !graph.containsEdge("B", "D");

       // Test getting neighbors
       Collection<String> neighborsA = graph.getNeighbours("A");
       assert neighborsA.size() == 2;
       assert neighborsA.contains("B");
       assert neighborsA.contains("E");

       Collection<String> neighborsB = graph.getNeighbours("B");
       assert neighborsB.size() == 2;
       assert neighborsB.contains("A");
       assert neighborsB.contains("C");

       Collection<String> neighborsC = graph.getNeighbours("C");
       assert neighborsC.size() == 2;
       assert neighborsC.contains("B");
       assert neighborsC.contains("D");

       Collection<String> neighborsD = graph.getNeighbours("D");
       assert neighborsD.size() == 2;
       assert neighborsD.contains("C");
       assert neighborsD.contains("E");

       Collection<String> neighborsE = graph.getNeighbours("E");
       assert neighborsE.size() == 2;
       assert neighborsE.contains("D");
       assert neighborsE.contains("A");

       // Test removing nodes and edges
       assert graph.removeEdge("A", "B");
       assert !graph.containsEdge("A", "B");
       assert !graph.containsEdge("B", "A");
       assert graph.numEdges() == 8;

       assert graph.removeNode("C");
       assert !graph.containsNode("C");
       assert !graph.containsEdge("B", "C");
       assert !graph.containsEdge("C", "B");
       assert !graph.containsEdge("C", "D");
       assert !graph.containsEdge("D", "C");
       assert graph.numNodes() == 4;
       assert graph.numEdges() == 5;
    }


    public Graph<Integer, Integer> graph;

    @Before
    public void setUp() {
        graph = new Graph<>(false, false);
    }

    @Test
    public void testAddNode() {
        Assert.assertTrue(graph.addNode(1));
        Assert.assertTrue(graph.containsNode(1));
    }

    @Test
    public void testAddEdge() {
        graph.addNode(1);
        graph.addNode(2);

        Assert.assertTrue(graph.addEdge(1, 2, 10));
        Assert.assertTrue(graph.containsEdge(1, 2));
        Assert.assertTrue(graph.containsEdge(2, 1));
    }

    @Test
    public void testRemoveNode() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 10);

        Assert.assertTrue(graph.removeNode(1));
        Assert.assertFalse(graph.containsNode(1));
        Assert.assertFalse(graph.containsEdge(1, 2));
    }

    @Test
    public void testRemoveEdge() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 10);

        Assert.assertTrue(graph.removeEdge(1, 2));
        Assert.assertFalse(graph.containsEdge(1, 2));
    }

    @Test
    public void testNumNodes() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);

        Assert.assertEquals(3, graph.numNodes());
    }

    @Test
    public void testNumEdges() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 20);
        Assert.assertEquals(4, graph.numEdges());
    }

    @Test
    public void testGetNodes() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);

        AbstractCollection<Integer> nodes = graph.getNodes();
        Assert.assertEquals(3, nodes.size());
        Assert.assertTrue(nodes.contains(1));
        Assert.assertTrue(nodes.contains(2));
        Assert.assertTrue(nodes.contains(3));
    }

    @Test
    public void testGetNeighbours() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 20);
        graph.addEdge(1, 4, 30);

        AbstractCollection<Integer> neighbours = graph.getNeighbours(1);
        Assert.assertEquals(3, neighbours.size());
        Assert.assertTrue(neighbours.contains(2));
        Assert.assertTrue(neighbours.contains(3));
        Assert.assertTrue(neighbours.contains(4));
    }

    @Test
    public void testGetLabel() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 10);

        Integer label = graph.getLabel(1, 2);
        Assert.assertEquals(10, label.intValue());
    }

    public static void main(String[] args) {
        testGraph();
        testGraph2();
    }
}
