package coding.arrays;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairSumsTest {

    /*
        Input: arr[] = {1, 5, 7, -1}, K = 6
        Output:  2
        Explanation: Pairs with sum 6 are (1, 5) and (7, -1).

        Input: arr[] = {1, 5, 7, -1, 5}, K = 6
        Output:  3
        Explanation: Pairs with sum 6 are (1, 5), (7, -1) & (1, 5).
     */

    @Test
    void run() {
        int[] arr = { 1, 5, 7, -1, 5, 5, 1 };
        int K = 6;
        assertEquals(7, getPairsCount(arr, K));
        assertEquals(7, getPairsCount2(arr, K));
    }

    // naive
    // O(n^2)
    public static int getPairsCount(int[] arr, int K)
    {
        int count = 0;

        // Consider all possible pairs and check their sums
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if ((arr[i] + arr[j]) == K)
                    count++;

        return count;
    }

    /* using hash map
    O(n)

    1: 6 - 1 = false
    1 -> 1

    5:  6 - 1 = true => count = 1
    1 -> 1
    5 -> 1

    7: 6 - 7 = -1 : false
    1 -> 1
    5 -> 1
    7 -> 1

    -1: 6 - -1 = true => count = 2
    1 -> 1
    5 -> 1
    7 -> 1
    1 -> 1

    5:   6 - 1 = true => count = 3
    1 -> 1
    5 -> 2
    7 -> 1
    1 -> 1

     */
    static int getPairsCount2(int[] array, int k)
    {
        HashMap<Integer, Integer> m = new HashMap<>();
        int count = 0;

        for (int value : array) {

            int needed = k - value;
            if (m.containsKey(needed)) {
                count += m.get(needed);
            }

            if (m.containsKey(value)) {
                m.put(value, m.get(value) + 1);
            } else {
                m.put(value, 1);
            }
        }
        return count;
    }

}
