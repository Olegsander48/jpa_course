package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ru.job4j.jdbc.entity.Student;

public class JDBCSelect {
    static String dbUrl = "jdbc:postgresql://localhost:5432/jpa";
    static String user = "postgres";
    static String password = "password";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM students WHERE avg_grade > ?"
            );
            preparedStatement.setDouble(1, 9.0);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<Student>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setAvgGrade(resultSet.getDouble("avg_grade"));
                students.add(student);
            }
            students.forEach(System.out::println);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
