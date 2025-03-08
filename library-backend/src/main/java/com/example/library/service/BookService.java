package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getBooksByPage(int page, int perPage) {
        PageRequest booksPaginated = PageRequest.of(page, perPage);
        return bookRepository.findAll(booksPaginated);
    }

    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book bookDetails) {
        Book book = bookRepository.findById(isbn).orElse(null);
        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setGenre(bookDetails.getGenre());
            book.setPublisher(bookDetails.getPublisher());
            book.setLanguage(bookDetails.getLanguage());
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}