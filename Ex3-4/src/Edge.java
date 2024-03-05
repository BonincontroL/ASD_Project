public class Edge<V, L extends Comparable<L>> implements AbstractEdge<V, L>, Comparable<Edge<V, L>> {
    //rappresentazione di un arco
    V start;
    V end;
    L label;

    public Edge(V a, V b, L l) {
        start = a;
        end = b;
        label = l;
    }

    public V getStart() {//nodo di partenza dell'arco
        return start;
    }

    public V getEnd() {//nodo di arrivo dell'arco
        return end;
    }

    public L getLabel() { //etichetta dell'arco
        return label;
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////
    public int compareTo(Edge<V, L> o) {
        if (o == null) {
            return 1;
        }
        return label.compareTo(o.getLabel());
    }
    public String toString(){
        return start + "," + end + "," + label;
    }


}
