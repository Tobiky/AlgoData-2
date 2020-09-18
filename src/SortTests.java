/*
    Author: Andreas Hammarstrand
    Written: 2020/09/15
    Updated: 2020/09/18
    Purpose:
        SortTests contains methods to help produce metric data for the various
        sorts implemented in the same folder.
    Usage:
        `AscendingInsertionSort`, `DescendingInsertionSort`, `CutOffSort`
        `MedianOfThreeQuickSort`, `QuickSort`, `ReversedQuickSort`,
        and `MergeSort` are required to run these tests.
        SortTests contains two modes, which are switched between by changing
        two lines of source code in the static main method. Comment one and
        uncomment the other.
 */

import java.util.Random;

public class SortTests
{
    // constant values for the tests
    private static final int TEST_AMOUNT = 1000;
    private static final int TEST_REPETITION = 50;
    private static final int TEST_ARRAY_SIZE_VARIANCE = 10;
    private static final int TEST_VARIANCE = 10_000;

    // fills the array with random values
    private static void fillArray(int[] array)
    {
        Random r = new Random();
        for (int i = 0; i < array.length; i++)
        {
            array[i] = r.nextInt(TEST_VARIANCE);
        }
    }

    // gives the time it took for the merge sort to sort an array of
    // `arraySize`
    private static long testMergeSort(int arraySize)
    {
        int[] array = new int[arraySize];
        fillArray(array);

        long start = System.nanoTime();
        MergeSort.sort(array);
        long time = System.nanoTime() - start;

        return time;
    }

    // gives the time it took for the insertion sort to sort an array of
    // `arraySize`
    private static long testInsertionSort(int arraySize)
    {
        int[] array = new int[arraySize];
        fillArray(array);

        long start = System.nanoTime();
        AscendingInsertionSort.sort(array);
        long time = System.nanoTime() - start;

        return time;
    }

    // gives the time it took for the top first quick sort to sort an array of
    // `arraySize`
    private static long testQuickSort(int arraySize)
    {
        int[] array = new int[arraySize];
        fillArray(array);

        long start = System.nanoTime();
        QuickSort.sort(array);
        long time = System.nanoTime() - start;

        return time;
    }

    // times merge sort, insertion sort, and quick sort for different sizes,
    // takes the mean of repetitions of the same size, and prints the results
    private static void testSorts()
    {
        // print header
        System.out.println("Merge Sort Time, Insertion Sort Time, Quick Sort Time, Size");

        // tests for different sizes
        for (int count = 0; count < TEST_AMOUNT; count++)
        {
            int size = (count + 1) * TEST_ARRAY_SIZE_VARIANCE;

            // calculate the mean for several repetitions of the same size
            long meanMergeSortTime = 0;
            for (int repitition = 0; repitition < TEST_REPETITION; repitition++)
            {
                meanMergeSortTime += testMergeSort(size);
            }
            meanMergeSortTime /= TEST_REPETITION;


            long meanInsertionSortTime = 0;
            for (int repitition = 0; repitition < TEST_REPETITION; repitition++)
            {
                meanInsertionSortTime += testInsertionSort(size);
            }
            meanInsertionSortTime /= TEST_REPETITION;

            long meanQuickSortTime = 0;
            for (int repetition = 0; repetition < TEST_REPETITION; repetition++)
            {
                meanQuickSortTime += testQuickSort(size);
            }
            meanQuickSortTime /= TEST_REPETITION;

            // prints the results into CSV format
            String out =
                    String.format(
                            "%d, %d, %d, %d",
                            meanMergeSortTime,
                            meanInsertionSortTime,
                            meanQuickSortTime,
                            size);

            System.out.println(out);
        }
    }

    // gives the time it took for the median-of-three quick sort to sort an array of
    // `arraySize`
    private static long testMedianOfThreeQuickSort(int arraySize)
    {
        int[] array = new int[arraySize];
        fillArray(array);

        long start = System.nanoTime();
        MedianOfThreeQuickSort.sort(array);
        long time = System.nanoTime() - start;

        return time;
    }

    // gives the time it took for the bottom first quick sort to sort an array of
    // `arraySize`
    private static long testReversedQuickSort(int arraySize)
    {
        int[] array = new int[arraySize];
        fillArray(array);

        long start = System.nanoTime();
        ReversedQuickSort.sort(array);
        long time = System.nanoTime() - start;

        return time;
    }


    // times median-of-three quick sort and bottom first quicksort for
    // different sizes, takes the mean of repetitions of the same size, and
    // prints the results
    private static void testQuickSorts()
    {
        System.out.println("Quick Sort Time, Median-of-Three Quick Sort Time, Size");
        for (int count = 0; count < TEST_AMOUNT; count++)
        {
            int size = (count + 1) * TEST_ARRAY_SIZE_VARIANCE;

            long meanQuickSortTime = 0;
            for (int repetition = 0; repetition < TEST_REPETITION; repetition++)
            {
                meanQuickSortTime += testReversedQuickSort(size);
            }
            meanQuickSortTime /= TEST_REPETITION;

            long meanMedianQuickSortTime = 0;
            for (int repetition = 0; repetition < TEST_REPETITION; repetition++)
            {
                meanMedianQuickSortTime += testMedianOfThreeQuickSort(size);
            }
            meanMedianQuickSortTime /= TEST_REPETITION;

            String out =
                    String.format(
                            "%d, %d, %d",
                            meanQuickSortTime,
                            meanMedianQuickSortTime,
                            size);

            System.out.println(out);
        }
    }

    // executes tests method
    public static void main(String[] args)
    {
        // testSorts();
        testQuickSorts();
    }
}
