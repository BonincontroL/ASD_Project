#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

/*
    Una libreria di funzioni  per ordinare array di qualsiasi tipo o record scritti  a condizione
    che sia specificato un puntatore a funzione comparatore per l'algoritmo per conoscere "la priorità"(> o < o =) di un oggetto rispetto all'altro,
    Pertanto, la dimensione dell'array deve essere conosciuta in un primo momento così come la dimensione in byte di ogni singolo elemento.
*/

int binary_search(void *arr, void *elem, int l, int r, int type_size, int (*compar)(const void *, const void *));
void *binary_insertion(void *arr, int size, int type_size, int (*compar)(const void *, const void *));
void *merge(void *v, void *w, int v_size, int w_size, int (*compar)(const void *, const void *), int type_size);
void merge_binary_insertion_sort(void *base, size_t nitems, size_t k, size_t size, int (*compar)(const void *, const void *));
