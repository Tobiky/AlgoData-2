/*
    Author: Andreas Hammarstrand
    Written: 2020/09/16
    Updated: 2020/09/18
    Purpose:
        QuickSort attempts at implementing a naive top first quicksort in
        ascending order.
    Usage:
        Can be imported to used to sort a list of integers with
        `QuickSort.sort`.
        To use test, launch as main entry point.
 */

import java.util.Scanner;

public class QuickSort
{
    // sorts the list of `int` items using bottom first naive quicksort
    public static void sort(int[] items)
    {
        quicksort(items, 0, items.length - 1);
    }

    // the recursive function of quicksort; entry of the algorithm
    private static void quicksort(int[] items, int low, int high)
    {
        // if high has gone past low or vice versa all items have been sorted,
        // due to `high` and `low` are decremented and incremented respectively
        // as seen below
        if (low < high)
        {
            // partition low to high, at start `0` to `items.length`
            int partitionIndex = partition(items, low, high);

            // recursively sort left side of partition index
            quicksort(items, low, partitionIndex - 1);

            // recursively sort right side of partition index
            quicksort(items, partitionIndex + 1, high);
        }
    }

    // partitions everything to smaller on the left side of the pivot element
    // and everything bigger on the right side of the pivot element
    // then returns the new index of the pivot element
    // pivot is selected as the top element
    private static int partition(int[] items, int low, int high)
    {
        // start from the bottom and compare with the top element
        int pivotElement = items[high];
        int i = low;

        for (int j = low; j < high; j++)
        {
            // this pushes anything lower than the pivot element down
            // because of where the `i` increment is located, element
            // at `i` will naturally move to be the highest element
            // and thus pushes all the elements bigger than the pivot
            // upwards as well
            if (items[j] < pivotElement)
            {
                // swap elements i and j
                Utility.swap(items, i, j);

                i++;
            }
        }

        // swap elements i and high (pivotElement)
        items[high] = items[i];
        items[i] = pivotElement;

        // return the new index of the pivot element
        return i;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        // take in test size from user
        System.out.print("Size of input: ");
        int size = in.nextInt();

        // fill array with values from user
        System.out.println("Values:");
        int[] items = new int[size];
        for (int count = 0; count < size; count++)
        {
            items[count] = in.nextInt();
        }

        // print out array for the user
        System.out.println("Input array:");
        System.out.println(Utility.intArrayToString(items));

        // print out the sorted array for the user
        System.out.println("Sorted array:");
        sort(items);
        System.out.println(Utility.intArrayToString(items));
    }
}
