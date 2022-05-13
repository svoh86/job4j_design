package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс описывает пример подключения к БД через драйвер.
 * После добавления зависимости на драйвер в pom, нам необходимо его
 * зарегистрировать в системе через Class.forName.
 * Для подключения нам нужны url, логин (имя пользователя) и пароль.
 * Для чтения из файла app.properties используем класс Config из раздела IO.
 * Чтобы получить подключение нужно воспользоваться классом DriverManager,
 * передав ему эти аргументы. Теперь мы получили объект типа Connection.
 * Если он не равен null, то это значит, что установлено подключение.
 * Для получения информации о БД и ее внутренней структуре существует
 * класс DatabaseMetaData.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("./src/main/resources/app.properties");
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getUserName());
        }
    }
}
