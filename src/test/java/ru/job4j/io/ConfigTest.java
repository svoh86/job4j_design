package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Mikhail"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Mikhail"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithPatternViolation() {
        String path = "./data/pair_with_pattern_violation.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithPatternViolationAnother() {
        String path = "./data/pair_with_pattern_violation_another.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithPatternViolationAnotherTwo() {
        String path = "./data/pair_with_pattern_violation_another2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname"), is("Ivanov=1"));
    }
}