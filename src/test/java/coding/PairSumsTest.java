package coding;

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
        int[] arr = { 1, 5, 7, -1, 5 };
        int K = 6;
        assertEquals(3, getPairsCount(arr, K));
        assertEquals(3, getPairsCount2(arr, K));
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

    // using hash map
    // O(n)
    static int getPairsCount2(int array[], int k)
    {
        HashMap<Integer, Integer> m = new HashMap<>();
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            if (m.containsKey(k - value)) {
                count += m.get(k - value);
            }
            if (m.containsKey(array[i])) {
                m.put(value, m.get(value) + 1);
            }
            else {
                m.put(value, 1);
            }
        }
        return count;
    }

}
