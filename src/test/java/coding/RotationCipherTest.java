package coding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotationCipherTest {

    @Test
    void run() {
//     "abcdxyzABCXYZ123".chars().forEach(x ->   System.out.println("x=" + (char)x + " int =" + (int)x));

//        assertEquals("defDEF456", rotationalCipher("abcABC123", 3));
//        assertEquals("xycXYC1", rotationalCipher("uvzUVZ8", 3));
//        assertEquals("Cheud-726?", rotationalCipher("Zebra-493?", 3));
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
        int position = -1;
        for (int index = 0; index < array.length; index++) {
            if (array[index] == input) {
                position = index;
            }
        }

        int adjustedFactor = rotationFactor % array.length;
        int p = position + adjustedFactor;
        if(p >= array.length) p = p - array.length;
        return array[p];
    }


    class Cipher {

        char[] numbers = "0123456789".toCharArray();
        char[] lower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().toCharArray();
        char[] upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        private final int key;

        private final CircularLinkedList lowercase = new CircularLinkedList();
        private final CircularLinkedList uppercase = new CircularLinkedList();

        public Cipher(int key) {
            this.key = key;
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().chars().forEach(x -> lowercase.addNode((char) x));
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().forEach(x -> uppercase.addNode((char) x));
        }

        public String encode(String inputText) {
            var buffer = new StringBuffer();
            var chars = inputText.toCharArray();

            for (int i = 0; i != chars.length; i++) {
                var value = chars[i];
                if (Character.isLowerCase(value)) {
                    var shifted = lowercase.getShiftedValue(value);
                    buffer.append(shifted);
                } else if (Character.isUpperCase(value)) {
                    var shifted = uppercase.getShiftedValue(value);
                    buffer.append(shifted);
                } else buffer.append(value);
            }
            return buffer.toString();
        }


        class Node {
            private char value;
            private Node nextNode;

            public Node(char value) {
                this.value = value;
            }
        }

        public class CircularLinkedList {
            private Node head = null;
            private Node tail = null;

            public void addNode(char value) {
                Node newNode = new Node(value);

                if (head == null) head = newNode;
                else tail.nextNode = newNode;

                tail = newNode;
                tail.nextNode = head;
            }

            public char getShiftedValue(char value) {

                int pos = key;
                boolean shift = false;

                while (true) {
                    Node currentNode = head;

                    do {
                        if (shift) --pos;
                        else {
                            if (currentNode.value == value) {
                                shift = true;
                            }
                        }

                        if (shift && pos <= 0) {
                            return currentNode.value;
                        }

                        currentNode = currentNode.nextNode;

                    } while (currentNode != head);
                }
            }
        }
    }
}
