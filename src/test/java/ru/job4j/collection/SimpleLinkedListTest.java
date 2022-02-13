package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SimpleLinkedListTest {

    @Test
    public void whenGetSize() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size(), is(3));
    }

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenSetToFirstAndLastThenGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.set(0, 5);
        list.set(3, 6);
        assertThat(list.get(0), is(5));
        assertThat(list.get(3), is(6));
        assertThat(list.get(4), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenAddIteratorHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenAddIteratorNextOne() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenEmptyIteratorHasNextFalse() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        it.hasNext();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddIteratorMultiHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenAddIteratorNextOneNextTwo() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(1));
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(2));
        assertThat(first.hasNext(), is(false));
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(1));
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(2));
        assertThat(second.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGetIteratorThenAddNewValue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        it.next();
        list.add(3);
        it.next();
    }

    @Test
    public void whenRemoveThenGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertThat(list.remove(2), is(3));
        assertThat(list.get(2), is(4));
    }

    @Test
    public void whenRemoveFirstElementThenGetFirst() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(0);
        assertThat(list.get(0), is(2));
    }

    @Test
    public void whenRemoveLastElementThenGetLast() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2);
        assertThat(list.get(1), is(2));
    }
}
