package coding.sorting;

import org.junit.jupiter.api.Test;

import static java.lang.System.arraycopy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/*
https://www.geeksforgeeks.org/merge-sort/?ref=lbp

Complexity Analysis of Merge Sort
Time Complexity: O(N log(N)),  Merge Sort is a recursive algorithm and time complexity can be expressed as following recurrence relation.

T(n) = 2T(n/2) + θ(n)

The above recurrence can be solved either using the Recurrence Tree method or the Master method. It falls in case II of the Master Method and the solution of the recurrence is θ(Nlog(N)). The time complexity of Merge Sort isθ(Nlog(N)) in all 3 cases (worst, average, and best) as merge sort always divides the array into two halves and takes linear time to merge two halves.

Auxiliary Space: O(N), In merge sort all elements are copied into an auxiliary array. So N auxiliary space is required for merge sort.

Applications of Merge Sort:
Sorting large datasets: Merge sort is particularly well-suited for sorting large datasets due to its guaranteed worst-case time complexity of O(n log n).
External sorting: Merge sort is commonly used in external sorting, where the data to be sorted is too large to fit into memory.
Custom sorting: Merge sort can be adapted to handle different input distributions, such as partially sorted, nearly sorted, or completely unsorted data.
Inversion Count Problem

Advantages of Merge Sort:
Stability: Merge sort is a stable sorting algorithm, which means it maintains the relative order of equal elements in the input array.
Guaranteed worst-case performance: Merge sort has a worst-case time complexity of O(N logN), which means it performs well even on large datasets.
Parallelizable: Merge sort is a naturally parallelizable algorithm, which means it can be easily parallelized to take advantage of multiple processors or threads.

Drawbacks of Merge Sort:
Space complexity: Merge sort requires additional memory to store the merged sub-arrays during the sorting process.
Not in-place: Merge sort is not an in-place sorting algorithm, which means it requires additional memory to store the sorted data. This can be a disadvantage in applications where memory usage is a concern.
Not always optimal for small datasets: For small datasets, Merge sort has a higher time complexity than some other sorting algorithms, such as insertion sort. This can result in slower performance for very small datasets.

 */

public class MergeSortTest {
    @Test
    public void positiveTest() {
        int[] actual = {100, 200, -1, 5, 1, 6, 2, 3, 4 };
        int[] expected = { -1, 1, 2, 3, 4, 5, 6, 100, 200 };

        simpleMergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);

        int[] actual1 = {100, 200, -1, 5, 1, 6, 2, 3, 4 };
        mergeSort(actual1, 0, actual.length - 1);
        assertArrayEquals(expected, actual1);

    }

    // Main function that sorts arr[l..r] using
    // merge()
    void mergeSort(int[] arr, int l, int r)
    {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    // Merges two sub arrays of arr[].
    // First sub array is arr[l..m]
    // Second sub array is arr[m+1..r]
    void merge(int[] arr, int l, int m, int r)
    {
        // Find sizes of two sub arrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    public static void simpleMergeSort(int[] array, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[n - mid];

        arraycopy(array, 0, leftArray, 0, mid);
        if (n - mid >= 0) arraycopy(array, mid, rightArray, 0, n - mid);

        simpleMergeSort(leftArray, mid);
        simpleMergeSort(rightArray, n - mid);

        merge(array, leftArray, rightArray, mid, n - mid);
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
