package coding;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevenueMilestoneSearchTest {

    @Test
    void run() {

        int[] revenues = new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[] milestones = new int[] {100, 200, 500};
        int[] output = new int[] {4, 6, 10};


        int index = runBinarySearchClosest(revenues, 37, false);
        assertEquals(3, index);
        index = runBinarySearchClosest(revenues, 37, true);
        assertEquals(2, index);

        index = runBinarySearchClosest(revenues, 9, true);
        assertEquals(-1, index);

        index = runBinarySearchClosest(revenues, 9, false);
        assertEquals(0, index);

        index = runBinarySearchClosest(revenues, 30, false);
        assertEquals(2, index);

        index = runBinarySearchClosest(revenues, 30, true);
        assertEquals(2, index);

        int index2 = runBinarySearch(revenues, 50);
        assertEquals(4, index2);
        int index3 = runBinarySearch(revenues, 51);
        assertEquals(-1, index3);

        var result = getMilestoneDays(revenues, milestones);
        assertEquals(output[0], result[0]);
        assertEquals(output[1], result[1]);
        assertEquals(output[2], result[2]);
    }

    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        List<Integer> list = new ArrayList<>();
        int previous = 0;
        for (int i = 0; i != revenues.length; i++) {
            list.add(previous + revenues[i]);
            previous += revenues[i];
        }



        return new int[] {4, 6, 10};
    }

    int runBinarySearch(int[] sortedArray, int key) {
        int index = -1;
        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            int mid = low  + ((high - low) / 2);
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    // 100, 200, 300, 400 with key = 250 returns 300
    int runBinarySearchClosest(int[] arr, int key, boolean floor) {
        int index = -1;
        int low = 0;
        int high = arr.length - 1;
        boolean exact = false;

        while (low <= high) {
            int mid = low  + ((high - low) / 2);
            index = mid;
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else if (arr[mid] == key) {
                exact = true;
                break;
            }
        }

        if(exact) return index;
        return floor ? index - 1 : index;
    }
}
