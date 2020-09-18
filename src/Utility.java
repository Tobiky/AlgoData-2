/*
    Author:
    Written:
    Updated:
    Purpose:
    Usage:
    Based on:
 */

public class Utility
{
    // returns a string representation of a list of `int` elements
    public static String intArrayToString(int[] array)
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (int i = 0; i < array.length - 1; i++)
        {
            sb
                    .append(array[i])
                    .append(", ");
        }

        sb.append(array[array.length - 1]);

        sb.append(']');
        return sb.toString();
    }

    // swaps two elements in an list of `int` elements, at indices A and B
    public static void swap(int[] array, int indexA, int indexB)
    {
        int hold = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = hold;
    }
}
