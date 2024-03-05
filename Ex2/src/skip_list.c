#include "skip_list.h"

void new_skiplist(Skiplist **list, size_t max_height, int (*compar)(const void *, const void *))
{
    (*list) = (Skiplist *)malloc(sizeof(Skiplist));
    (*list)->head = new_Node(max_height);
    (*list)->compare = compar;
    (*list)->max_level = 0;
    (*list)->max_height = max_height;
}

Node *new_Node(int size)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->next = (Node **)malloc(sizeof(Node *) * (size + 1));
    newNode->size = size;

    for (int i = newNode->size; i >= 0; i--)
    {
        newNode->next[i] = NULL;
    }

    return newNode;
}

void clear_skiplist(Skiplist **list)
{ // dealloca tutti i nodi della skiplist e la skiplist stessa.
    if (*list)
    {
        Node *current = (*list)->head;
        Node *tmp;

        while (current != NULL)
        {
            tmp = current;
            current = current->next[0];
            free(tmp->next);
            free(tmp);
        }
        // Libera la skiplist stessa
        free(*list);
        *list = NULL;
    }
}

void insert_skiplist(Skiplist *list, void *item)
{
    
    int random_level = randomLevel(list->max_height);
    Node *new = new_Node(random_level);
    new->item = item;

    
    if (new->size > list->max_level)
        list->max_level = new->size;

    Node *tmp = list->head;
    for (int k = list->max_level; k >= 0; k--)
    {
        
        if (tmp->next[k] == NULL || list->compare(item, tmp->next[k]->item) < 0)
        {
            if (k <= new->size)
            {
                new->next[k] = tmp->next[k];
                tmp->next[k] = new;
            }
        }
        else // Se invece viene raggiunto il fondo della lista o viene trovato un elemento con un valore uguale o maggiore di quello del nuovo elemento, la funzione passa al livello inferiore e continua a cercare.
        {
            tmp = tmp->next[k];
            k++;
        }
    }
}

/*
    La funzione randomLevel() utilizza la funzione rand() per generare un numero casuale nell'intervallo [0, RAND_MAX]
    e lo divide per RAND_MAX per ottenere un valore casuale tra 0 e 1.
    La funzione quindi confronta questo valore con la soglia di 0.5 per determinare se
    generare un nuovo puntatore di livello superiore nella skip list.

    Il confronto tra il valore casuale e la soglia di 0.5 assicura che la probabilità di generare
    un nuovo puntatore di livello superiore nella skip list sia del 50%, ovvero un valore equilibrato
    che assicura che la struttura della skip list sia efficiente e bilanciata.
*/
int randomLevel(size_t max_height)
{
    int level = 0;

    while (((double)rand() / RAND_MAX) < 0.5 && level < max_height)
    {
        level = level + 1;
    }
    return level;
}


const void *search_skiplist(Skiplist *list, void *elem)
{
    Node *tmp = list->head;

    for (int i = list->max_level; i >= 0; i--)
    {
        while (tmp->next[i] != NULL && list->compare(tmp->next[i]->item, elem) < 0)
        {
            // Se l'elemetno che cerco è più grande, avanzo nella lista
            tmp = tmp->next[i];
        }
    }

    tmp = tmp->next[0];
    if (tmp != NULL && list->compare(tmp->item, elem) == 0)
    {
        return tmp->item;
    }
    else
    {
        return NULL;
    }
}
