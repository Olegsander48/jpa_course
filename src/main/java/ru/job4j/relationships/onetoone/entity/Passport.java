package ru.job4j.relationships.onetoone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Passport(String email, int height, String eyeColor) {
        this.email = email;
        this.height = height;
        this.eyeColor = eyeColor;
    }
}
