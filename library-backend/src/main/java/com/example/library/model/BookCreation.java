package com.example.library.model;

import java.util.List;

import lombok.Data;

@Data
public class BookCreation {
    private String isbn;
    private String title;
    private String genre;
    private String publisher;
    private String language;
    private List<Long> authorIds;
    private String format;
}
