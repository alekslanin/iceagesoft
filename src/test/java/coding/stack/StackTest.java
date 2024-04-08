package coding.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackTest {

    @Test
    public void whenElementIsPoppedFromStack_thenElementIsRemovedAndSizeChanges() {
        Stack<Integer> intStack = new Stack<>();
        intStack.push(5);

        Integer element = intStack.pop();

        assertEquals(Integer.valueOf(5), element);
        assertTrue(intStack.isEmpty());
    }

    @Test
    public void whenElementIsOnStack_thenSearchReturnsItsDistanceFromTheTop() {
        Stack<Integer> intStack = new Stack<>();
        intStack.push(5);
        intStack.push(8);

        assertEquals(2, intStack.search(5));
    }

    @Test
    public void whenRemoveIfIsInvoked_thenAllElementsSatysfyingConditionAreRemoved() {
        Stack<Integer> intStack = new Stack<>();
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        intStack.addAll(intList);

        intStack.removeIf(element -> element < 6);

        assertEquals(2, intStack.size());
    }

    @Test
    public void whenAnotherStackCreatedWhileTraversingStack_thenStacksAreEqual() {
        Stack<Integer> intStack = new Stack<>();
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        intStack.addAll(intList);

        ListIterator<Integer> it = intStack.listIterator();

        Stack<Integer> result = new Stack<>();
        while(it.hasNext()) {
            result.push(it.next());
        }

        assertThat(result, equalTo(intStack));
    }

    @Test
    public void givenElementsOnStack_whenRemoveAllIsInvoked_thenAllElementsFromCollectionAreRemoved() {
        Stack<Integer> intStack = new Stack<>();
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        intStack.addAll(intList);
        intStack.add(500);

        intStack.removeAll(intList);

        assertEquals(1, intStack.size());
        assertEquals(1, intStack.search(500));
    }
}
