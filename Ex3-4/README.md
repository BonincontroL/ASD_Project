# Ex4

# Implementazione di un Grafo Generico

## Classe Edge

È stata creata una classe Edge per rappresentare un arco del grafo. Ogni arco ha un nodo di partenza, un nodo di arrivo e un'etichetta. Questo consente di gestire sia grafi con archi pesati che grafi con archi non pesati.

## Implementazione delle operazioni

Le operazioni principali come l'aggiunta e la rimozione di nodi e archi sono implementate in modo efficiente. Utilizzando la mappa adjacencyList, è possibile accedere agli archi di un nodo in modo diretto e rimuoverli rapidamente.

## Controllo di esistenza dei nodi

Prima di aggiungere un arco tra due nodi, viene verificato che entrambi i nodi esistano nel grafo. Questo garantisce la coerenza della struttura del grafo.

## Gestione degli archi inversi

Quando il grafo non è diretto, viene gestito automaticamente l'aggiunta degli archi inversi. Ciò assicura che le operazioni di rimozione degli archi siano consistenti in entrambe le direzioni.

## Ricerca di nodi e archi

La ricerca di nodi e archi viene effettuata utilizzando le strutture dati appropriate in modo efficiente. La ricerca di archi viene eseguita direttamente sulla lista di adiacenza del nodo, riducendo la complessità computazionale.


# Implementazione dell'Algoritmo di Prim per la Minima Foresta Ricoprente

## Utilizzo di una coda di priorità

La classe `PriorityQueue` viene utilizzata per gestire gli archi in base al loro peso. Ciò consente di selezionare facilmente l'arco con il peso minimo durante l'esecuzione dell'algoritmo.

## Implementazione del comparatore

Viene definito un comparatore personalizzato per ordinare gli archi in base al loro peso. Questo comparatore è utilizzato dalla coda di priorità per mantenere l'ordine corretto degli archi.

## Utilizzo di insiemi per i nodi visitati e gli archi della foresta ricoprente

Gli insiemi `visited` e `forestEdges` vengono utilizzati per tenere traccia dei nodi visitati durante l'esecuzione dell'algoritmo e degli archi che fanno parte della minima foresta ricoprente.

## Aggiunta degli archi nella coda di priorità

Durante l'esecuzione dell'algoritmo, vengono aggiunti nella coda di priorità tutti gli archi uscenti dal nodo corrente, in modo da selezionare l'arco con il peso minimo per espandere la foresta ricoprente.

## Ciclo principale dell'algoritmo

Il ciclo principale dell'algoritmo viene eseguito finché la coda di priorità non è vuota e finché non sono stati visitati tutti i nodi del grafo. Viene estratto l'arco con il peso minimo dalla coda, e se il nodo di destinazione non è ancora stato visitato, viene aggiunto alla foresta ricoprente e vengono aggiunti alla coda di priorità gli archi uscenti da quel nodo.

## Lettura del grafo da un file di input

Il codice legge il grafo da un file di input CSV, in cui ogni riga rappresenta un arco del grafo. Viene utilizzata la classe `BufferedReader` per leggere il file linea per linea e viene utilizzata la classe `String.split()` per separare i valori della riga.

## Scrittura della foresta ricoprente su un file di output

Dopo aver calcolato la minima foresta ricoprente, il codice scrive gli archi della foresta su un file di output CSV. Viene utilizzata la classe `BufferedWriter` per scrivere i dati nel file.

