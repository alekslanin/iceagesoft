package problems.matcher;

import java.util.HashMap;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    private static class TrieNode {
        private final HashMap<Character, TrieNode> children;
        private boolean endOfKey;

        public TrieNode() {
            children = new HashMap<>();
            endOfKey = false;
        }
    }

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
