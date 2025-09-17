package ru.job4j.jdbc.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleConnectionPool {
    private final BlockingQueue<Connection> pool;

    public SimpleConnectionPool(String url, String username, String password, int poolSize) throws SQLException {
        pool = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, username, password);
            pool.offer(connection);
        }
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take(); // блокируется, если все заняты
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection); // возвращаем в БД коннект
    }

    public int getAvailableConnections() {
        return pool.size();
    }
}
