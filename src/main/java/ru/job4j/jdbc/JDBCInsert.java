package ru.job4j.jdbc;

import ru.job4j.jdbc.entity.Student;
import java.sql.*;

public class JDBCInsert {
    static String dbUrl = "jdbc:postgresql://localhost:5432/jpa";
    static String user = "postgres";
    static String password = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            Student student = new Student("Glev", "Newwel", 8.8);

            PreparedStatement statement = connection.prepareStatement(
                    "insert into students(name, surname, avg_grade) values(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setDouble(3, student.getAvgGrade());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert failed");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1)); //first column for indexes
            } else {
                throw new SQLException("Failed to get the generated id");
            }
            statement.close();
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
