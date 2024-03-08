package coding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ArraysToListAndSearch {

    int[] revenues = new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

    List<Integer> list = Stream.of(10, 20).toList();

    int i = Collections.binarySearch(Arrays.asList(new Integer[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 }), 100);  // returns exact value
    int ii = Arrays.binarySearch(revenues, 50);
}
