package coding.arrays;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostFrequentTest {

    @Test
    void run () {

        int arr[] = {40,50,30,40,50,30,30};
        int n = arr.length;

        assertEquals(30, mostFrequent(arr, n));
    }


    static int mostFrequent(int[] arr, int n) {
        Map<Integer, Integer> hp = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            int key = arr[i];
            if (hp.containsKey(key)) {
                int freq = hp.get(key);
                freq++;
                hp.put(key, freq);
            } else {
                hp.put(key, 1);
            }
        }

        return findMax(hp);
    }

    // find max frequency.
    static int findMax(Map<Integer, Integer> hp) {
        Map.Entry<Integer, Integer> max = Collections.max(hp.entrySet(), comparingInt(e -> e.getValue()));
        return max.getKey();
    }

    static int findMaxManually(Map<Integer, Integer> hp) {
        int max_count = 0, res = -1;

        for(Map.Entry<Integer, Integer> val : hp.entrySet())
        {
            if (max_count < val.getValue())
            {
                res = val.getKey();
                max_count = val.getValue();
            }
        }
        return res;
    }
}
