package coding.arrays;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class FizzBuzzTest {

    @Test
    void runTest() {
        fizz(10);
    }

    void fizz(int n) {
        IntStream
                .rangeClosed(1, n)
                .mapToObj( i -> {
                    if( i % 5 == 0 && i % 7 == 0) return "FizzBuzz";
                    if( i % 5 == 0) return "Fizz";
                    if(i % 7 == 0) return "Buzz";
                    return i;
                })
                .forEach(System.out::println);
    }
}
