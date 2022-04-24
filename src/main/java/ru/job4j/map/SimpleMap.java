package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private static int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return (hashCode * 31) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= (capacity * LOAD_FACTOR)) {
            capacity *= 2;
            @SuppressWarnings("unchecked")
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (int i = 0; i < table.length - 1; i++) {
                if (table[i] != null) {
                    int hash = hash(table[i].key.hashCode());
                    newTable[indexFor(hash)] = table[i];
                }
            }
            table = newTable;
        }
    }

    @Override
    public boolean put(K key, V value) {
        expand();
        boolean result = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null && Objects.equals(key, table[index].key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null && Objects.equals(key, table[index].key)) {
            table[index] = null;
            result = true;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

