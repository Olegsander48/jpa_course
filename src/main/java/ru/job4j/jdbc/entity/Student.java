package ru.job4j.jdbc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    private int id;
    private String name;
    private String surname;
    private double avgGrade;

    public Student(String name, String surname, double avgGrade) {
        this.name = name;
        this.surname = surname;
        this.avgGrade = avgGrade;
    }
}
