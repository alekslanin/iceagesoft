package coding.trie;

import java.util.HashMap;

/*
Trie is also known as digital tree or prefix tree.
https://www.geeksforgeeks.org/introduction-to-trie-data-structure-and-algorithm-tutorials/



 */
class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    private static class TrieNode {
        // A map of child nodes indexed by the next character in the key
        private final HashMap<Character, TrieNode> children;

        // A flag to indicate that this node represents the end of a key
        private boolean endOfKey;

        public TrieNode() {
            children = new HashMap<>();
            endOfKey = false;
        }
    }

    // Inserts a key into the trie
    public void insert(String key) {
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                node = new TrieNode();
                current.children.put(c, node);
            }
            current = node;
        }
        current.endOfKey = true;
    }

    // Returns true if the trie contains the given key, false otherwise
    public boolean contains(String key) {
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.endOfKey;
    }

    // Returns true if the trie contains a key that starts with the given prefix, false otherwise
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }
}