#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include <time.h>
#include <string.h>
#include <ctype.h>

typedef struct node
{
    struct node **next; // next è l'array di puntatori in un dato nodo della SkipList;
    size_t size;        // size è il numero di puntatori in un dato nodo della SkipList;
    void *item;         // item è il dato memorizzato in un dato nodo della SkipList.
} Node;

typedef struct skiplist
{
    struct node *head; // head è il primo nodo della SkipList
    size_t max_level;  // max_level è il massimo numero di puntatori che al momento ci sono in un singolo nodo della SkipList
    size_t max_height; // max_height è una costante che definisce il massimo numero di puntatori che possono esserci in un singolo nodo della SkipList

    int (*compare)(const void *, const void *); // compar è il criterio secondo cui ordinare i dati (dati due puntatori a elementi)
} Skiplist;

// newSkipList: crea una skiplist vuota
void new_skiplist(Skiplist **list, size_t max_height, int (*compar)(const void *, const void *));

//new_Node:  crea un nuovo nodo
Node *new_Node(int size);

// clear_skiplist: dealloca tutti i nodi della skiplist e la skiplist stessa.
void clear_skiplist(Skiplist **list);

// insertSkipList: inserisce un elemento nella skiplist
void insert_skiplist(Skiplist *list, void *item);

// randomLevel:  determina il numero di puntatori da includere nel nuovo nodo
int randomLevel(size_t max_height);

// searchSkipList: verifica se un elemento è presente nella skiplist
const void *search_skiplist(Skiplist *list, void *elem);
