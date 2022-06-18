package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
    @Ignore
    @Test
    public void whenGenerate() {
        Generator generator = new GeneratorOne();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        assertEquals("I am a Petr Arsentev, Who are you? ", generator.produce(template, map));
    }

    /**
     * Когда шаблоне есть ключи, которых нет в карте.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateIncorrectly() {
        Generator generator = new GeneratorOne();
        String template = "I am a ${name}, Who are ${subject}, my friend ${friend}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        generator.produce(template, map);
    }

    /**
     * Когда в карте есть лишние ключи.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapIncorrectly() {
        Generator generator = new GeneratorOne();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        map.put("friend", "friend");
        generator.produce(template, map);
    }
}