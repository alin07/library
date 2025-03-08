package com.example.library.repository;

import com.example.library.model.BookCopy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    List<BookCopy> findByBook(String isbn);
}