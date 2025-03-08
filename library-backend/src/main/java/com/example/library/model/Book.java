package com.example.library.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @Column(name = "isbn")
    private String isbn;
    private String title;
    private String genre;
    private String publisher;
    private String language;
    @ManyToMany
    @JoinTable(
        name = "book_author", 
        joinColumns = @JoinColumn(name = "isbn"), 
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
    

    @OneToMany(mappedBy="book")
    private List<BookCopy> bookCopies;

    public Book(String isbn, String title, String genre, String publisher, String language) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.language = language;
    }
}
