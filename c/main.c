/*
    Author: Andreas Hammarstrand
    Written: 2020/09/15
    Updated: 2020/09/18
    Purpose:
        main.c attempts at reordering the elements in an array through putting
        all the negative values on the left-hand side of the array and the rest
        on the right-hand side. Values are otherwise not sorted.
*/

// reorders the array so that all negatives are on left hand side
// and the rest on the right hand side. Does not sort the array
// otherwise.

#include <stdio.h>
#include <stdlib.h>

void order_negatives(int* array, int count)
{
    int neg_pointer = -1;
    int i;
    for (i = 0; i < count; i++)
    {
        if (array[i] >= 0)
        {
            int j;
            for (j = i + 1; j < count; j++)
            {
                if (array[j] < 0)
                {
                    int hold = array[i];
                    array[i] = array[j];
                    array[j] = hold;
                }
            }
        }
    }
}

// print chosen list of `int` elements to stdout
void print_array(int* array, int count)
{
    putchar('[');

    int i;
    for (i = 0; i < count - 1; i++)
    {
        printf("%d, ", array[i]);
    }
    
    printf("%d]", array[i]);
}

// creates an array of size with random values
int* create_random_array(int size)
{
    int* array = malloc(size * sizeof(int));

    int i;
    for (i = 0; i < size; i++)
    {
        int randomNumber = rand() % 10000;
        if (rand() % 2 == 1)
        {
            randomNumber = -randomNumber;
        }

        array[i] = randomNumber;
    }

    return array;
}


int main()
{
    // take size from user
    printf("Input number of values: ");
    int size = 0;
    scanf("%d", &size);

    // create random array of users size
    int* array = create_random_array(size);

    // print array before and after the use of the order function
    printf("Array before order:\n");
    print_array(array, size);
    putchar('\n');

    printf("Array after order:\n");
    order_negatives(array, size);
    print_array(array, size);
    putchar('\n');

    free(array);
}