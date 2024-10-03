package javalanguage;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/*

A method reference in Java is a special type of lambda expression that allows you to reference existing methods.
It simplifies code and enhances readability. There are four types of method references:

Reference to a Static Method: You can refer to a static method using the :: operator. For example, instead of using a lambda expression to capitalize and print a list of strings, you can directly reference the StringUtils.capitalize() method like this:
Java

List<String> messages = Arrays.asList("hello", "baeldung", "readers!");
messages.forEach(StringUtils::capitalize);


Reference to an Instance Method of a Particular Object: When comparing objects, you can use a method reference to handle parameter passing.
For instance, if you have a BicycleComparator object to compare bicycle frame sizes, you can sort a list of bicycles like this:
Java

createBicyclesList().stream().sorted(bikeFrameSizeComparator::compare);


Reference to an Instance Method of an Arbitrary Object of a Particular Type: This type of method reference is similar to the previous example but doesn’t require creating a custom object. For example, to sort a list of integers:
Java

List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);
numbers.stream().sorted(Integer::compareTo);

Constructor Reference: You can also reference constructors using method references.

 */
public class MethodReferenceTest {

    @Test
    public void run() {
        List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);
        var result = numbers.stream().sorted(Integer::compareTo).toList();
        var result2 = numbers.stream().sorted((o1, o2) -> o1.compareTo(o2)).toList();

        org.hamcrest.MatcherAssert.assertThat(result, Matchers.containsInAnyOrder(result2.toArray()));
        org.assertj.core.api.AssertionsForInterfaceTypes.assertThat(result).hasSameElementsAs(result2);
    }

    @FunctionalInterface
    interface StringFunction {
        String run(String str);
    }

}
