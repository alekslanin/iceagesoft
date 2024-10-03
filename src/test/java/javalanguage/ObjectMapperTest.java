package javalanguage;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectMapperTest {

    @Test
    void run() {
        var input = new StudentInput("one", "alex lanin", 5.55);
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.convertValue(input, Student.class);
        assertNotNull(student);
        assertEquals(input.getMarks(), student.getMarks());
        assertNull(student.getLocation());
    }

    // mapstruct
    // TODO:
    // https://www.baeldung.com/mapstruct


    @Data
    @AllArgsConstructor
    public static class StudentInput {
        private String id;
        private String name;
        private double marks;
    }

    @Data
    public static class Student {
        private String id;
        private String name;
        private double marks;
        private double grade;
        private String location;
    }
}
