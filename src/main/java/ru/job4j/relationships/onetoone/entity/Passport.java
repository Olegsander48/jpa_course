package ru.job4j.relationships.onetoone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private int height;

    @Column(name = "eye_color")
    private String eyeColor;

    @OneToOne(mappedBy = "passport",
              cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private Student student;

    public Passport(String email, int height, String eyeColor) {
        this.email = email;
        this.height = height;
        this.eyeColor = eyeColor;
    }
}
