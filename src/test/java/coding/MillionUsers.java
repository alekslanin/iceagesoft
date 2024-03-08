package coding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MillionUsers {

    @Test
    void run() {
        float[] growthRates = new float[] {(float)1.1, (float)1.2, (float)1.3};
        int output = 79;

        assertEquals(output, getBillionUsersDay(growthRates));

        growthRates = new float[] {(float)1.01, (float)1.02};
        output = 1047;
        assertEquals(output, getBillionUsersDay(growthRates));

    }
    private double userOnDay(float rate, int day) {
        return Math.pow(rate, day);
    }

    public int getBillionUsersDay(float[] growthRates) {
        int start = 1;
        int end = 1000;
        double target = 1000000000;

        while (start < end) {
            double total = 0;
            int mid = start + (end - start) / 2;

            for (float growthRate : growthRates) {
                total += userOnDay(growthRate, mid);
            }

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
