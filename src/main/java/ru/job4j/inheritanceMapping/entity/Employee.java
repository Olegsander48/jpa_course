package ru.job4j.inheritancemapping.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private int salary;
    @Column
    private double experience;

    protected Employee(String name, int salary, double experience) {
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }
}
