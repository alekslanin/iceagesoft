package problems;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstUniqueLetterTest {

    @Test
    void run() {
        assertEquals(3, get("aoazcobqqbw"));
        assertEquals(-1, get("aabb"));
        assertEquals(0, get("zaabb"));
        assertEquals(3, get("aoazcobqqbw"));
        assertEquals(10, get("aoazcobqqbwzc"));
    }

    public static int get(String str) {
        if(str == null || str.isEmpty()) return -1;
        var map = str.chars().mapToObj(x -> (char)x).collect(groupingBy(x -> x, LinkedHashMap::new, counting()));
        var letter = map.entrySet().stream().filter(x -> x.getValue() == 1).map(x -> x.getKey()).findFirst();
        return letter.map(str::indexOf).orElse(-1);
    }
}
