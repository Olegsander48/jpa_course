package ru.job4j.jdbc;

import ru.job4j.jdbc.entity.Student;
import java.sql.*;

public class JDBCInsert {
    static String dbUrl = "jdbc:postgresql://localhost:5432/jpa";
    static String user = "postgres";
    static String password = "password";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            Student student = new Student("Vladimir", "Haff", 5.5);

            Statement statement = connection.createStatement();
            String sqlQuery = "INSERT INTO students(name, surname, avg_grade) VALUES " +
                    "('" + student.getName() + "', '" +
                    student.getSurname() +  "', " +
                    student.getAvgGrade() + ");";
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
