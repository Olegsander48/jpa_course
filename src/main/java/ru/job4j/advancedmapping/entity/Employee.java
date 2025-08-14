package ru.job4j.advancedmapping.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private int salary;
    private double experience;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "emp_country")),
            @AttributeOverride(name = "city", column = @Column(name = "emp_city"))
    })
    private Address address;

    public Employee(String firstName, int salary, double experience, Address address) {
        this.firstName = firstName;
        this.salary = salary;
        this.experience = experience;
        this.address = address;
    }
}
