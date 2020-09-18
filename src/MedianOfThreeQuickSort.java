/*
    Author: Andreas Hammarstrand
    Written: 2020/9/16
    Updated: 2020/9/17
    Purpose:
        MedianOfThreeQuickSort is an attempt at median-of-three quicksort, in
        ascending order.
    Usage:
        Can be imported to used to sort a list of integers with
        `MedianOfThreeQuickSort.sort`.
        To use test, launch as main entry point.
 */

import java.util.Scanner;

public class MedianOfThreeQuickSort
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
    // pivot is selected by the median of the middle, bottom, and top element
    private static int partition(int[] items, int low, int high)
    {
        // start from the bottom and compare with the top element
        int pivotElement = median(items, low, high);
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
                int hold = items[j];
                items[j] = items[i];
                items[i] = hold;

                i++;
            }
        }

        // swap elements i and high (pivotElement)
        items[high] = items[i];
        items[i] = pivotElement;

        // return the new index of the pivot element
        return i;
    }

    // selects the median of `items` between `low`, `high`, and the middle
    // also swaps them in the correct order, except median which is placed
    // in high and returned
    private static int median(int[] items, int low, int high)
    {
        int midIndex = (low + high) / 2;

        // these two if statements ensure that the lowest element is in low
        // and that high and medium are the top elements

        // swap mid and low if mid < low
        if (items[midIndex] < items[low])
        {
            Utility.swap(items, midIndex, low);
        }

        // swap high and low if high < low
        if (items[high] < items[low])
        {
            Utility.swap(items, high, low);
        }

        // if the median got into mid, then swap it with high

        // swap mid and high if mid < high
        if (items[midIndex] < items[high])
        {
            Utility.swap(items, midIndex, high);
        }

        return items[high];
    }

    // test method
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

        System.out.println("Input array:");
        System.out.println(Utility.intArrayToString(items));

        System.out.println("Sorted array:");
        sort(items);
        System.out.println(Utility.intArrayToString(items));
    }
}
