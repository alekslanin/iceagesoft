package coding.arrays;

import org.junit.jupiter.api.Test;

import java.io.Console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountSubArrayTest {

    @Test
    void countSubArraysTest() {
        int[] test = {3, 4, 1, 6, 2};
        int[] expected = {1, 3, 1, 5, 1};

        var res = countSubArrays(test);

        assertNotNull(res);
        assertEquals(res[0], expected[0]);
        assertEquals(res[res.length - 1], expected[expected.length - 1]);
    }

    int[] countSubArrays(int[] arr) {
        int[] result = new int[arr.length];

        for(int i = 0; i != arr.length; i++) {
            int max = arr[i];
            int counter = 0;
            for(int j = i; j != -1; j--) {
                System.out.println(arr[j]);
                if(arr[j] > max) break;
                counter++;
            }

            for(int j = i + 1; j != arr.length; j++) {
                System.out.println(arr[j]);
                if(arr[j] > max) break;
                counter++;
            }

            result[i] = counter;
        }

        return result;
    }
}
