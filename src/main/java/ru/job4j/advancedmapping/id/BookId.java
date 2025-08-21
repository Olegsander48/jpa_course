package ru.job4j.advancedmapping.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookId implements Serializable {
    private String author;
    private String name;
}
