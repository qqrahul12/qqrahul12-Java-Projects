package com.rahul.dsa.LinkedList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {

    @Test
    public void testLRUCacheEviction() {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);

        // Access "A" to make it recently used
        cache.get("A");

        // Add a new entry, "D", which should evict "B"
        cache.put("D", 4);

        assertTrue(cache.containsKey("A"));
        assertFalse(cache.containsKey("B"));
        assertTrue(cache.containsKey("C"));
        assertTrue(cache.containsKey("D"));
    }

    @Test
    public void testLRUCacheOrder() {
        LRUCache<String, Integer> cache = new LRUCache<>(2);
        cache.put("X", 10);
        cache.put("Y", 20);

        // Add a new entry, "Z", which should evict "X"
        cache.put("Z", 30);

        assertFalse(cache.containsKey("X"));
        assertTrue(cache.containsKey("Y"));
        assertTrue(cache.containsKey("Z"));
    }
}
