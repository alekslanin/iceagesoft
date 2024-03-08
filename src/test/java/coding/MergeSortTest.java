package coding;

import org.junit.jupiter.api.Test;

import static java.lang.System.arraycopy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// https://www.geeksforgeeks.org/merge-sort/?ref=lbp
public class MergeSortTest {
    @Test
    public void positiveTest() {
        int[] actual = {100, 200, -1, 5, 1, 6, 2, 3, 4 };
        int[] expected = { -1, 1, 2, 3, 4, 5, 6, 100, 200 };
        mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
    }

    public static void mergeSort(int[] array, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        arraycopy(array, 0, left, 0, mid);
        if (n - mid >= 0) arraycopy(array, mid, right, 0, n - mid);

        mergeSort(left, mid);
        mergeSort(right, n - mid);

        merge(array, left, right, mid, n - mid);
    }

    static void merge( int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }

        while (i < left) {
            a[k++] = l[i++];
        }

        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
