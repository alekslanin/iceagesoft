package coding.arrays;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArraysToListAndSearchTest {

    int[] revenues = new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

    List<Integer> list = Stream.of(10, 20).toList();

    List<Integer> list2 = Arrays.asList(new Integer[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 });

    int i = Collections.binarySearch(list2, 100);  // returns exact value
    int ii = Arrays.binarySearch(revenues, 50);

    @Test
    void run() {
       // "abcdxyzABCXYZ123".chars().forEach(x ->   System.out.println("x=" + (char)x + " int =" + (int)x));

        assertEquals(i, 9);
        assertEquals(ii, 4);

        assertEquals(-2, Arrays.binarySearch(revenues, 17));

    }
}
