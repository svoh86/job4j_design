package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SimpleMapTest {
    Map<Integer, String> map = new SimpleMap<>();

    @Test
    public void whenAddTrue() {
        assertTrue(map.put(1, "first"));
        assertTrue(map.put(2, "second"));
    }

    @Test
    public void whenAddFalse() {
        assertTrue(map.put(1, "first"));
        assertFalse(map.put(1, "first"));
    }

    @Test
    public void whenGetValue() {
        map.put(1, "first");
        map.put(2, "second");
        assertThat(map.get(2), is("second"));
    }

    @Test
    public void whenGetNull() {
        map.put(1, "first");
        map.put(2, "second");
        assertNull(map.get(3));
    }

    @Test
    public void whenRemoveTrue() {
        map.put(1, "first");
        map.put(2, "second");
        assertTrue(map.remove(1));
    }

    @Test
    public void whenRemoveFalse() {
        map.put(1, "first");
        map.put(2, "second");
        assertFalse(map.remove(3));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        map.put(1, "first");
        Iterator<Integer> iterator = map.iterator();
        map.put(2, "second");
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        map.put(1, "first");
        map.put(2, "second");
        map.put(3, "third");
        Iterator<Integer> iterator = map.iterator();
        map.remove(2);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test
    public void whenCheckIterator() {
        map.put(1, "first");
        map.put(2, "second");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
    }

    @Test
    public void whenCheckIteratorMulti() {
        for (int i = 0; i < 10; i++) {
            assertTrue(map.put(i, ""));
        }
    }
}