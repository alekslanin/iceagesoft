package coding.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillionUsersGrowthRateTest {

    /*
    We have N different apps with different user growth rates.
    At a given time t, measured in days, the number of users using an app is g^t (for simplicity we'll allow fractional users),
    where g is the growth rate for that app.
    These apps will all be launched at the same time and no user ever uses more than one of the apps.
    We want to know how many total users there are when you add together the number of users from each app.

    After how many full days will we have 1 billion total users across the N apps?

    Signature
    int getBillionUsersDay(float[] growthRates)

    Input
    1.0 < growthRate < 2.0 for all growth rates
    1 <= N <= 1,000

    Output

    Return the number of full days it will take before we have a total of 1 billion users across all N apps.

    Example 1
    growthRates = [1.5]
    output = 52

    Example 2
    growthRates = [1.1, 1.2, 1.3]
    output = 79

    Example 3
    growthRates = [1.01, 1.02]
    output = 1047
     */

    @Test
    void run() {
        float[] growthRates = new float[] {(float)1.1, (float)1.2, (float)1.3};
        int output = 79;

        assertEquals(output, getBillionUsersDay(growthRates));

//        growthRates = new float[] {(float)1.01, (float)1.02};
//        output = 1047;
//        assertEquals(output, getBillionUsersDay(growthRates));

    }
    private double userOnDay(float rate, int day) {
        return Math.pow(rate, day);
    }

    public int getBillionUsersDay(float[] growthRates) {
        int start = 1;
        int end = 2_000;
        double target = 1_000_000_000;

        while (start < end) {
            double total = 0;
            int mid = start + (end - start) / 2;

            for (float growthRate : growthRates) {
                total += userOnDay(growthRate, mid);
            }

            System.out.println("mdi :: " + mid + " total ::" + total);

            if (total == target) {
                return mid;
            } else if (total > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
