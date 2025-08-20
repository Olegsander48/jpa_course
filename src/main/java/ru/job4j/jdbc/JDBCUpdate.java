package ru.job4j.jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBCUpdate {
    static String dbUrl = "jdbc:postgresql://localhost:5432/jpa";
    static String user = "postgres";
    static String password = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter student name: ");
            String enteredName = scanner.nextLine();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE students SET avg_grade = 7.5 WHERE name = ?"
            );
            preparedStatement.setString(1, enteredName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
