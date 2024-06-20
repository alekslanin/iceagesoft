package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
There's a stack of
N inflatable discs, with the
ith disc from the top having an initial radius of
𝑅 inches.
The stack is considered unstable if it includes at least one disc whose radius is larger than or equal to that of the disc directly under it. In other words, for the stack to be stable, each disc must have a strictly smaller radius than that of the disc directly under it.
As long as the stack is unstable, you can repeatedly choose any disc of your choice and deflate it down to have a radius of your choice which is strictly smaller than the disc’s prior radius. The new radius must be a positive integer number of inches.
Determine the minimum number of discs which need to be deflated in order to make the stack stable, if this is possible at all. If it is impossible to stabilize the stack, return
−1 instead.

Sample test case #1
N = 5
R = [2, 5, 3, 6, 5]
Expected Return Value = 3
Sample test case #2
N = 3
R = [100, 100, 100]
Expected Return Value = 2
Sample test case #3
N = 4
R = [6, 5, 4, 3]
Expected Return Value = -1
Sample Explanation
In the first case, One optimal way to stabilize the stack is by deflating disc
1 from 2" to1", deflating disc 2 from 5" to 2", and deflating disc 4 from 6" to 4".

In the second case, one optimal way to stabilize the stack is by deflating disc 1 from 100" to 1" and disc 2 from 100" to 10".

In the third case, it is impossible to make the stack stable after any number of deflations

 */
public class StackStabilizationTest {

    @Test
    void run() {
        assertEquals(3, getCount(5, new int[]{2, 5, 3, 6, 5}));  // 1, 2, [3], 4, [5]
        assertEquals(2, getCount(5, new int[]{2, 59, 30, 66, 55}));  // 1, 2, [3], 4, [5]
        assertEquals(2, getCount(3, new int[]{100, 100, 100}));
        assertEquals(-1, getCount(4, new int[]{6, 5, 4, 3}));
        assertEquals(-1, getCount(4, new int[]{6, 5, 2, 90}));
    }

    public int getCount(int N, int[] D) {
        int counter = 0;
        int prev = D[D.length - 1];
        if(prev < D.length) return -1;
        for(int i = D.length - 2; i > -1; i--) {
            if(D[i] < i + 1) return -1;
            int current = D[i];
            if(current >= prev) {
                D[i] = prev - 1;
                counter++;
            }
            prev = D[i];
        }

        return counter;
    }
}
