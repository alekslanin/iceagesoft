package whatsnew;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class java17Test {

    @Test
    void switchExpression() {

        DayOfWeek day = DayOfWeek.FRIDAY;
        int numOfLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY                -> 7;
            case THURSDAY, SATURDAY     -> 8;
            case WEDNESDAY              -> 9;
        };

        assertEquals(6, numOfLetters);
    }

    @Test
    void PatternMatchingForInstanceOfIest() {
        var obj = "Hello World!";
        if (obj instanceof String) {
            int length = obj.length();
        }
    }
    @Test
    void switchPatternMatchingTest() {
        /*
        var result = switch (shape) {
            case Triangle t &&(t.getNumberOfSides() != 3) ->"This is a weird triangle";
            case Circle c &&(c.getNumberOfSides() != 0) ->"This is a weird circle";
                default -> "Just a normal shape";
        };
         */
    }
}
