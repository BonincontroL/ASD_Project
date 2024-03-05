
import java.io.*;
import java.util.*;

public class Prim {

    // Restituisce una collezione di oggetti AbstractEdge<V, L> che rappresentano
    // gli archi della minima foresta ricoprente.

    public static <V, L extends Number & Comparable<L>> Collection<AbstractEdge<V, L>> minimumSpanningForest(
            Graph<V, L> graph) {

        // Creazione del comparatore per ordinare gli archi in base al peso
        Comparator<Edge<V, L>> compar = new Comparator<Edge<V, L>>() {
            public int compare(Edge<V, L> o1, Edge<V, L> o2) {
                return o1.getLabel().compareTo(o2.getLabel()); 
            }
        };

        Set<V> visited = new HashSet<>(); // Nodi visitati
        Set<AbstractEdge<V, L>> forestEdges = new HashSet<>(); // Archi della minima foresta ricoprente
        PriorityQueue<Edge<V, L>> queue = new PriorityQueue<>(compar); // Coda di priorità per gli archi

        for (V v : graph.getNodes()) {
            if (!visited.contains(v)) {
                visited.add(v);

                // Aggiungi tutti gli archi uscenti dal nodo nella coda di priorità
                for (V u : graph.getNeighbours(v)) {
                    L l = graph.getLabel(v, u);
                    queue.push(new Edge<>(v, u, l));
                }

                while (!queue.empty() && visited.size() < graph.numNodes()) {
                    Edge<V, L> minEdge = queue.top(); // Prendo l'arco con il peso minimo dalla coda
                    queue.pop();

                    V start = minEdge.getStart();
                    V end = minEdge.getEnd();

                    if (!visited.contains(end)) {
                        visited.add(end); // Aggiungo il nodo di destinazione ai visitati
                        forestEdges.add(minEdge); // Aggiungo l'arco alla collezione degli archi della minima foresta
                                                  // ricoprente

                        // Aggiungo tutti gli archi uscenti dal nodo di destinazione nella coda di
                        // priorità
                        for (V u : graph.getNeighbours(end)) {
                            L l = graph.getLabel(end, u);
                            if (!visited.contains(u)) {
                                queue.push(new Edge<>(end, u, l));
                            }

                        }
                    }
                }
            }
        }
        return forestEdges;
    }

    public static void main(String[] args) {

        /* if (args.length < 1) {
            System.out.println("Error: Missing input file file argument--> java Prim <input file.csv>");
            System.exit(1);
        } */

        /* String inFile = args[0]; */
        String inFile = "italian_dist_graph.csv";
        String outFile = "out.csv";
        Graph<String, Double> graph = new Graph<>(false, true);

        try {
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                graph.addNode(split[0]);
                graph.addNode(split[1]);
                graph.addEdge(split[0], split[1], Double.parseDouble(split[2]));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            System.exit(1);
        }

        long startTime = System.currentTimeMillis();
        Collection<AbstractEdge<String, Double>> primForest = minimumSpanningForest(graph);
        long stopTime = System.currentTimeMillis();
        System.out.println("Tempo: " + (stopTime - startTime) / 1000.0 + "s");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            for (AbstractEdge<String, Double> edge : primForest) {
                bw.write(edge.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Numero di nodi originale: " + graph.numNodes());
        System.out.println("Numero di archi originale: " + graph.numEdges());

        HashSet<String> nodesInForest = new HashSet<>();
        for (AbstractEdge<String, Double> edge : primForest) {
            nodesInForest.add(edge.getStart());
            nodesInForest.add(edge.getEnd());
        }
        int numNodesInForest = nodesInForest.size();
        int numEdges = primForest.size();

        double totalWeight = 0.0;
        Set<AbstractEdge<String, Double>> visitedEdges = new HashSet<>();
        
        for (AbstractEdge<String, Double> edge : primForest) {
            visitedEdges.add(edge);
            totalWeight += edge.getLabel();
        }

        System.out.println("Minima foresta ricoprente:");
        System.out.println("Numero di nodi : " + numNodesInForest);
        System.out.println("Numero di archi: " + numEdges);
        System.out.println("Peso totale della minima foresta ricoprente: " + totalWeight/1000 +" km");
       
    }
}
