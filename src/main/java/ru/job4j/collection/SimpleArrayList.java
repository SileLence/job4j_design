package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size + 1 > container.length) {
            container = Arrays.copyOf(
                    container, container.length * 2
            );
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[index];
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        if (!indexValidation(index)) {
            throw new IndexOutOfBoundsException();
        }
        T removedValue = container[index];
        if (index != container.length - 1) {
            System.arraycopy(
                    container,
                    index + 1,
                    container, index,
                    container.length - index - 1
            );
        }
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removedValue;
    }

    @Override
    public T get(int index) {
        if (!indexValidation(index)) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }

    private boolean indexValidation(int index) {
        try {
            return Objects.checkIndex(index, container.length) == index;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }
}
