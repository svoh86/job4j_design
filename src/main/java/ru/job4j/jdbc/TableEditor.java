package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Класс описывает случаи создания таблицы, добавления столбцов,
 * их переименование и удаление, удаление самой таблицы.
 * Для чтения из файла app.properties используем класс Properties.
 * Для загрузки настроек используем ClassLoader.
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
     * После добавления зависимости на драйвер в pom.xml, нам необходимо его
     * зарегистрировать в системе через Class.forName.
     * Для подключения нам нужны url, логин (имя пользователя) и пароль.
     * Чтобы получить подключение нужно воспользоваться классом DriverManager,
     * передав ему эти аргументы. Теперь мы получили объект типа Connection.
     * Далее этот объект идет в конструктор TableEditor.
     *
     * @throws Exception исключение
     */
    private void initConnection() throws Exception {
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
     */
    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s();", tableName);
        execute(sql);
    }

    /**
     * Метод удаляет таблицу.
     *
     * @param tableName имя таблицы
     */
    public void dropTable(String tableName) {
        String sql = String.format("drop table %s", tableName);
        execute(sql);
    }

    /**
     * Метод добавляет столбец.
     *
     * @param tableName  имя таблицы
     * @param columnName имя столбца
     * @param type       тип данных столбца
     */
    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add %s %s",
                tableName, columnName, type);
        execute(sql);
    }

    /**
     * Метод удаляет столбец.
     *
     * @param tableName  имя таблицы
     * @param columnName имя столбца
     */
    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table %s drop column %s",
                tableName, columnName);
        execute(sql);
    }

    /**
     * Метод переименовывает столбец.
     *
     * @param tableName     имя таблицы
     * @param columnName    имя столбца
     * @param newColumnName новое имя столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table %s rename column %s to %s",
                tableName, columnName, newColumnName);
        execute(sql);
    }

    /**
     * Метод выполняет sql-запрос.
     *
     * @param sql Строка sql-запроса
     */
    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
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

    /**
     * Для чтения из файла app.properties используем класс Properties.
     * Ресурсные файлы в папке resources по пути src/main/resources.
     * Для загрузки настроек используем ClassLoader,
     * который будет искать файлы по названию именно в этой папке.
     * Чтобы закрывать созданное подключение, используем TableEditor с try with resources
     *
     * @param args аргументы
     * @throws Exception исключения
     */
    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
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
    }
}
