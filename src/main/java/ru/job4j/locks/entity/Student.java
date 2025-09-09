package ru.job4j.locks.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private double grade;
    private int age;

    @Version
    private int version;

    public Student(String name, String surname, double grade, int age) {
        this.name = name;
        this.surname = surname;
        this.grade = grade;
        this.age = age;
    }
}
