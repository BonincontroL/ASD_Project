#include <assert.h>
#include "mbi.h"

typedef struct record
{
    int id;
    char *field1; // String
    int field2;   // int
    float field3; // float

} Record;

int cmp_Int(const void *a, const void *b)
{
    return *(int *)a - *(int *)b;
}
int cmp_float(const void *a, const void *b)
{
    return *(float *)a - *(float *)b;
}

int cmp_str(const void *a, const void *b)
{
    return strcmp(*(char **)a, *(char **)b);
}

int cmp_Record_int(const void *a, const void *b)
{
    if (((Record *)a)->field2 < ((Record *)b)->field2)
        return -1;
    else if (((Record *)a)->field2 > ((Record *)b)->field2)
        return 1;
    else
        return 0;
}
int cmp_Record_str(const void *a, const void *b)
{
    return strcmp(((Record *)a)->field1, ((Record *)b)->field1);
}
int cmp_Record_float(const void *a, const void *b)
{
    if (((Record *)a)->field3 < ((Record *)b)->field3)
        return -1;
    else if (((Record *)a)->field3 > ((Record *)b)->field3)
        return 1;
    else
        return 0;
}

/////////// TEST SORT ///////////

void test_sort_reverse_sorted_array()
{
    int arr[] = {5, 4, 3, 2, 1};
    int expected[] = {1, 2, 3, 4, 5};
    int n = sizeof(arr) / sizeof(arr[0]);
    merge_binary_insertion_sort(arr, n, 0, sizeof(int), cmp_Int);
    for (int i = 0; i < n; i++)
    {
        assert(arr[i] == expected[i]);
    }
}

void test_sort_null_array()
{
    int *empty_int_array = NULL;
    merge_binary_insertion_sort(empty_int_array, 0, 2, sizeof(int), cmp_Int);
    assert(empty_int_array == NULL);
}

void test_sort_empty_array()
{
    int arr[] = {};
    int expected[] = {};
    int n = sizeof(arr) / sizeof(arr[0]);
    int n2 = sizeof(expected) / sizeof(arr[0]);
    merge_binary_insertion_sort(arr, 0, 0, sizeof(int), cmp_Int);
    assert(n == n2);
}

void test_sort_sorted_array()
{

    int arr[] = {1, 3, 5, 7, 11};
    int expected[] = {1, 3, 5, 7, 11};
    int n = sizeof(arr) / sizeof(arr[0]);
    merge_binary_insertion_sort(arr, n, 0, sizeof(int), cmp_Int);
    for (int i = 0; i < n; i++)
    {
        assert(arr[i] == expected[i]);
    }
}

// Test per l'ordinamento di un array di interi
void test_int_sort()
{
    int arr[] = {4, 2, 7, 1, 3};
    int expected[] = {1, 2, 3, 4, 7};
    int n = sizeof(arr) / sizeof(arr[0]);
    merge_binary_insertion_sort(arr, n, 0, sizeof(int), cmp_Int);
    for (int i = 0; i < n; i++)
    {
        assert(arr[i] == expected[i]);
    }
}

// Test per l'ordinamento di un array di float
void test_float_sort()
{
    float arr[] = {4.5, 2.3, 7.1, 1.0, 3.8};
    float expected[] = {1.0, 2.3, 3.8, 4.5, 7.1};
    int n = sizeof(arr) / sizeof(arr[0]);
    merge_binary_insertion_sort(arr, n, 0, sizeof(float), cmp_float);
    for (int i = 0; i < n; i++)
    {
        assert(arr[i] == expected[i]);
    }
}

// Test per l'ordinamento di un array di stringhe
void test_string_sort()
{
    const char *arr[] = {"peperone", "banana", "fragola", "mela", "arancia"};
    const char *expected[] = {"arancia", "banana", "fragola", "mela", "peperone"};
    int n = sizeof(arr) / sizeof(arr[0]);
    merge_binary_insertion_sort(arr, n, 0, sizeof(char *), cmp_str);
    for (int i = 0; i < n; i++)
    {
        assert(strcmp(arr[i], expected[i]) == 0);
    }

    char *arr2[] = {"pippo", "paperino", "pluto", "topolino", "paperone", "gastone"};
    char *expected_arr2[] = {"gastone", "paperino", "paperone", "pippo", "pluto", "topolino"};
    merge_binary_insertion_sort(arr2, 6, 0, sizeof(char *), cmp_str);
    for (int i = 0; i < 6; i++)
    {
        assert(strcmp(arr2[i], expected_arr2[i]) == 0);
    }
}
void test_merge()
{
    int v[8] = {1, 4, 7, 8, 2, 3, 6, 78};
    int *p = &v[4];
    int ans[] = {1, 2, 3, 4, 6, 7, 8, 78};
    merge(v,p,4,4,cmp_Int,sizeof(int));
   
    for (int i = 0; i < 8; i++)
    {
        assert(v[i] == ans[i]);
    }
}
void test_sort_records()
{
    Record r[4];
    r[0].id = 0;
    r[0].field3 = 2.1;
    r[0].field1 = "Ciao";
    r[0].field2 = 4;

    r[0].id = 1;
    r[1].field3 = 7.89;
    r[1].field1 = "Libro";
    r[1].field2 = 5;

    r[2].id = 2;
    r[2].field3 = 1.5;
    r[2].field1 = "Torino";
    r[2].field2 = 2;

    r[3].id = 3;
    r[3].field3 = 9.8;
    r[3].field1 = "Pomodoro";
    r[3].field2 = 3;

    merge_binary_insertion_sort(r, 4, 0, sizeof(Record), cmp_Record_int);

    for (int i = 0; i < 3; i++)
    {
        assert(r[i].field2 <= r[i + 1].field2);
    }

    merge_binary_insertion_sort(r, 4, 0, sizeof(Record), cmp_Record_float);

    for (int i = 0; i < 3; i++)
    {
        assert(r[i].field3 <= r[i + 1].field3);
    }

    merge_binary_insertion_sort(r, 4, 0, sizeof(Record), cmp_Record_str);

    for (int i = 0; i < 3; i++)
    {
        assert(strcmp(r[i].field1, r[i + 1].field1) <= 0);
    }
}

int main()
{
    test_sort_reverse_sorted_array();
    test_int_sort();
    test_sort_sorted_array();
    test_float_sort();
    test_string_sort();
    test_sort_records();
    test_sort_empty_array();
    test_sort_null_array();
    test_merge();
    return 0;
}
