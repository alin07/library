package com.example.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="book_copy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_copy_id")
    private Long bookCopyId;

    @ManyToOne 
    @JoinColumn(name = "isbn", nullable = false)
    private Book book;

    // @ManyToOne 
    // @JoinColumn(name = "book_format_id", nullable = false)
    // @Enumerated(EnumType.STRING)
    private BookFormat bookFormat;

    public BookCopy(Book book, BookFormat bookFormat) {
        this.book = book;
        this.bookFormat = bookFormat;
    }
}

