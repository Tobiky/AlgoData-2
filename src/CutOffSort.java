/*
    Author: Andreas Hammarstrand
    Written: 2020/09/16
    Updated: 2020/09/17
    Purpose:
        Cut-off sort attempts at optimizing the sorting of both merge and
        insertion sort when one is more effective than the other.
    Usage:
        Import as class to use the sorting algorithm with `sort`. Otherwise,
        to get metric data call the main method. Data is printed to stdout
        of Java.
        Requires `AscendingInsertionSort` to function.
 */

import java.util.Arrays;
import java.util.Random;

public class CutOffSort
{
    // sorts the list of `int` items in ascending order using cutoff from
    // insertion sort to merge sort
    public static int[] sort(int[] items)
    {
        return mergeSort(items, items.length, 1);
    }

    // sorts the list of `int` items in ascending order using cutoff from
    // merge sort to insertion sort at the specified threshold

    // the recursive function of merge sort; the entry point of the algorithm
    private static int[] mergeSort(int[] items, int size, int threshold) {
        if (size > 1) {
            int mid = size / 2;

            int[] firstHalf = Arrays.copyOfRange(items, 0, mid);
            int[] secondHalf = Arrays.copyOfRange(items, mid, size);

            int[] sortedFirstHalf;
            // insertion sort should be faster under the threshold
            if (firstHalf.length < threshold)
            {
                AscendingInsertionSort.sort(firstHalf);
                sortedFirstHalf = firstHalf;
            }
            else
            {
                // recursive call for first half array
                sortedFirstHalf = mergeSort(firstHalf, mid, threshold);
            }


            int[] sortedSecondHalf;
            // insertion sort should be faster under the threshold
            if (firstHalf.length < threshold)
            {
                AscendingInsertionSort.sort(secondHalf);
                sortedSecondHalf = secondHalf;
            }
            else
            {
                // recursive call for second half array
                sortedSecondHalf = mergeSort(secondHalf, mid, threshold);
            }

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

    // constant values for the tests
    private static final int ARRAY_SIZE_VARIANCE = 1;
    private static final int TEST_COUNT = 1000;
    private static final int TEST_CUTOFF_MAX = 31;
    private static final int TEST_REPETITION = 15;

    // fills the array with random values
    private static void fillArray(int[] array)
    {
        Random r = new Random();
        for (int i = 0; i < array.length; i++)
        {
            array[i] = r.nextInt(10_000);
        }
    }


    // gives the time it took for the cut-off sort to sort an array of
    // `arraySize` with the cut off at `cutOff`
    private static long testCutOffSort(int arraySize, int cutOff)
    {
        int[] array = new int[arraySize];
        fillArray(array);

        long start = System.nanoTime();
        mergeSort(array, arraySize, cutOff);
        long time = System.nanoTime() - start;

        return time;
    }

    // testing method
    public static void main(String[] args)
    {
        // printing a header
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TEST_CUTOFF_MAX; i++)
        {
            sb.append(String.format("Sort Time for Cut Off at %d, ", i));
        }
        sb.append("Size");
        System.out.println(sb);

        long[] values = new long[TEST_REPETITION];
        // tests for different sizes
        for (int count = 0; count < TEST_COUNT; count++)
        {
            // timesAndSize contains the a row of CSV for times of different cut offs
            StringBuilder timesAndSize = new StringBuilder();
            int size = (count + 1) * ARRAY_SIZE_VARIANCE;

            for (int cutoff = 0; cutoff < TEST_CUTOFF_MAX; cutoff++)
            {
                // get the mean of `TEST_REPETITION` amount of tries
                long mean = 0;

                for (int repetition = 0; repetition < TEST_REPETITION; repetition++)
                {
                    long value = testCutOffSort(size, cutoff);
                    mean += value;
                    values[repetition] = value;
                }
                mean /= TEST_REPETITION;

                // add it to the horizontal list
                timesAndSize
                        .append(mean)
                        .append(", ");
            }

            // add size and print value to stdout
            timesAndSize.append(size);
            System.out.println(timesAndSize);
        }
    }
}
