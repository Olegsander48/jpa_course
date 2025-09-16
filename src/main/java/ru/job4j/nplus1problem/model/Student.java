package ru.job4j.nplus1problem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double grade;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @ToString.Exclude
    private University university;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}
