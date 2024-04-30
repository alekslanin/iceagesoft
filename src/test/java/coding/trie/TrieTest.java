package coding.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieTest {
    @Test
    public void testTrie() {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("car");
        trie.insert("cart");
        trie.insert("cartoon");

        assertTrue(trie.contains("cart"));
        assertFalse(trie.contains("dog"));
        assertTrue(trie.startsWith("ca"));
        assertFalse(trie.startsWith("d"));
    }
}