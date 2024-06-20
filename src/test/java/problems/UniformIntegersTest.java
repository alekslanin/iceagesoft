package problems;

import org.junit.jupiter.api.Test;
import java.util.stream.LongStream;
import static org.junit.jupiter.api.Assertions.assertEquals;


/*
A positive integer is considered uniform if all of its digits are equal. For example,
222 is uniform, while
223 is not.

Given two positive integers
A and
𝐵, determine the number of uniform integers between
A and 𝐵, inclusive.
Please take care to write a solution which runs within the time limit.

A = 75
B = 300
Expected Return Value = 5

A = 1
B = 9
Expected Return Value = 9

A = 999999999999
B = 999999999999
Expected Return Value = 1


*/
public class UniformIntegersTest {

    @Test
    void run() {
        assertEquals(5, getUniformIntegerCountInInterval0(75, 300));
        // 77, 88, 99, 111, 222,
        assertEquals(1, getUniformIntegerCountInInterval1(999999999999L, 999999999999L));
        assertEquals(9, getUniformIntegerCountInInterval1(1, 9));
    }

    public int getUniformIntegerCountInInterval0(long A, long B) {
        var len_a = len(A);
        var len_b = len(B);
        var tmp_a = ones(len_a);
        var tmp_b = ones(len_b);

        var nb_a = (tmp_a * 10 - A) / tmp_a;
        var nb_b = B / tmp_b;

        var nb_m = (len_b - len_a >= 2) ? (9 * (len_b - len_a - 1)) : 0;

        var nb = nb_a + (long)nb_m + nb_b;

        if (len_a == len_b)  nb -= 9;

        return (int)nb;
    }

    int len(long value) {
        int len = 0;
        while(value > 0) {
            value /= 10;
            len++;
        }
        return len;
    }

    long ones(long value) {
        long l = 0L;
        int i = 0;
        while (i < value) {
            i += 1;
            l = l * 10 + 1;
        }
        return l;
    }

    public int getUniformIntegerCountInInterval1(long A, long B) {
        return (int) LongStream.range(A, B + 1)
                .parallel()
                .mapToObj(x -> {
                    boolean uniformed = true;
                    char[] arr = String.valueOf(x).toCharArray();
                    int first = arr[0];
                    for (int i = 1; i != arr.length; i++) {
                        if (first != arr[i]) {
                            uniformed = false;
                            break;
                        }
                    }
                    return uniformed;
                })
                .filter(x -> x)
                .count();
    }

    public int getUniformIntegerCountInInterval2(long A, long B) {
        int counter = 0;
        for(long l = A; l <= B; l++) {
            boolean uniformed = true;
            char[] arr = String.valueOf(l).toCharArray();
            char first = arr[0];

            for (int i = 1; i != arr.length; i++) {
                if(first != arr[i]) {
                    uniformed = false;
                    break;
                }
            }

            if(uniformed) counter++;
        }

        return counter;
    }
}
