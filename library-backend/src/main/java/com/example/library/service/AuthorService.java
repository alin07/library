package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
       return authorRepository.save(author);
    }

    public List<Author> getAuthorsByBook(String isbn) {
        return authorRepository.findByBooksIsbn(isbn);
    }

    public List<Author> findAllById(List<Long> authorIds) {
        return authorRepository.findAllByAuthorId(authorIds);
    }

}
