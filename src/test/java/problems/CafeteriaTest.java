package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CafeteriaTest {

    @Test
    void run() {
        assertEquals(3, getMaxAdditionalDinersCount(10, 1, 2, new long[]{12, 6}));
        assertEquals(1, getMaxAdditionalDinersCount(15, 2, 3, new long[]{11, 6, 14}));
    }

    public long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        // Write your code here
        return 0L;
    }

}
