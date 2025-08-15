package ru.job4j.persistencecontext.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String subject;
    @Column(name = "is_professor")
    private boolean isProfessor;

    public Teacher(String name, String surname, String subject, boolean isProfessor) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.isProfessor = isProfessor;
    }

    @PrePersist
    void prePersist() {
        System.out.println("@Pre-Persist");
    }

    @PostPersist
    void postPersist() {
        System.out.println("@Post-Persist");
    }

    @PreUpdate
    void preUpdate() {
        System.out.println("@Pre-Update");
    }

    @PostUpdate
    void postUpdate() {
        System.out.println("@Post-Update");
    }

    @PreRemove
    void preRemove() {
        System.out.println("@Pre-Remove");
    }

    @PostRemove
    void postRemove() {
        System.out.println("@Post-Remove");
    }

    @PostLoad
    void postLoad() {
        System.out.println("@Post-Load");
    }

}
