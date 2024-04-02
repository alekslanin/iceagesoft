package coding;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayContentCompareTest {

    boolean areTheyEqual(int[] array_a, int[] array_b) {
        if(array_a.length != array_b.length) {
            return false;
        }

        Arrays.sort(array_a);
        Arrays.sort(array_b);
        for(int i = 0; i != array_a.length; i++) {
            if(array_a[i] != array_b[i]) return false;
        }
        return true;
    }

    @Test
    public void run() {
        int[] array_a_1 = {1, 2, 3, 4};
        int[] array_b_1 = {1, 4, 3, 2};

        boolean output_1 = areTheyEqual(array_a_1, array_b_1);

        assertTrue(output_1);

        int[] array_a_2 = {1, 2, 3, 4};
        int[] array_b_2 = {1, 4, 3, 3};
        boolean output_2 = areTheyEqual(array_a_2, array_b_2);
        assertFalse(output_2);
    }

}
