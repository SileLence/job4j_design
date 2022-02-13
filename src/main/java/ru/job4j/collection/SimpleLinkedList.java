package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
    	linkLast(value);
    }

    @Override
    public E set(int index, E value) {
        Node<E> current = getNodeByIndex(index);
        linkBefore(value, current);
        return current.value;
    }

    @Override
    public E remove(int index) {
        return unlink(getNodeByIndex(index));
    }

    @Override
    public E get(int index) {
        return getNodeByIndex(index).value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        final Node<E>[] arrayOfIterator = new Node[size];
        if (size != 0) {
            arrayOfIterator[0] = first;
            for (int i = 1; i < size; i++) {
                arrayOfIterator[i] = arrayOfIterator[i - 1].next;
            }
        }
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int cursor = 0;

        	@Override
        	public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
        		return cursor != size;
        	}
        	
        	@Override
        	public E next() {
        		if (!hasNext()) {
        			throw new NoSuchElementException();
        		}
        		return arrayOfIterator[cursor++].value;
        	}
        };
    }
    
    private void linkLast(E value) {
    	final Node<E> l = last;
    	final Node<E> newNode = new Node<>(l, value, null);
    	last = newNode;
    	if (l == null) {
    		first = newNode;
    	} else {
    		l.next = newNode;
    	}
        size++;
        modCount++;
    }
    
    private void linkBefore(E value, Node<E> current) {
    	final Node<E> before = current.prev;
    	final Node<E> newNode = new Node<>(before, value, current);
        current.prev = newNode;
        if (before == null) {
            first = newNode;
        } else {
            before.next = newNode;
        }
        size++;
        modCount++;
    }

    private E unlink(Node<E> deleted) {
        if (deleted.prev == null) {
            first = deleted.next;
        } else if (deleted.next == null) {
            last = deleted.prev;
        } else {
            deleted.prev.next = deleted.next;
            deleted.next.prev = deleted.prev;
        }
        size--;
        modCount++;
        return deleted.value;

    }

    private Node<E> getNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return first;
        } else if (index == size - 1) {
            return last;
        }
        Node<E> result = first.next;
        for (int i = 1; i < size - 1; i++) {
            if (i != index) {
                result = result.next;
            } else {
                break;
            }
        }
        return result;
    }
    
    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
