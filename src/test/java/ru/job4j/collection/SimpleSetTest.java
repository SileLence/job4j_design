package ru.job4j.collection;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleSetTest {
    Set<Integer> set = new SimpleSet<>();

    @Test
    public void whenAddNonNull() {
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenNotContainsNull() {
        set.add(1);
        set.add(2);
        assertFalse(set.contains(null));
    }

    @Test
    public void whenEmptySetNotContainsAnything() {
        assertFalse(set.contains(0));
        assertFalse(set.contains(null));
    }
}
