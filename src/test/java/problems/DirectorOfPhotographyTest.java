package problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
A photography set consists of
𝑁
N cells in a row, numbered from 1 to 𝑁 in order, and can be represented by a string 𝐶 of length 𝑁
Each cell
i is one of the following types A, P, B or .
A photograph consists of a photographer, an actor, and a backdrop, such that each of them is placed in a valid cell,
and such that the actor is between the photographer and the backdrop.
Such a photograph is considered artistic if the distance between the photographer and the actor is between
Determine the number of different artistic photographs which could potentially be taken at the set.
Two photographs are considered different if they involve a different photographer cell, actor cell, and/or backdrop cell.

Sample test case #3
N = 8
C = .PBAAP.B
X = 1
Y = 3
Expected Return Value = 3

Sample Explanation
In the third case, there are 4 possible photographs, illustrated as follows:
.P.A...B
.P..A..B
..BA.P..
..B.AP..

All are artistic except the first, where the actor and backdrop exceed the maximum distance of 3

 */
public class DirectorOfPhotographyTest {

    @Test
    void test() {
        assertEquals(1, getArtisticPhotographCount(5, "APABA", 1, 2));
        assertEquals(0, getArtisticPhotographCount(5, "APABA", 2, 3));
        assertEquals(3, getArtisticPhotographCount(8, ".PBAAP.B", 1, 3));
        assertEquals(1, getArtisticPhotographCount(7, ".PAB...", 1, 1));
        assertEquals(1, getArtisticPhotographCount(7, ".BAP...", 1, 10));
        assertEquals(5, getArtisticPhotographCount(8, "BBPA.BPP", 1, 10));
        assertEquals(10, getArtisticPhotographCount(18, ".B.B.P.A.A.B.P.P..", 1, 10));
        assertEquals(2, getArtisticPhotographCount(18, ".BB.P.AA.B..P.P........", 1, 3));
    }

    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        int[] P = new int[N + 1];
        int[] B = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            P[i] = 'P' == C.charAt(i - 1) ? P[i - 1] + 1 : P[i - 1];
            B[i] = 'B' == C.charAt(i - 1) ? B[i - 1] + 1 : B[i - 1];
        }

        int counter = 0;

        for (int i = 0; i < N; i++) {
            if ('A' == C.charAt(i)) {
                int min = Math.max(0, i + 1 - X);
                int max = Math.max(0, i - Y);
                int lpCount = P[min] - P[max];
                int rbCount = B[min] - B[max];

                min = Math.min(N, i + X);
                max = Math.min(N, i + 1 + Y);
                int lbCount = B[max] - B[min];
                int rpCount = P[max] - P[min];

                counter += (lpCount * lbCount) + (rpCount * rbCount);
            }
        }

        return counter;
    }
}
