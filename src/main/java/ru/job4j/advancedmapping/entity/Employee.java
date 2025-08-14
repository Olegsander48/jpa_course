package ru.job4j.advancedmapping.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "emp_friends", joinColumns = @JoinColumn(name = "emp_id"))
    @Column(name = "friend_name")
    List<String> friends;

    public Employee(String firstName, int salary, double experience, List<String> friends) {
        this.firstName = firstName;
        this.salary = salary;
        this.experience = experience;
        this.friends = friends;
    }
}
