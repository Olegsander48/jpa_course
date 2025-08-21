package ru.job4j.relationships.onetomany.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "founding_date")
    private Date foundingDate;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Student> students;

    public University(String name, Date foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setUniversity(this); //set current university to student
    }
}
