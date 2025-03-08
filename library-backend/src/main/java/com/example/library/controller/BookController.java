package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.model.BookCreation;
import com.example.library.model.BookFormat;
import com.example.library.service.AuthorService;
import com.example.library.service.BookCopyService;
import com.example.library.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Book> getAllBooks(
        @RequestParam int page, 
        @RequestParam(name="per_page") int perPage) 
    {
        Page<Book> books = bookService.getBooksByPage(page, perPage);
        return books.getContent();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}/books")
    public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable(value="authorId") Long authorId) {
        List<Book> books = bookService.getBooksByAuthorId(authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookCreation creation) {
        String isbn = creation.getIsbn();
        Book book = bookService.getBookByIsbn(isbn);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.CONFLICT);
        }

        book = new Book(
            isbn,
            creation.getTitle(),
            creation.getGenre(), 
            creation.getPublisher(), 
            creation.getLanguage());

        List<Long> authorIds = creation.getAuthorIds();
        List<Author> authors = authorService.findAllById(authorIds);
        book.setAuthors(authors);
        book = bookService.createBook(book);
        
        BookFormat format = BookFormat.valueOf(BookFormat.class, creation.getFormat().toUpperCase());
        BookCopy bookCopy = new BookCopy(book, format);
        bookCopyService.createBookCopy(bookCopy);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book bookDetails) {
        return bookService.updateBook(isbn, bookDetails);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        // TODO: DELETE ALL BOOK COPIES OF THIS BOOK
        bookService.deleteBook(isbn);
    }
}