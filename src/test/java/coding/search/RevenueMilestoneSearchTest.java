package coding.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevenueMilestoneSearchTest {

    /*
    Revenue Milestones
    We keep track of the revenue Facebook makes every day, and we want to know on what days Facebook hits certain revenue milestones. Given an array of the revenue on each day, and an array of milestones Facebook wants to reach, return an array containing the days on which Facebook reached every milestone.
    Signature
    int[] getMilestoneDays(int[] revenues, int[] milestones)
    Input
    revenues is a length-N array representing how much revenue FB made on each day (from day 1 to day N). milestones is a length-K array of total revenue milestones.
    Output
    Return a length-K array where K_i is the day on which FB first had milestones[i] total revenue. If the milestone is never met, return -1.
    Example
    revenues = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
    milestones = [100, 200, 500]
    output = [4, 6, 10]
    Explanation
    On days 4, 5, and 6, FB has total revenue of $100, $150, and $210 respectively. Day 6 is the first time that FB has >= $200 of total revenue.

     */
    @Test
    void run() {

        int[] revenues = new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[] milestones = new int[] {100, 200, 500};
        int[] output = new int[] {4, 6, 10};


//        int index = runBinarySearchClosest(revenues, 37, false);
//        assertEquals(3, index);
//        index = runBinarySearchClosest(revenues, 37, true);
//        assertEquals(2, index);
//
//        index = runBinarySearchClosest(revenues, 9, true);
//        assertEquals(-1, index);
//
//        index = runBinarySearchClosest(revenues, 9, false);
//        assertEquals(0, index);
//
//        index = runBinarySearchClosest(revenues, 30, false);
//        assertEquals(2, index);
//
//        index = runBinarySearchClosest(revenues, 30, true);
//        assertEquals(2, index);
//
//        int index2 = runBinarySearch(revenues, 50);
//        assertEquals(4, index2);
//        int index3 = runBinarySearch(revenues, 51);
//        assertEquals(-1, index3);

        var result = getMilestoneDays(revenues, milestones);
        assertEquals(output[0], result[0]);
        assertEquals(output[1], result[1]);
        assertEquals(output[2], result[2]);
    }

    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        int[] list = new int[revenues.length];
        int previous = 0;
        for (int i = 0; i != revenues.length; i++) {
            list[i] = previous + revenues[i];
            previous += revenues[i];
        }

        int[] result = new int[milestones.length];
        for (int i = 0; i != milestones.length; i++) {
            result[i] = runBinarySearchClosest(list, milestones[i], false);
        }

        return result;
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

        if(exact) return index + 1;
        return floor ? index : index + 1;
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
}
