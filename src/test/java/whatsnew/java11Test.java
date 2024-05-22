package whatsnew;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class java11Test {

    @Test
    // Java 11 adds a few new methods to the String class: isBlank, lines, strip, stripLeading, stripTrailing, and repeat.
    void StringTest() {
        String multilineString = "Baeldung helps \n \n developers \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(s -> s.strip())
                .collect(Collectors.toList());
        assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");
    }

    @Test
    void IsNotPredicateMethodTest() {
        List<String> sampleList = Arrays.asList("Java", "\n \n", "Kotlin", " ");
        var withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank)) // <--
                .collect(Collectors.toList());
        assertThat(withoutBlanks).containsExactly("Java", "Kotlin");
    }

    /*
    Flight Recorder
    https://www.baeldung.com/java-flight-recorder-monitoring
     */


//    @Test
//    void CollectionToArrayTest() {
//        List sampleList = Arrays.asList("Java", "Kotlin");
//        String[] sampleArray = sampleList.toArray(String[]::new);
//        assertThat(sampleArray).containsExactly("Java", "Kotlin");
//    }
}
