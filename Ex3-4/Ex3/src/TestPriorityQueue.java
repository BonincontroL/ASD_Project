
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Assert;

public class TestPriorityQueue {

    Comparator<Integer> compar = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2)
                return 1;
            else if (o1 < o2)
                return -1;
            else
                return 0;
        }

    };

    Comparator<String> stringCompar = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    };

    @Test
    public void testPushAndTop() {
        // Creazione di una coda con un comparatore per gli interi in ordine decrescente
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(compar);

        assertTrue(priorityQueue.empty());

        // Aggiunta di elementi alla coda prioritaria
        priorityQueue.push(5);
        priorityQueue.push(2);
        priorityQueue.push(8);
        priorityQueue.push(1);

        assertFalse(priorityQueue.empty());

        // Verifica che l'elemento in cima alla coda prioritaria sia corretto
        assertEquals(Integer.valueOf(1), priorityQueue.top());
    }

    @Test
    public void testPop() {

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(stringCompar);

        priorityQueue.push("Arancia");
        priorityQueue.push("banana");
        priorityQueue.push("fragola");

        // Verifica che il primo elemento dopo l'operazione pop sia "banana"
        priorityQueue.pop();
        assertEquals("banana", priorityQueue.top());

        // Verifica che la dimensione della coda sia 2 dopo due operazioni pop
        priorityQueue.pop();
        priorityQueue.pop();
        assertEquals(0, priorityQueue.getSize());
    }

    @Test
    public void emptyOnEmptyQueue() {
        PriorityQueue<Integer> intPQ = new PriorityQueue<>(compar);
        assertTrue(intPQ.empty());

        PriorityQueue<String> stringPQ = new PriorityQueue<>(stringCompar);
        assertTrue(stringPQ.empty());

    }

    @Test
    public void emptyOnNotEmptyQueue() {
        PriorityQueue<Integer> intPQ = new PriorityQueue<>(compar);
        intPQ.push(10);
        assertFalse(intPQ.empty());

        PriorityQueue<String> stringPQ = new PriorityQueue<>(stringCompar);
        stringPQ.push("Hello");
        assertFalse(stringPQ.empty());
    }
    @Test
    public void testPushAndPop() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(compar);

        // Test inserimento
        pq.push(10);
        pq.push(5);
        pq.push(8);
        pq.push(3);
        pq.push(12);

        Assert.assertEquals(3, (int) pq.top());
        pq.pop();
        Assert.assertEquals(5, (int) pq.top());
        pq.pop();
        Assert.assertEquals(8, (int) pq.top());
        pq.pop();
        Assert.assertEquals(10, (int) pq.top());
        pq.pop();
        Assert.assertEquals(12, (int) pq.top());
        pq.pop();
        Assert.assertTrue(pq.empty());

        pq.push(15);
        pq.push(7);
        pq.push(2);
        pq.push(9);
        pq.push(0);
        pq.push(4);
        pq.push(1);

        Assert.assertEquals(0, (int) pq.top());
    }


}
