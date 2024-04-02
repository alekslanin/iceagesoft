package coding.sorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/*
https://www.geeksforgeeks.org/bucket-sort-2/

Complexity Analysis of Bucket Sort Algorithm:
Time Complexity: O(n2),

If we assume that insertion in a bucket takes O(1) time then steps 1 and 2 of the above algorithm clearly take O(n) time.
The O(1) is easily possible if we use a linked list to represent a bucket.
Step 4 also takes O(n) time as there will be n items in all buckets.
The main step to analyze is step 3. This step also takes O(n) time on average if all numbers are uniformly distributed.

Auxiliary Space: O(n+k)
 */
public class BucketSortAndInsertSortTest {

    /*
    https://www.geeksforgeeks.org/insertion-sort/?ref=lbp

    Time Complexity of Insertion Sort
    The worst-case time complexity of the Insertion sort is O(N^2)
    The average case time complexity of the Insertion sort is O(N^2)
    The time complexity of the best case is O(N).

    Space Complexity of Insertion Sort
    The auxiliary space complexity of Insertion Sort is O(1)
    */

    static void insertionSort(List<Float> bucket) {
        for (int i = 1; i < bucket.size(); ++i) {

            float key = bucket.get(i);

            int j = i - 1;
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }

    @Test
    void run () {
        float[] arr = {
                (float)0.897,
                (float)0.565,
                (float)0.656,
                (float)0.1234,
                (float)0.665,
                (float)0.3434 };

        float[] expected = { (float)0.1234,  (float)0.3434,  (float)0.565,  (float)0.656,  (float)0.665,  (float)0.897 };

        bucketSort(arr, arr.length);
        assertArrayEquals(expected, arr);

    }

    void bucketSort(float[] arr, int n)
    {
        if (n <= 0) return;

        // 1) Create n empty buckets
        List<Float>[] buckets = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int)idx].add(arr[i]);
        }

        // 3) Sort individual buckets
        for (int i = 0; i < n; i++) {
            //Collections.sort(buckets[i]);
            insertionSort(buckets[i]);

        }

        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }
}
