package ru.job4j.jdbc;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Класс описывает случаи создания таблицы, добавления столбцов,
 * их переименование и удаление, удаление самой таблицы.
 * Для чтения из файла app.properties используем класс Properties.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод описывает подключение к БД через драйвер.
     * Открываем символьный поток и читаем properties через load().
     * После добавления зависимости на драйвер в pom.xml, нам необходимо его
     * зарегистрировать в системе через Class.forName.
     * Для подключения нам нужны url, логин (имя пользователя) и пароль.
     * Для чтения из файла app.properties используем класс Properties.
     * Чтобы получить подключение нужно воспользоваться классом DriverManager,
     * передав ему эти аргументы. Теперь мы получили объект типа Connection.
     * Далее этот объект идет в конструктор TableEditor.
     *
     * @throws Exception исключение
     */
    private void initConnection() throws Exception {
        Path path = Paths.get("./data/app.properties");
        try (FileReader fr = new FileReader(path.toFile())) {
            properties.load(fr);
        }
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * Метод создает пустую таблицу.
     *
     * @param tableName имя таблицы
     * @throws Exception исключение
     */
    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("create table if not exists %s();", tableName);
            statement.execute(sql);
        }
    }

    /**
     * Метод удаляет таблицу.
     *
     * @param tableName имя таблицы
     * @throws Exception исключение
     */
    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table %s", tableName);
            statement.execute(sql);
        }
    }

    /**
     * Метод добавляет столбец.
     *
     * @param tableName  имя таблицы
     * @param columnName имя столбца
     * @param type       тип данных столбца
     * @throws Exception исключение
     */
    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s add %s %s",
                    tableName, columnName, type);
            statement.execute(sql);
        }
    }

    /**
     * Метод удаляет столбец.
     *
     * @param tableName  имя таблицы
     * @param columnName имя столбца
     * @throws Exception исключение
     */
    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s drop column %s",
                    tableName, columnName);
            statement.execute(sql);
        }
    }

    /**
     * Метод переименовывает столбец.
     *
     * @param tableName     имя таблицы
     * @param columnName    имя столбца
     * @param newColumnName новое имя столбца
     * @throws Exception исключение
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s rename column %s to %s",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
        }
    }

    /**
     * Метод выводит схему таблицы через StringJoiner().
     * Через executeQuery() выполняем запрос SELECT.
     * Получаем метаданные ResultSet, идем по ним циклом
     * и добавляем в StringJoiner().
     *
     * @param connection объект Connection
     * @param tableName  название таблицы
     * @return строку из объекта StringJoiner()
     * @throws SQLException исключение
     */
    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        String rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        String header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        StringJoiner buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (Statement statement = connection.createStatement()) {
            ResultSet selection = statement.executeQuery(String.format("select * from %s limit 1",
                    tableName));
            ResultSetMetaData metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("Cars");
        System.out.println(getTableScheme(tableEditor.connection, "Cars"));

        tableEditor.addColumn("Cars", "model", "text");
        System.out.println(getTableScheme(tableEditor.connection, "Cars"));

        tableEditor.renameColumn("Cars", "model", "brand");
        System.out.println(getTableScheme(tableEditor.connection, "Cars"));

        tableEditor.dropColumn("Cars", "brand");
        System.out.println(getTableScheme(tableEditor.connection, "Cars"));

        tableEditor.dropTable("Cars");
    }
}
