package ru.job4j.relationships.manytomany.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String subject;

    @Column(name = "is_professor")
    private boolean isProfessor;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "teacher_uni",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id")
    )
    @ToString.Exclude
    private List<University> universities;

    public Teacher(String name, String surname, String subject, boolean isProfessor) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.isProfessor = isProfessor;
        universities = new ArrayList<>();
    }

    public void  addUniversity(University university) {
        universities.add(university);
    }
}
