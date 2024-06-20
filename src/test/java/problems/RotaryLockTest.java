package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotaryLockTest {

    @Test
    void run() {
        assertEquals(2, getMinCodeEntryTime(3, 3, new int[]{1, 2, 3}));
        assertEquals(11, getMinCodeEntryTime(10, 4, new int[]{9, 4, 4, 8}));
    }

    public long getMinCodeEntryTime(int N, int M, int[] C) {
        int pointer = 1;
        int seconds = 0;
        for (int i = 0; i != C.length; i++) {
            int value = C[i];
            //int a = Math.abs(pointer - value);
            int a = (value - pointer) % N;  // positive move
            a = (a < 0) ? (a + N) : a;
            int b = N - a;
            seconds += Math.min(a, b);
            pointer = value;
        }

        return seconds;
    }
}
