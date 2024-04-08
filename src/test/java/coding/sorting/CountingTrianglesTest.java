package coding.sorting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountingTrianglesTest {

    @Getter
    @Setter
    @AllArgsConstructor
    static class Sides{
        int a, b, c;
    }


    @Test
    void run() {
        // arr = [[2, 2, 3], [3, 2, 2], [2, 5, 6]]
        // output = 2

        var arr = Stream.of(new Sides(2,2,3), new Sides(3,2,2), new Sides(2,5,6)).collect(Collectors.toCollection(ArrayList::new));
        //List<Sides> list = List.of(new Sides(2,2,3), new Sides(3,2,2), new Sides(2,5,6));
        assertEquals(2, countDistinctTriangles(arr));

        //arr = [[5, 8, 9], [5, 9, 8], [9, 5, 8], [9, 8, 5], [8, 9, 5], [8, 5, 9]]
        //output = 1

        var arr1 = Stream.of(new Sides(5,8,9),new Sides(5,8,9),new Sides(5,8,9),new Sides(5,8,9),new Sides(5,8,9) ).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(1, countDistinctTriangles(arr1));

        var arr2 = Stream.of(new Sides(5,8,9),new Sides(50,8,9),new Sides(5,8,9),new Sides(5,8,9),new Sides(5,8,9) ).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(2, countDistinctTriangles(arr2));
    }

//    String getAsString(Sides sides) {
//        int[] arr = new int[] {sides.a, sides.b, sides.c};
//        var result = Arrays.stream(arr).map(x -> x.).collect(Collectors.toList());
//    }

    int countDistinctTriangles(ArrayList<Sides> arr) {
        return (int)arr
                .stream()
                .map(x -> {
                    int[] array = new int[] {x.a, x.b, x.c};
                    Arrays.sort(array);
                    return array[0] + "-" + array[1] + "-" + array[2];
                })
                .distinct()
                .count();
    }

}
