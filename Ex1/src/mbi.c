#include "mbi.h"

/*
    swap:
    Una semplice funzione che scambia il contenuto di due elementi di un array di tipo sconosciuto
    a è un puntatore all'area di memoria della prima variabile o record
    b è un puntatore all'area di memoria della seconda variabile o record
    type_size è la dimensione in byte di a e b
*/
void swap(void *a, void *b, int type_size)
{
    unsigned char *p = a, *q = b, tmp;
    for (int i = 0; i < type_size; i++)
    {
        tmp = p[i];
        p[i] = q[i];
        q[i] = tmp;
    }
}
/*
    copy_bytes:
    Una semplice funzione che copia il contenuto di una variabile o record in un'altra variabile o record di tipo sconosciuto
    v è un puntatore all'area di memoria contenente i nostri dati
    w è un puntatore all'area di memoria in cui vogliamo copiare i nostri dati
    type_size è la dimensione in byte di v e w

*/
void copy_bytes(void *v, void *w, int type_size) // Copia il contenuto di v in w
{
    unsigned char *a = v, *b = w;
    for (int i = 0; i < type_size; i++)
    {
        b[i] = a[i];
    }
}

//=============================================================================================================================

int binary_search(void *arr, void *elem, int l, int r, int type_size, int (*compar)(const void *, const void *))
{
    if (r >= l)
    {
        int mid = (l + r) / 2;

        if (compar(arr + type_size * mid, elem) == 0)
            return mid;
        if (compar(arr + type_size * mid, elem) > 0)
            return binary_search(arr, elem, l, mid - 1, type_size, compar);
        return binary_search(arr, elem, mid + 1, r, type_size, compar);
    }
    return l;
}

//=============================================================================================================================

void *binary_insertion(void *arr, int size, int type_size, int (*compar)(const void *, const void *))
{

    if (size == 0)
        return arr;
    if (arr == NULL)
        return NULL;
    for (int i = 1; i < size; i++)
    {
        int start = 0, end = i - 1;
        int pos = binary_search(arr, arr + i * type_size, start, end, type_size, compar); // Ricerca binaria per trovare la posizione corretta in cui inserire l'elemento corrente

        for (int k = i; k > pos; k--)
        {
            swap((arr + k * type_size), (arr + (k - 1) * type_size), type_size); // elemento corrente con la posizione precedente
        }
    }
    return arr;
}

//=============================================================================================================================

void *merge(void *v, void *w, int v_size, int w_size, int (*compar)(const void *, const void *), int type_size)
{
    int i = 0, j = 0, k = 0;
    int length = v_size + w_size;
    void *arr = malloc(length * type_size);
    while (k < length)
    {
        if (j == w_size)
        {
            copy_bytes(v + i * type_size, arr + k * type_size, type_size);
            if (i < v_size)
                i++;
        }
        else if (i == v_size)
        {
            copy_bytes(w + j * type_size, arr + k * type_size, type_size);
            if (j < w_size)
                j++;
        }
        else if (compar(v + i * type_size, w + j * type_size) < 0)
        {
            copy_bytes(v + i * type_size, arr + k * type_size, type_size);
            if (i < v_size)
                i++;
        }
        else if (compar(v + i * type_size, w + j * type_size) >= 0)
        {
            copy_bytes(w + j * type_size, arr + k * type_size, type_size);
            if (j < w_size)
                j++;
        }
        k++;
    }
    for (int i = 0; i < length; i++)
    {
        copy_bytes(arr + i * type_size, v + i * type_size, type_size);
    }

    free(arr);
    return v;
}

//=============================================================================================================================

void merge_binary_insertion_sort(void *base, size_t nitems, size_t k, size_t size, int (*compar)(const void *, const void *))
{
    if (base == NULL || nitems == 0)
        return;
    void *mid = base + (nitems / 2) * size;
    if (nitems > k) // Se l'array è lungo usiamo merge sort
    {
        if (nitems > 1)
        {
            merge_binary_insertion_sort(base, nitems / 2, k, size, compar);
            merge_binary_insertion_sort(mid, (nitems + 1) / 2, k, size, compar);
            merge(base, mid, nitems / 2, (nitems + 1) / 2, compar, size);
        }
    }
    else
    {
        binary_insertion(base, nitems, size, compar); // Quando l'array è abbastanza corto, eseguiamo  Binary-Insertion sort
    }
}
