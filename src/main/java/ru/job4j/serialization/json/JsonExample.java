package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isPermission() {
        return permission;
    }

    public int getExperience() {
        return experience;
    }

    public String getPassport() {
        return passport;
    }

    public User getUser() {
        return user;
    }

    public Integer[] getChildren() {
        return children;
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

        /* JSONObject из json-строки строки */
        JSONObject jsonUser = new JSONObject("{\"name\":\"Petr\",\"surname\":\"Petrov\"}");
        /* JSONArray из ArrayList */
        List<Integer> list = new ArrayList<>();
        list.add(15);
        list.add(13);
        JSONArray jsonChildren = new JSONArray(list);
        /* JSONObject напрямую методом put */
        final JsonExample jSONExample = new JsonExample(true, 15, "111-1111",
                new User("Ivan", "Ivanov"), new Integer[]{10, 6});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("permission", jSONExample.isPermission());
        jsonObject.put("experience", jSONExample.getExperience());
        jsonObject.put("passport", jSONExample.getPassport());
        jsonObject.put("user", jsonUser);
        jsonObject.put("children", jsonChildren);
        System.out.println(jsonObject.toString());
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(jSONExample).toString());
    }
}
