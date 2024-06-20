package coding.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountSubArrayTest {

    @Test
    void countSubArraysTest() {
        int[] test = {3, 4, 1, 6, 2};
        int[] expected = {1, 3, 1, 5, 1};

        var res = countSubArrays(test);

        assertNotNull(res);
        assertArrayEquals(expected, res);
    }

    int[] countSubArrays(int[] arr) {
        int[] result = new int[arr.length];

        for(int i = 0; i != arr.length; i++) {
            int max = arr[i];
            int counter = 0;
            for(int j = i; j != -1; j--) {
                if(arr[j] > max) break;
                counter++;
            }

            for(int j = i + 1; j != arr.length; j++) {
                if(arr[j] > max) break;
                counter++;
            }

            result[i] = counter;
        }

        return result;
    }
}
