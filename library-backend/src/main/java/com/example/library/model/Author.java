package com.example.library.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Author {
    @Id
    private Long authorId;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;


}
