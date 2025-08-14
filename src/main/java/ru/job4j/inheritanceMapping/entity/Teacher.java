package ru.job4j.inheritancemapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher extends Employee {
    @Column
    private String subject;

    @Column(name = "is_professor")
    private boolean isProfessor;

    public Teacher(String name, int salary, double experience, String subject, boolean isProfessor) {
        super(name, salary, experience);
        this.subject = subject;
        this.isProfessor = isProfessor;
    }
}
