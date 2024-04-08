package coding.arrays;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static graphql.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BalancedSplitTest {
    /*
        Example 1
        arr = [1, 5, 7, 1]
        output = true
        We can split the array into A = [1, 1, 5] and B = [7].

        Example 2
        arr = [12, 7, 6, 7, 6]
        output = false
        We can't split the array into A = [6, 6, 7] and B = [7, 12] since this doesn't satisfy the requirement that all integers in A are smaller than all integers in B.
     */

    @Test
    void run() {
        int []arr = new int[]{1, 5, 7, 1};
        assertTrue(balancedSplitExists(arr));

        int [] arr2 = new int[]{12, 7, 6, 7, 6};
        assertFalse(balancedSplitExists(arr2));

    }

    boolean balancedSplitExists(int[] arr) {
        Arrays.sort(arr);
        int left = IntStream.of(arr).sum();
        int right = 0;
        for(int i = arr.length - 1; i != 0; i--) {
            int value = arr[i];
            left -= value;
            right += value;

            if(value <= arr[i - 1]) break;

            if(left < right) break;

            if(left == right) return true;
        }

        return false;
    }

}
