package ru.job4j.jdbc.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс описывает перевод данных из файла в БД.
 * Предварительно создаем таблицу users в БД
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод читает из файла, разбивает строки по ";"
     * и добавляет их в List<User>.
     *
     * @return список users
     * @throws IOException исключения
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] words = s.split(";");
                if (words.length != 2) {
                    throw new IllegalArgumentException("Wrong arguments");
                }
                users.add(new User(words[0], words[1]));
            });
        }
        return users;
    }

    /**
     * Метод сохраняет лист users в БД.
     * Регистрируем драйвер, создаем соединение.
     * Идем циклом по листу users.
     * Создаем PreparedStatement, указываем значение параметров и execute().
     *
     * @param users лист users
     * @throws ClassNotFoundException исключения
     * @throws SQLException           исключения
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "insert into users(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    /**
     * Для чтения из файла appDB.properties используем класс Properties.
     * Для загрузки настроек используем ClassLoader.
     *
     * @param args аргументы
     * @throws Exception исключения
     */
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("appDB.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
