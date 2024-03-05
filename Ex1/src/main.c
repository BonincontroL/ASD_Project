#include "mbi.h"

#define STR_LEN 30
#define N_LIMIT 20000000
#define MAX_LINE_LENGTH 1024

typedef struct record
{
    int id;
    char *field1; // String
    int field2;   // int
    float field3; // float
} Record;

int record_int_comparator(const void *p1, const void *p2)
{
    if (((Record *)p1)->field2 < ((Record *)p2)->field2)
        return -1;
    else if (((Record *)p1)->field2 > ((Record *)p2)->field2)
        return 1;
    else
        return 0;
}

int record_float_comparator(const void *p1, const void *p2)
{
    if (((Record *)p1)->field3 < ((Record *)p2)->field3)
        return -1;
    else if (((Record *)p1)->field3 > ((Record *)p2)->field3)
        return 1;
    else
        return 0;
}

int record_char_comparator(const void *p1, const void *p2)
{
    return strcmp(((Record *)p1)->field1, ((Record *)p2)->field1);
}

void copy_Record(Record *r, int i, int id, char *str, int word, float flt)
{
    r[i].id = id;
    r[i].field1 = malloc((STR_LEN) * sizeof(char));
    strncpy(r[i].field1, str, STR_LEN);
    r[i].field2 = word;
    r[i].field3 = flt;
}
int (*get_comparator(size_t field))(const void *, const void *)
{
    switch (field)
    {
    case 1:
        return record_char_comparator;
    case 2:
        return record_int_comparator;
    case 3:
        return record_float_comparator;
    default:
        printf("Invalid field value: %ld\n", field);
        return NULL;
    }
}
void sort_records(FILE *infile, FILE *outfile, size_t k, size_t field)
{
    Record *record = malloc(N_LIMIT * sizeof(Record));

    if (infile == NULL || outfile == NULL)
    {
        printf("File error...");
        free(record);
        return;
    }
    printf("File exists!\nReading File...\n");
    char line[MAX_LINE_LENGTH];
    int i = 0; // Line
    while (((fgets(line, sizeof(line), infile)) != NULL))
    {
        int id = atoi(strtok(line, ","));
        char *str = strdup(strtok(NULL, ","));
        int word = atoi(strtok(NULL, ","));
        float flt = atof(strtok(NULL, ","));

        copy_Record(record, i, id, str, word, flt);
        free(str);
        i++;
    }
    printf("Ok, file uploaded!!\n");
    clock_t start, end;
    int countLine = i;
    switch (field)
    {
    case 1:
        printf("-----------|>>Sorting String<<|-----------\n");
        break;
    case 2:
        printf("-----------|>>Sorting Int<<|-----------\n");
        break;
    case 3:
        printf("-----------|>>Sorting Float<<|-----------\n");
        break;
    default:
        printf("Invalid field value %ld\n", field);
        free(record);
        return; // Esce dalla funzione direttamente in caso di errore
    }

    if (field >= 1 && field <= 3)
    {
        start = clock();
        merge_binary_insertion_sort(record, countLine, k, sizeof(Record), get_comparator(field));
        end = clock();
        printf("Durata: %fs\n", ((double)(end - start)) / CLOCKS_PER_SEC);

        for (int i = 0; i < countLine; i++)
        {
            fprintf(outfile, "%d,%s,%d,%f\n", record[i].id, record[i].field1, record[i].field2, record[i].field3);
        }

        printf("End creating sorted file!!\nNumRighe:%d\n", i);
        printf("Free memory...\n");
        for (int j = 0; j < countLine; j++)
        {
            free(record[j].field1);
        }
        free(record);
        printf("End!!\n");
    }
}

int main(int argc, char *argv[])
{
    FILE *infile = fopen(argv[1], "r");
    FILE *outfile = fopen(argv[2], "w");
    int k = atoi(argv[3]);
    int field = atoi(argv[4]);

    sort_records(infile, outfile, k, field);

    fclose(outfile);
    fclose(infile);

    return 0;
}
