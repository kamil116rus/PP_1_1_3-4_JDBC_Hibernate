package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/myDB";
    private static final String LOGIN = "root";
    private static final String PASS = "root";

    // реализуйте настройку соеденения с БД
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASS);
            Statement statement = connection.createStatement();
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД Установлено!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
