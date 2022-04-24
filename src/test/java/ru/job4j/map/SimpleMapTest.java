package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SimpleMapTest {

    @Test
    public void whenPutDifferentKeysThenTrue() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        boolean expected = map.put("Maldives", 812798);
        assertTrue(expected);
    }

    @Test
    public void whenPutSameKeysThenFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        boolean expected = map.put("Madagascar", 345);
        assertFalse(expected);
    }

    @Test
    public void whenPutNullKeyThenTrue() {
        Map<String, Integer> map = new SimpleMap<>();
        boolean expected = map.put(null, 0);
        assertTrue(expected);
    }

    @Test
    public void whenIteratorHasNextThenTrue() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        map.put("Maldives", 812798);
        Iterator<String> it = map.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
    }

    @Test
    public void whenIteratorHasNextWithEmptyMapThenFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        Iterator<String> it = map.iterator();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextWithEmptyMap() {
        Map<String, Integer> map = new SimpleMap<>();
        Iterator<String> it = map.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenMapWasChangedThenIterator() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        Iterator<String> it = map.iterator();
        it.next();
        map.put("Maldives", 812798);
        it.next();
    }

    @Test
    public void whenGetExistingThenReturnValue() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        map.put("Maldives", 812798);
        Integer expected = 812798;
        Integer actual = map.get("Maldives");
        assertEquals(expected, actual);
    }

    @Test
    public void whenGetNonExistingThenReturnNull() {
        Map<String, Integer> map = new SimpleMap<>();
        Integer expected = null;
        Integer actual = map.get("Maldives");
        assertEquals(expected, actual);
    }

    @Test
    public void whenGetNullThenReturnValue() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        map.put("Maldives", 812798);
        map.put(null, 0);
        Integer expected = 0;
        Integer actual = map.get(null);
        assertEquals(expected, actual);
    }

    @Test
    public void whenGetSameIndexButDifferentKeysThenNull() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        map.put("Maldives", 812798);
        Object actual = map.get("Crete");
        assertNull(actual);
    }

    @Test
    public void whenRemoveThenTrue() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        map.put("Maldives", 812798);
        boolean actual = map.remove("Maldives");
        assertTrue(actual);
    }

    @Test
    public void whenRemoveNullThenFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        boolean actual = map.remove(null);
        assertFalse(actual);
    }

    @Test
    public void whenRemoveThenFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Madagascar", 345);
        map.put("Maldives", 812798);
        boolean actual = map.remove("Phu Quoc");
        assertFalse(actual);
    }

    @Test
    public void whenRemoveEmptyTableThenFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        boolean actual = map.remove("Madagascar");
        assertFalse(actual);
    }

    @Test
    public void whenRemoveSameIndexButDifferentKeysThenFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("Maldives", 345);
        boolean actual = map.remove("Crete");
        assertFalse(actual);
    }
}
