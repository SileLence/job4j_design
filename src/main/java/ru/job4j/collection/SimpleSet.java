package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private final SimpleArrayList<T> set;

    public SimpleSet() {
        set = new SimpleArrayList<>(10);
    }

    @Override
    public boolean add(T value) {
        boolean result = !contains(value);
        if (result) {
            set.add(value);
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T item : set) {
            if (Objects.equals(value, item)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
