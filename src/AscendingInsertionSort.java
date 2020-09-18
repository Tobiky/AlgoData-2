/*
    Author: Andreas Hammarstrand
    Written: 2020/09/15
    Updated: 2020/09/17
    Purpose:
        AscendingInsertionSort attempts at implementing an ascending
        insertion sort, through the use of polymorphism and a general
        implementation of insertion sort.
    Usage:
        Class `InsertionSort` is required to run this class.
        Tests are runnable through the main method, which only accepts
        integers between -2,147,483,648 and 2,147,483,647. The sorting
        algorithm can also be used by importing the class.
 */

import java.util.Scanner;

public class AscendingInsertionSort
{
    // sorts the list of `int` items in ascending order using insertion sort
    public static int sort(int[] items)
    {
        // passes the items to be sorted as well as the standard `compareTo`
        // method for Integers, which will cause it to sort in
        // ascending order.
        return InsertionSort.sort(items, Integer::compareTo);
    }

    // counts the number of inversions in a list of `int` elements
    public static int inversions(int[] items)
    {
        return InsertionSort.inversions(items, Integer::compareTo);
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

        System.out.println("Inversions:");
        int inversions = inversions(items);
        System.out.println("Amount of inversions: " + inversions);

        // print out the sorted array for the user
        System.out.println("Sorted array:");
        int swaps = sort(items);
        System.out.println(Utility.intArrayToString(items));
        System.out.println("Amount of swaps: " + swaps);
    }
}
