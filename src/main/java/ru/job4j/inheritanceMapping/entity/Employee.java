package ru.job4j.inheritancemapping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int salary;
    private double experience;

    public Employee(String name, int salary, double experience) {
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }
}
