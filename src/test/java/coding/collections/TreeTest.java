package coding.collections;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeTest {

    // public class TreeMap<K,V> extends AbstractMap<K,V> implements NavigableMap<K,V>, Cloneable, java.io.Serializable

    // TreeMap is a map implementation that keeps its entries sorted according to the natural ordering of its keys
    // or better still using a comparator if provided by the user at construction time.

    // BINARY SEARCH TREE

    // a RED_BLACK tree is a self-balancing binary search tree.
    // This attribute and the above guarantee that basic operations like search, get, put and remove take logarithmic time O(log n).

    @Test
    public void givenTreeMap_whenOrdersEntriesNaturally_thenCorrect() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(-5, "val");
        map.put(5, "val");
        map.put(4, "val");

        assertEquals("[-5, 1, 2, 3, 4, 5]", map.keySet().toString());
    }

    @Test
    public void givenTreeMap_whenOrdersEntriesByComparator_thenCorrect() {
        TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");

        assertEquals("[5, 4, 3, 2, 1]", map.keySet().toString());
    }

    @Test
    public void givenTreeMap_whenPerformsQueries_thenCorrect() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");

        Integer highestKey = map.lastKey();
        Integer lowestKey = map.firstKey();
        Set<Integer> keysLessThan3 = map.headMap(3).keySet();
        Set<Integer> keysGreaterThanEqTo3 = map.tailMap(3).keySet();

        assertEquals(5, highestKey);
        assertEquals(1, lowestKey);
        assertEquals("[1, 2]", keysLessThan3.toString());
        assertEquals("[3, 4, 5]", keysGreaterThanEqTo3.toString());
    }
}
