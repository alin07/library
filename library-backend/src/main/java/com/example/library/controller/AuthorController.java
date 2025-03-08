package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    
    @GetMapping
    public List<Author> getAuthorsByBook(@RequestParam(name="isbn") String isbn) {
        return authorService.getAuthorsByBook(isbn);
    }

    @PostMapping
    public Author createAuthor(Author author) {
        return authorService.createAuthor(author);
    }

}
