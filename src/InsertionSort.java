/*
    Author: Andreas Hammarstrand
    Written: 2020/09/12
    Updated: 2020/09/17
    Purpose:
        InsertionSort attempts at implementing a general insertion sort
        that can be used for both descending and ascending sort, depending
        on the comparator used.
        It also contains a miscellaneous method `inversions` which
        counts the number of inversions in a given array of numbers.
    Usage:
        Use or create a comparator to pass in as the second argument to
        `sort` to use either algorithms.
 */

import java.util.Comparator;

public class InsertionSort
{
    // sorts the list of `int` items where the order is determined by the
    // comparator
    public static int sort(int[] items, Comparator<Integer> comparator)
    {
        // at the end of the method we return the amount of swaps
        int swaps = 0;

        // the outer loop iterates through all elements, the inner loop
        // produces the sorting effect.
        for (int i = 1; i < items.length; i++)
        {
            // go backwards and push all sorted elements forward while the
            // current element (i) is less (in perspective of the comparator)
            // than the looked at element (j).
            for (int j = i; j > 0 && comparator.compare(items[j - 1], items[j]) > 0; j--)
            {
                // swap elements; pushes "bigger" elements forward "bigger"
                // in this case is in the eyes of the comparator
                int item = items[j - 1];
                items[j - 1] = items[j];
                items[j] = item;

                // print out array
                // System.out.println(Utility.intArrayToString(items));

                // increment swaps
                // swaps++;
            }
        }

        return swaps;
    }

    // counts the number of inversions in the list of `int` items where the
    // order is determined by the comparator
    public static int inversions(int[] items, Comparator<Integer> comparator)
    {
        int inversions = 0;
        for (int i = 0; i < items.length; i++)
        {
            for (int j = i + 1; j < items.length; j++)
            {
                if (comparator.compare(items[i], items[j]) > 0)
                {
                    inversions++;
                    String output = String.format("[%d, %d], [%d, %d]", i, items[i], j, items[j]);
                    System.out.println(output);
                }
            }
        }

        return inversions;
    }
}
