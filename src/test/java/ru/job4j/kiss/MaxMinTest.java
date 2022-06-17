package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

public class MaxMinTest {

    @Test
    public void whenMax() {
        List<Integer> list = List.of(19, 7, 100, -8, 0);
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> com = Integer::compareTo;
        assertThat(maxMin.max(list, com), is(100));
    }

    @Test
    public void whenMin() {
        List<Integer> list = List.of(19, 7, 100, -8, 0);
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> com = Integer::compareTo;
        assertThat(maxMin.min(list, com), is(-8));
    }
}