package javalanguage;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://www.baeldung.com/java-groupingby-collector

public class LambdaTest {

    public static record Data(String name, int age, float salary) {
    }

    private static final List<Data> list = List.of(
            new Data("sasha", 125, 100.0F),
            new Data("bob", 25, 100.0F),
            new Data("sasha", 25, 100.0F),
            new Data("sasha", 25, 100.0F),
            new Data("alena", 75, 100.0F),
            new Data("greg", 55, 100.0F),
            new Data("anna", 25, 100.0F),
            new Data("anna", 125, 100.0F) );

    @Test
    void groupBySimpleTest() {
        var result = list.stream().collect(groupingBy(x -> x.age));
        assertEquals(4, result.size());

        var result1 = list.stream().collect(groupingBy(x -> x.salary));
        assertEquals(1, result1.size());
    }

    @Test
    void groupByComplexKeyTest() {
        var result = list.stream().collect(groupingBy(post -> new ImmutablePair<>(post.age(), post.name())));
        assertEquals(7, result.size());

//        Map<BlogPostType, Integer> likesPerType = posts.stream()
//                .collect(groupingBy(BlogPost::getType, summingInt(BlogPost::getLikes)));
    }
}
