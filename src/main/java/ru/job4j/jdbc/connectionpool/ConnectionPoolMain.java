package ru.job4j.jdbc.connectionpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolMain {
    static String dbUrl = "jdbc:postgresql://localhost:5432/jpa";
    static String user = "postgres";
    static String password = "password";

    public static void main(String[] args) throws SQLException {
        SimpleConnectionPool connectionPool = new SimpleConnectionPool(dbUrl, user, password, 5);

        ExecutorService pool = Executors.newFixedThreadPool(10);
        Runnable runnable = () -> {
            Connection connection = null;
            try {
                connection = connectionPool.getConnection();
                System.out.println(Thread.currentThread().getName());
                ResultSet resultSet = connection.prepareStatement("SELECT * FROM student")
                        .executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name") + " " + resultSet.getString("grade"));
                }
                Thread.sleep(5000);
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        };
        for (int i = 0; i < 10; i++) {
            pool.execute(runnable);
        }
        pool.shutdown();
    }
}
