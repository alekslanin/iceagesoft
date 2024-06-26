package coding.arrays;

import org.junit.jupiter.api.Test;

public class DummyArrayTest {
    @Test
    public void testArray() {

        var g = new int[2][3];
        g[0][0] = 0;
        g[0][1] = 0;
        g[0][2] = 1;
        g[1][0] = 1;
        g[1][1] = 0;
        g[1][2] = 1;

        int counter = 0;
        int total = 0;

        for(int i = 0; i != g.length; i++) {
            for(int j = 0; j != g[i].length; j++) {
                if(g[i][j] != 0) counter++;
                total++;
            }
        }

        double a = (double) counter /total;
        System.out.println("" + counter);
        System.out.println("" + total);
        System.out.println("" + a);
    }
}
