#include <assert.h>
#include <time.h>

#include "skip_list.h"
#define MAX_HEIGHT 30

int int_compar(const void *a, const void *b)
{
    return *(int *)a - *(int *)b;
}
int str_compar(const void *a, const void *b)
{
    return strcmp((char *)a, (char *)b);
}

void test_empty_skiplist()
{
    int not_in_list = 100;
    Skiplist *list;
    new_skiplist(&list, 30, int_compar);
    assert(list != NULL);
    assert(list->head != NULL);
    assert(list->max_level == 0);
    assert(list->max_height == 30);
    assert(list->compare == int_compar);
    assert(search_skiplist(list, &not_in_list) == NULL);
    clear_skiplist(&list);
}

// Testa la creazione di una skip list
void test_create_skiplist()
{
    Skiplist *list = NULL;
    new_skiplist(&list, MAX_HEIGHT, int_compar);
    assert(list != NULL);
    assert(list->head != NULL);
    assert(list->max_level == 0);
    assert(list->max_height == MAX_HEIGHT);
    assert(list->compare == int_compar);
    clear_skiplist(&list);
}

void test_clear_skiplist()
{
    Skiplist *list;
    new_skiplist(&list, MAX_HEIGHT, int_compar);
    int n = 10000;
    int values[n];

    // Generazione di n numeri casuali e inserimento nella SkipList
    for (int i = 0; i < n; i++)
    {
        values[i] = rand();
        insert_skiplist(list, &values[i]);
    }

    clear_skiplist(&list);

    assert(list == NULL);
}

void test_insert_and_search()
{
    Skiplist *list;
    new_skiplist(&list, MAX_HEIGHT, int_compar);

    int values[] = {10, 5, 20, 7, 15, 3, 18};
    int n = sizeof(values) / sizeof(int);

    // Inserimento dei valori nella SkipList
    for (int i = 0; i < n; i++)
    {
        insert_skiplist(list, (void *)(values + i));
    }

    // Ricerca dei valori nella SkipList
    for (int i = 0; i < n; i++)
    {
        const int *result = search_skiplist(list, &values[i]);
        assert(result != NULL && *result == values[i]);
    }

    // Ricerca di un valore non presente nella SkipList
    int not_in_list = 100;
    const int *result = search_skiplist(list, &not_in_list);
    assert(result == NULL);

    clear_skiplist(&list);
}

void test_insert_random()
{
    Skiplist *list;
    new_skiplist(&list, MAX_HEIGHT, int_compar);

    int n = 10000;
    int values[n];

    // Generazione di n numeri casuali e inserimento nella SkipList
    for (int i = 0; i < n; i++)
    {
        values[i] = rand();
        insert_skiplist(list, &values[i]);
    }

    // Verifica che i valori siano stati inseriti correttamente
    for (int i = 0; i < n; i++)
    {
        const int *result = search_skiplist(list, &values[i]);
        assert(result != NULL && *result == values[i]);
    }

    clear_skiplist(&list);
}

void test_str_insert_and_search()
{

    Skiplist *list;
    new_skiplist(&list, MAX_HEIGHT, str_compar);

    char *value[] = {"Napoli", "Cosenza", "Palermo", "Torino", "Roma"};
    int size = sizeof(value) / sizeof(char *);
    for (int i = 0; i < size; i++)
    {
        insert_skiplist(list, &value[i]);
    }

    for (int i = 0; i < size; i++)
    {
        const int *result = search_skiplist(list, &value[i]);
        assert(result != NULL && strcmp((char *)(result), value[i]));
    }

    char *not_in_list[] = {"Milano"};
    int n = sizeof(not_in_list) / sizeof(char *);

    for (int i = 0; i < n; i++)
    {
        const int *result = search_skiplist(list, (not_in_list + i));
        assert(result == NULL);
    }
    clear_skiplist(&list);
}

int main()
{
    test_empty_skiplist();
    test_create_skiplist();
    test_insert_and_search();
    test_clear_skiplist();
    test_insert_random();
    test_str_insert_and_search();
    return 0;
}
