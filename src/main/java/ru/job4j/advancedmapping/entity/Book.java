package ru.job4j.advancedmapping.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.advancedmapping.id.BookId;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@IdClass(BookId.class)
public class Book {
    @Id
    private String author;
    @Id
    private String name;

    @Column(name = "publication_date")
    private int publicationDate;
    private double rating;

    public Book(String author, String name, int publicationDate, double rating) {
        this.author = author;
        this.name = name;
        this.publicationDate = publicationDate;
        this.rating = rating;
    }
}
