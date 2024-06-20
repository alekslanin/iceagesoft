package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
There are
N dishes in a row on a kaiten belt, with the
𝑖ith dish being of type 𝐷
Some dishes may be of the same type as one another.
You're very hungry, but you'd also like to keep things interesting. The
N dishes will arrive in front of you, one after another in order, and for each one you'll eat it as long as it isn't the same type as any of the previous
K dishes you've eaten. You eat very fast, so you can consume a dish before the next one gets to you.
Any dishes you choose not to eat as they pass will be eaten by others.
Determine how many dishes you'll end up eating.
 */
public class KaitenSushiTest {

    @Test
    void run() {
        assertEquals(5, getMaximumEatenDishCount(6,  new int[] {1, 2, 3, 3, 2, 1}, 1));
        assertEquals(4, getMaximumEatenDishCount(6,  new int[] {1, 2, 3, 3, 2, 1}, 2));
        assertEquals(2, getMaximumEatenDishCount(7,  new int[] {1, 2, 1, 2, 1, 2, 1}, 2));
        assertEquals(5, getMaximumEatenDishCount(8,  new int[] {1, 2, 1, 2, 3, 4, 2, 1}, 3));
        assertEquals(6, getMaximumEatenDishCount(8,  new int[] {1, 2, 1, 2, 3, 4, 1, 2}, 3));
    }

    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        FixedSizeQueue fifo = new FixedSizeQueue(K);
        int counter = 0;
        for(int i = 0; i != D.length; i++) {
           if(fifo.contains(D[i])) continue;
           fifo.offer(D[i]);
           counter++;
        }

        return counter;
    }

    public static class FixedSizeQueue {
        final int[] items;
        int count;

        public FixedSizeQueue(int capacity) {
            super();

            items = new int[capacity];
            count = 0;
        }

        public void offer(int e) {
            if (count == items.length) {
                this.poll();
            }

            this.items[count] = e;
            count++;
        }

        public boolean contains(int x) {
            for (int i = 0; i < count; i++) {
                if(items[i] == x) return true;
            }
            return false;
        }

        public void poll() {
            if (count <= 0) {
                return;
            }
            int item = items[0];
            shiftLeft();
            count--;
        }

        private void shiftLeft() {
            int i = 1;
            while (i < items.length) {
                items[i - 1] = items[i];
                i++;
            }
        }
    }
}
