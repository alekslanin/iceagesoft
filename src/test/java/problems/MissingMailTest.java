package problems;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
In the first case, packages will never be stolen. You should therefore enter the mail room just once, on the final day, at which point there are sure to be
5 packages there with a total value of 10+2+8+6+4=30 dollars. Subtracting the
5-dollar fee for entering the mail room, your profit is guaranteed to be 30−5=25 dollars.

In the second case, each package is sure to be stolen at the end of the day on which its delivered. You should enter the mail room on days
1,3, and 4, each time collecting just the package delivered on that day. This yields a guaranteed profit of
10+8+6−(3∗5)=9 dollars.

In the third case, on each day, there's a
50% chance that all packages in the mail room will be stolen. You should enter the mail room on days
1,3,4, and 5.
Note that, when you enter on day 3, there will be a 50% chance of the room having 2 packages (with values of 2 and 8 dollars),
and a 50% chance of the room having just 1 package (worth 8 dollars).

In the fourth case, you should only enter the mail room on days
1 and 5.

 */
public class MissingMailTest {

    @Test
    void run() {
        assertEquals(25.00, getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 5, 0.0));
        assertEquals(9.00, getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 5, 1.0));
        assertEquals(17.00, getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 3, 0.5));
        assertEquals(20.10825000, getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 3, 0.15));
    }

    public double getMaxExpectedProfit(int N, int[] V, int C, double S) {
        // Write your code here
        return 25.0;
    }
}
