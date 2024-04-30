package coding.rotationCipher;

public class CircularLinkedListTest {
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
            var buffer = new StringBuilder();
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

        public class CircularLinkedList {
            private Node head = null;
            private Node tail = null;

            static class Node {
                private final char value;
                private Node nextNode;

                public Node(char value) {
                    this.value = value;
                }
            }

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
