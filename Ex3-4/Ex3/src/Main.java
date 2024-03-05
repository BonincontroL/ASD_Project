import java.util.*;
public class Main {
    public static void main(String[] args) {
        Comparator<Integer> compar = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2)
                    return 1;
                else if (o1 < o2)
                    return -1;
                else
                    return 0;
            }
        };

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(compar);
        for(int i=0;i<1000;i++){
            priorityQueue.push(i);
        }
        priorityQueue.printPriorityQueue();
        System.out.println(priorityQueue.empty());
        System.out.println(priorityQueue.getSize());
        System.out.println("Elemento in cima alla coda: "  + priorityQueue.top());
        priorityQueue.pop();
        System.out.println("Elemento in cima alla coda: "  + priorityQueue.top());
        System.out.println(priorityQueue.contains(12)); 
        System.out.println(priorityQueue.contains(2)); 
        priorityQueue.remove(122);
        System.out.println(priorityQueue.contains(122)); 
        priorityQueue.printPriorityQueue();


    }
}

