package com.example.library.repository;

import com.example.library.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
   
    List<Book> findByAuthorId(Long authorId);
}