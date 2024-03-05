import java.util.*;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    ArrayList<E> queue;
    Comparator<E> compar;
    HashMap<E, Integer> hashMap;

    public PriorityQueue(Comparator<E> compar) {
        this.queue = new ArrayList<>();
        this.compar = compar;
        this.hashMap = new HashMap<>();
    }

    public E getParent(int i) {
        if (i < 0 || i >= queue.size())
            return null;
        int p = (i - 1) / 2;
        if (p < queue.size())
            return queue.get(p);
        else
            return null;
    }

    public E getLeftChild(int i) {
        if (i < 0 || i >= queue.size())
            return null;
        int l = 2 * i + 1;
        if (l < queue.size())
            return queue.get(l);
        else
            return null;
    }

    public E getRightChild(int i) {
        if (i < 0 || i >= queue.size())
            return null;
        int r = 2 * i + 2;
        if (r < queue.size())
            return queue.get(r);
        else
            return null;
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public int getSize() {
        return queue.size();
    }

    public boolean push(E e) {
        if (hashMap.containsKey(e) || e == null)
            return false;

        queue.add(e);
        hashMap.put(e, queue.size() - 1);

        // Riposiziona l'elemento nella radice dell'heap
        int indexNewElem = queue.size() - 1;
        while (indexNewElem > 0) {
            int parentIndex = (indexNewElem - 1) / 2;
            E parent = queue.get(parentIndex);
            if (compar.compare(e, parent) >= 0)
                break;

            queue.set(indexNewElem, parent);
            hashMap.put(parent, indexNewElem);

            indexNewElem = parentIndex;
        }

        queue.set(indexNewElem, e);
        hashMap.put(e, indexNewElem);

        return true;
    }

    public boolean contains(E e) {
        return hashMap.containsKey(e);
    }

    public E top() {
        if (queue.isEmpty())
            return null;
        return queue.get(0);
    }

    public void pop() {
        if (queue.isEmpty())
            return;

        E removedElement = queue.get(0);
        int lastIndex = queue.size() - 1;
        E lastElement = queue.remove(lastIndex);
        hashMap.remove(removedElement);

        if (lastIndex > 0) {
            queue.set(0, lastElement);
            hashMap.put(lastElement, 0);
            heapify(0);
        }
    }

    public boolean remove(E e) {
        Integer index = hashMap.get(e);
        if (index != null) {
            int lastIndex = queue.size() - 1;
            E lastElement = queue.get(lastIndex);

            // Sostituisco l'elemento da rimuovere con l'ultimo elemento
            queue.set(index, lastElement);
            queue.remove(lastIndex);

            // Aggiorno l'indice dell'ultimo elemento nella mappa
            hashMap.put(lastElement, index);
            hashMap.remove(e);

            // Eseguo l'heapify per riposizionare l'elemento nella coda
            heapify(index);
            return true;
        }
        return false;
    }

    public void heapify(int i) {

        if (i >= queue.size())
            return;

        E startElem = getElem(i);
        E leftE = getLeftChild(i);
        E rightE = getRightChild(i);

        E min = min(startElem, leftE, rightE);
        int IndexMin = queue.indexOf(min);
        int IndexElemStart = queue.indexOf(startElem);

        if (IndexMin != i) {
            queue.set(IndexElemStart, min);
            queue.set(IndexMin, startElem);

            hashMap.put(min, IndexElemStart);
            hashMap.put(startElem, IndexMin);

            heapify(IndexMin);
        }
    }

    public E getElem(int i) {
        if (i < 0 || i >= queue.size()) {
            return null;
        }
        return queue.get(i);
    }

    public E min(E p, E l, E r) {
        if (l == null && r == null) {
            return p;
        } else {
            if (l != null) {
                if (compar.compare(p, l) < 0) {
                    if (r == null) {
                        return p;
                    } else {
                        if (compar.compare(p, r) < 0) {
                            return p;
                        } else
                            return r;
                    }
                } else {
                    if (r == null) {
                        return l;
                    } else {
                        if (compar.compare(l, r) < 0) {
                            return l;
                        } else
                            return r;
                    }
                }
            } else {
                if (compar.compare(p, r) < 0) {
                    return p;
                } else
                    return r;
            }
        }
    }

    public void printPriorityQueue() {
        System.out.println(queue);
    }

}
