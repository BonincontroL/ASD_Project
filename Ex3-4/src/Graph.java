import java.util.*;

public class Graph<V, L extends Comparable<L>> implements AbstractGraph<V, L> {

    boolean directed;
    boolean labelled;
    AbstractCollection<V> nodes; // nodes è una collezione che tiene traccia dei vertici del grafo.
    AbstractCollection<AbstractEdge<V, L>> edges; // è una collezione che tiene traccia degli archi del grafo.
    HashMap<V, Set<Edge<V, L>>> adjacencyList; // adjacencyList è una mappa che rappresenta la lista di adiacenza del
                                               // grafo,associando ogni vertice ai relativi archi adiacenti.

    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean isLabelled() {
        return labelled;
    }

    public boolean addNode(V a) {
        return nodes.add(a);
    }

    
    public boolean addEdge(V a, V b, L l) {
        if (!containsNode(a) || !containsNode(b))
            return false;

        Edge<V, L> edge = new Edge<>(a, b, l);
        edges.add(edge);

        Set<Edge<V, L>> adjacentEdges = adjacencyList.get(a);// recupera l'insieme degli archi adiacenti al vertice a
                                                             // dalla mappa adjacencyList
        if (adjacentEdges == null) { // Se è null, significa che l'insieme non esiste ancora nella mappa.
            adjacentEdges = new HashSet<>();
            adjacencyList.put(a, adjacentEdges);
        }
        adjacentEdges.add(edge);

        if (!directed) {
            Edge<V, L> reverseEdge = new Edge<>(b, a, l); // Creazione dell'arco inverso
            edges.add(reverseEdge); // Aggiunta dell'arco inverso all'insieme di archi

            // Controllo se il vertice b ha già un insieme di archi adiacenti
            Set<Edge<V, L>> adjacentEdgesB = adjacencyList.get(b);
            if (adjacentEdgesB == null) {
                // Se l'insieme non esiste, viene creato un nuovo HashSet vuoto e associato al
                // vertice b nella mappa adjacencyList
                adjacentEdgesB = new HashSet<>();
                adjacencyList.put(b, adjacentEdgesB);
            }
            // Aggiunta dell'arco inverso all'insieme di archi adiacenti del vertice b
            adjacentEdgesB.add(reverseEdge);
        }

        return true;
    }

    public boolean containsNode(V a) {
        return nodes.contains(a);
    }

    public boolean containsEdge(V a, V b) {
        if (!containsNode(a) || !containsNode(b))
            return false;

        for (Edge<V, L> edge : adjacencyList.get(a)) {
            if (edge.getEnd().equals(b))
                return true;
        }
        return false;
    }

    public boolean removeNode(V a) {
        if (!containsNode(a)) {
            return false;
        }

        Set<Edge<V, L>> edgesToRemove = adjacencyList.remove(a); // Rimuove il vertice 'a' dalla lista di adiacenza e
                                                                 // restituisce l'insieme degli archi adiacenti ad a
        if (edgesToRemove != null) {
            for (Edge<V, L> edge : edgesToRemove) {
                V endNode = edge.getEnd();
                Set<Edge<V, L>> adjacentEdges = adjacencyList.get(endNode);
                if (adjacentEdges != null) {
                    adjacentEdges.removeIf(e -> e.getEnd().equals(a)); // Rimuove gli archi adiacenti con estremo 'a'
                }
                edges.remove(edge); // Rimuove l'arco dalla collezione degli archi
            }
        }
        nodes.remove(a); // Rimuove il vertice 'a' dalla collezione dei vertici
        return true;
    }


    public boolean removeEdge(V a, V b) {
        if (!containsNode(a) || !containsNode(b)) {
            return false;
        }

        // Recupera l'insieme degli archi del nodo 'a'
        Set<Edge<V, L>> edgesOfA = adjacencyList.get(a);

        
        Edge<V, L> edgeToRemove = null;
        for (Edge<V, L> edge : edgesOfA) {
            // Verifica se l'estremo dell'arco corrente è il nodo 'b'
            if (edge.getEnd().equals(b)) {
                edgeToRemove = edge;
                break;
            }
        }

        if (edgeToRemove != null) {
            edgesOfA.remove(edgeToRemove); // Rimuove l'arco dall'insieme degli archi del nodo 'a'
            edges.remove(edgeToRemove); // Rimuove l'arco dalla collezione complessiva degli archi

            // Se il grafo è non diretto, si rimuove anche l'arco inverso da 'b' ad 'a'
            if (!isDirected()) {
                Set<Edge<V, L>> edgesOfB = adjacencyList.get(b);
                edgesOfB.removeIf(edge -> edge.getEnd().equals(a));
            }
            return true; 
        }
        return false;
    }


    public int numNodes() {
        return nodes.size();
    }

    public int numEdges() {
        return edges.size();
    }

    public AbstractCollection<V> getNodes() {
        return nodes;
    }

    public AbstractCollection<AbstractEdge<V, L>> getEdges() {
        return edges;
    }

    public AbstractCollection<V> getNeighbours(V a) {
        if (!containsNode(a))
            return null;

        AbstractCollection<V> neighbours = new HashSet<>();
        for (Edge<V, L> edge : adjacencyList.get(a)) {
            neighbours.add(edge.getEnd());
        }
        return neighbours;//neighbours conterrà tutti i nodi adiacenti al nodo 'a' 
    }

    public L getLabel(V a, V b) {
        if (!containsNode(a) || !containsNode(b))
            return null;

        for (Edge<V, L> edge : adjacencyList.get(a)) {
            if (edge.getEnd().equals(b)) {
                return edge.getLabel();
            }
        }
        return null;
    }

}
