package com.example.library.repository;

import com.example.library.model.BookCopy;
import com.example.library.model.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    public BookCopy findByBookCopyId(Long bookCopyId);
    public BookReservation findByAccountIdAndBookCopyId(Long accountId, Long bookCopyId);
}