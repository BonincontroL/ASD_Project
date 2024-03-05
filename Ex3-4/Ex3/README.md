# Ex3

## Complessità delle funzioni

Le complessità delle varie funzioni della classe PriorityQueue sono:

- `getParent(int i)`: complessità O(1) - restituisce il genitore dell'elemento nell'heap dato l'indice.
- `getLeftChild(int i)`: complessità O(1) - restituisce il figlio sinistro dell'elemento nell'heap dato l'indice.
- `getRightChild(int i)`: complessità O(1) - restituisce il figlio destro dell'elemento nell'heap dato l'indice.
- `empty()`: complessità O(1) - verifica se la coda di priorità è vuota.
- `push(E e)`: complessità O(logN) - inserisce un elemento nella coda di priorità e riposiziona l'elemento per mantenere la proprietà dell'heap.
- `contains(E e)`: complessità O(1) - verifica se un elemento è presente nella coda di priorità utilizzando la mappa hashMap.
- `top()`: complessità O(1) - restituisce l'elemento di massima (o minima) priorità senza rimuoverlo dalla coda.
- `pop()`: complessità O(logN) - rimuove l'elemento di massima (o minima) priorità dalla coda e riposiziona gli elementi per mantenere la proprietà dell'heap.
- `remove(E e)`: complessità O(logN) - rimuove l'elemento specificato dalla coda di priorità e riposiziona gli elementi per mantenere la proprietà dell'heap.
- `heapify(int i)`: complessità O(logN) - ripristina la proprietà dell'heap partendo dal nodo specificato.
- `getElem(int i)`: complessità O(1) - restituisce l'elemento nell'heap dato l'indice.
- `min(E p, E l, E r)`: complessità O(1) - restituisce l'elemento minimo tra tre elementi dati.
- `printPriorityQueue()`: complessità O(N) - stampa gli elementi presenti nella coda di priorità.

La complessità complessiva dell'algoritmo dipende dal numero di elementi presenti nella coda di priorità (N), quindi le operazioni di inserimento, rimozione e heapify hanno una complessità logaritmica rispetto al numero di elementi nella coda.

## Scelte implementative

Per l'implementazione della struttura dati PriorityQueue è stato scelto di utilizzare uno heap minimo, in quanto la struttura dati sarà utilizzata nell'esercizio 4, che richiede l'implementazione di un grafo con l'algoritmo di Prim per calcolare il minimo albero ricoprente.

