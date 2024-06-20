package coding.rotationCipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotationCipherTest {

    @Test
    void run() {
        assertEquals("defDEF456", rotationalCipher("abcABC123", 3));
        assertEquals("defDEF", rotationalCipher("abcABC", (26*3 + 3)));
        assertEquals("xycXYC1", rotationalCipher("uvzUVZ8", 3));
        assertEquals("Cheud-726?", rotationalCipher("Zebra-493?", 3));
        assertEquals("nopqrstuvwxyzABCDEFGHIJKLM9012345678", rotationalCipher("abcdefghijklmNOPQRSTUVWXYZ0123456789", 39));
    }

    char[] numbers = "0123456789".toCharArray();
    char[] lower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().toCharArray();
    char[] upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    String rotationalCipher(String input, int rotationFactor) {
        var buf = new StringBuffer();
        input.chars().forEach(x -> buf.append(rotate((char)x, rotationFactor)));
        return buf.toString();
    }

    char rotate(char input, int rotationFactor) {
        if(Character.isLowerCase(input)) {
            return rotate(input, rotationFactor, lower);
        } else if(Character.isUpperCase(input)) {
            return rotate(input, rotationFactor, upper);
        } else if(Character.isDigit(input)) {
            return rotate(input, rotationFactor, numbers);
        } else return input;
    }

    char rotate(char input, int rotationFactor, char[] array) {
        int position = getPosition(input, array);

        if(position == -1) return input;

        int adjustedFactor = rotationFactor % array.length;

        int p = position + adjustedFactor;
        if(p >= array.length) p = p - array.length;
        return array[p];
    }

    int getPosition(char input, char[] array) {
        int position = -1;
        for (int index = 0; index < array.length; index++) {
            if (array[index] == input) {
                position = index;
            }
        }
        return position;
    }
}
