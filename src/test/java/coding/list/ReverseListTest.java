package coding.list;

import java.util.ArrayList;
import java.util.List;

public class ReverseListTest {
    public static List<Integer> reverseArray(List<Integer> a) {
        var r = new ArrayList<Integer>();
        a.forEach(x -> r.add(0, x));
        return r;

    }
}
