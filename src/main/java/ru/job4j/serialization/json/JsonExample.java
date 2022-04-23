package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * Класс описывает преобразование объекта в JSON и обратно.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class JsonExample {
    private final boolean permission;
    private final int experience;
    private final String passport;
    private final User user;
    private final Integer[] children;

    public JsonExample(boolean permission, int experience, String passport, User user, Integer[] children) {
        this.permission = permission;
        this.experience = experience;
        this.passport = passport;
        this.user = user;
        this.children = children;
    }

    @Override
    public String toString() {
        return "JsonExample{" + "permission=" + permission
                + ", experience=" + experience + ", passport='"
                + passport + '\'' + ", user=" + user + ", children="
                + Arrays.toString(children) + '}';
    }

    /**
     * Преобразуем объект jsonExample в json-строку через GsonBuilder().create()
     * и выводим в консоль.
     * Потом модифицируем json-строку и получаем объект jsonExampleMod
     * через gson.fromJson.
     *
     * @param args параметры
     */
    public static void main(String[] args) {
        JsonExample jsonExample = new JsonExample(true, 15, "111-1111",
                new User("Ivan", "Ivanov"), new Integer[]{10, 6});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(jsonExample));
        final String jsonExampleJson =
                "{"
                        + "\"permission\":false,"
                        + "\"experience\":10,"
                        + "\"passport\":\"222-2222\","
                        + "\"user\":{\"name\":\"Petr\",\"surname\":\"Petrov\"},"
                        + "\"children\":[3]"
                        + "}";
        final JsonExample jsonExampleMod = gson.fromJson(jsonExampleJson, JsonExample.class);
        System.out.println(jsonExampleMod);
    }
}
