#include "skip_list.h"

#define MAX_LINE_LENGTH 1024

int str_compar(const void *p1, const void *p2)
{
    return strcmp((char *)p1, (char *)p2);
}

void find_errors(FILE *dictfile, FILE *textfile, size_t max_height)
{
    if (dictfile == NULL)
    {
        printf("Failed to open dictionary.txt file!\n");
        return;
    }
    if (textfile == NULL)
    {
        printf("File correctme.txt not found!\n");
        return;
    }

    Skiplist *list = NULL;
    new_skiplist(&list, max_height, str_compar);

    char line[MAX_LINE_LENGTH];
    clock_t start = clock();
    while (fgets(line, sizeof(line), dictfile) != NULL)
    {
        line[strcspn(line, "\r\n")] = '\0'; // Rimuove eventuali caratteri di newline  
        char *w = strdup(line);             // Crea una copia della stringa letta dal file
        insert_skiplist(list, (void *)w);
    }
    clock_t end = clock();
    printf("\n");
    printf("Tutte le parole del dizionario sono state aggiunte alla Skiplist!\n");
    printf("Durata caricamento: %fs\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("\n");
    printf("---------------------------|>>Ricerca ERRORI<<|----------------------------\n");
    char character;
    char word[MAX_LINE_LENGTH];
    memset(word, 0, MAX_LINE_LENGTH);
    int i = 0;                        // count error
    start = clock();
    while ((character = fgetc(textfile)) != EOF)
    {
        if (isspace(character) || ispunct(character))
        {
            if (strlen(word) > 0) // Controlla se la stringa word non è vuota
            {
                const void *found = search_skiplist(list, (void *)word);
                if (found == NULL)
                {
                    printf("|Parola Errata n.%d  => %s\n", i, word);
                    i++;
                }
                memset(word, 0, MAX_LINE_LENGTH);
            }
        }
        else
        {
            character = tolower(character); // converte il carattere contenuto nella variabile character in una versione in minuscolo
            strncat(word, &character, sizeof(char));
        }
    }
    end = clock();
    printf("---------------------------|>>END<<|---------------------------------------\n");
    printf("Durata ricerca errori: %fs\n", (double)(end - start) / CLOCKS_PER_SEC);
    // Pulisce la memoria
    clear_skiplist(&list);
}

int main(int argc, char *argv[])
{
    FILE *dictfile = fopen(argv[1], "r");
    FILE *textfile = fopen(argv[2], "r");
    int max_height = atoi(argv[3]);

    find_errors(dictfile, textfile, max_height);

    fclose(dictfile);
    fclose(textfile);
    return 0;
}
