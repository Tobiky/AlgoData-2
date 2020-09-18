/*
    Author: Andreas Hammarstrand
    Written: 2020/09/15
    Updated: 2020/09/18
    Purpose:
        MergeSort attempts at implementing a naive immutable merge sort
        in ascending order.
    Usage:
        Can be imported to used to sort a list of integers with
        `MergeSort.sort`.
        To use test, launch as main entry point.
    Based on:
        https://appdividend.com/2019/07/19/merge-sort-in-java-example-java-merge-sort-program/
 */

import java.util.*;

public class MergeSort
{
    // sorts the list of `int` elements in ascending order through merge sort
    public static int[] sort(int[] items)
    {
        return mergeSort(items, items.length);
    }


    // the recursive function of merge sort; the entry point of the algorithm
    private static int[] mergeSort(int[] items, int size) {
        if (size > 1) {
            int mid = size / 2;

            // recursive call for first half array
            int[] sortedFirstHalf =
                    mergeSort(
                            Arrays.copyOfRange(items, 0, mid),
                            mid);

            // recursive call for second half array
            int[] sortedSecondHalf =
                    mergeSort(
                            Arrays.copyOfRange(items, mid, size),
                            size - mid);

            // merge the two merge sorted halves into a ordered one
            items =
                    arrayMerge(
                            sortedFirstHalf,
                            sortedSecondHalf);
        }
        return items;
    }


    // merge two arrays together into a new ordered one and return it
    private static int[] arrayMerge(int[] first, int[] second)
    {
        int[] totalItems = new int[first.length + second.length];
        int totalItemsIndex = 0, firstIndex = 0, secondIndex = 0;

        // fill up `totalItems` with the lowest values from the input arrays `first`
        // and `second`.
        while (firstIndex < first.length && secondIndex < second.length) {
            if (first[firstIndex] < second[secondIndex])
            {
                totalItems[totalItemsIndex++] = first[firstIndex++];
            }
            else
            {
                totalItems[totalItemsIndex++] = second[secondIndex++];
            }
        }

        // put in the rest of the values from either input arrays into `totalItems`
        // (the previous loop would have emptied one or both input arrays)
        while (firstIndex < first.length)
        {
            totalItems[totalItemsIndex++] = first[firstIndex++];
        }

        while (secondIndex < second.length)
        {
            totalItems[totalItemsIndex++] = second[secondIndex++];
        }

        // return the sorted partition.
        return totalItems;
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

        // print out array for the user
        System.out.println("Input array:");
        System.out.println(Utility.intArrayToString(items));

        // print out the sorted array for the user
        System.out.println("Sorted array:");
        int[] sorted = sort(items);
        System.out.println(Utility.intArrayToString(sorted));
    }
}
