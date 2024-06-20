package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...
public class FibonacciTest {

    @Test
    void run() {
        assertEquals(144, nthFibonacciRecursive(12));
        assertEquals(144, nthFibonacciTerm(12));
        assertEquals(144, nthFibonacciBinetFormula(12));
    }

    public static int nthFibonacciRecursive(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return nthFibonacciTerm(n-1) + nthFibonacciTerm(n-2);
    }

    public static int nthFibonacciTerm(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        int n0 = 0, n1 = 1;
        int tempNthTerm;
        for (int i = 2; i <= n; i++) {
            tempNthTerm = n0 + n1;
            n0 = n1;
            n1 = tempNthTerm;
        }
        return n1;
    }

    /*
      Φ = ( 1 + √5 )/2 = 1.6180339887... <-- golden ratio
      Sn = Φⁿ–(– Φ⁻ⁿ)/√5  <-- formula
     */
    public static int nthFibonacciBinetFormula(int n) {
        double squareRootOf5 = Math.sqrt(5);
        double phi = (1 + squareRootOf5)/2;
        return (int) ((Math.pow(phi, n) - Math.pow(-phi, -n))/squareRootOf5);
    }

}
