package coding.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/*
https://www.studytonight.com/data-structures/quick-sort#google_vignette

Quick Sort is one of the different Sorting Technique which is based on the concept of Divide and Conquer,
just like merge sort. But in quick sort all the heavy lifting(major work) is done while dividing the array into subarrays,
while in case of merge sort, all the real work happens during merging the subarrays.
In case of quick sort, the combine step does absolutely nothing.

if partitioning leads to almost equal subarrays, then the running time is the best, with time complexity as O(n*log n).

Worst Case Time Complexity [ Big-O ]: O(n2)

Best Case Time Complexity [Big-omega]: O(n*log n)

Average Time Complexity [Big-theta]: O(n*log n)

Space Complexity: O(n*log n)

 */
public class QuickSortTest {

    @Test
    public void positiveTest() {
        int[] actual = {100, 200, -1, 2, 5, 1, 6, 2, 3, 4 };
        int[] expected = { -1, 1, 2, 2, 3, 4, 5, 6, 100, 200 };

//        mergeSort(actual, actual.length);
//        assertArrayEquals(expected, actual);

        sort(actual, 0, actual.length - 1);
        assertArrayEquals(expected, actual);

    }

    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index
    static void sort(int[] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot
    static int partition(int[] arr, int low, int high)
    {
        // Choosing the pivot as last element.
        // It can also be first, middle or random
        int pivot = arr[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j] < pivot) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
