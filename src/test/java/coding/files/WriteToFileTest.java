package coding.files;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.readAllLines;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class WriteToFileTest {

    @Test
    public void givenUsingFileWriter_whenStringList_thenGetTextFile() {
        try {
            String fileName = WriteToFileTest.write(FILENAME, list);
            //long count = readAllLines(Paths.get(fileName), UTF_8).size();
            long count = lines(Paths.get(fileName), UTF_8).count();
            assertTrue("No. of lines in file should be equal to no. of Strings in List", ((int) count) == list.size());
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static final String FILENAME = "C:\\sandbox\\temp\\test.txt";
    private static final List<String> list = Arrays.asList("Hello", "World", "blah", "blah");

    public static String write(String filename, List<String> stringList) throws IOException {
        Path path = Paths.get(filename);
        Files.deleteIfExists(path);
        Files.createFile(path);

        stringList.forEach(line -> {
            try {
                Files.writeString(path, line + System.lineSeparator(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return path.toString();
    }
}
