/*
    Author: Andreas Hammarstrand
    Written: 2020/09/16
    Updated: 2020/09/17
    Purpose:
        DescendingInsertionSort attempts at implementing an descending
        insertion sort, through the use of polymorphism and a general
        implementation of insertion sort.
    Usage:
        Class `InsertionSort` is required to run this class.
        Tests are runnable through the main method, which only accepts
        integers between -2,147,483,648 and 2,147,483,647. The sorting
        algorithm can also be used by importing the class.
 */

import java.util.Comparator;
import java.util.Scanner;

public class DescendingInsertionSort
{
    // an inverse comparator; inverse to `Integer.compareTo`
    // this is used to sort an `int[]` in descending order with `InsertionSort.sort`
    private static class InvertedCompareTo implements Comparator<Integer>
    {
        @Override
        public int compare(Integer o1, Integer o2)
        {
            Comparator<Integer> comparator = Integer::compareTo;
            return -comparator.compare(o1, o2);
        }
    }

    // a constant instance of the comparator so a new one is not created every
    // time `sort` is called
    private static final Comparator<Integer> comparator =
            new InvertedCompareTo();

    // sorts the list of `int` items in descending order using insertion sort
    public static int sort(int[] items)
    {
        // passes the items to be sorted as well as the standard `compareTo`
        // method for Integers, which will cause it to sort in
        // ascending order.
        return InsertionSort.sort(items, comparator);
    }

    // counts the inversions of a list of `int` elements
    public static int inversions(int[] items)
    {
        return InsertionSort.inversions(items, comparator);
    }

    // testing method
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

        System.out.println("Inversions:");
        int inversions = inversions(items);
        System.out.println("Amount of inversions: " + inversions);

        System.out.println("Sorted:");
        int swaps = sort(items);
        System.out.println("Amount of swaps: " + swaps);
    }
}
