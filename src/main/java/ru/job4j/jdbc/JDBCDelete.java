package ru.job4j.jdbc;

import java.sql.*;

public class JDBCDelete {
    static String dbUrl = "jdbc:postgresql://localhost:5432/jpa";
    static String user = "postgres";
    static String password = "password";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            preparedStatement.setInt(1, 6);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
