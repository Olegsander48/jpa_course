package ru.job4j.hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;

    @Column(name = "avg_grade")
    private double avgGrade;

    public Student(String name, String surname, double avgGrade) {
        this.name = name;
        this.surname = surname;
        this.avgGrade = avgGrade;
    }
}
