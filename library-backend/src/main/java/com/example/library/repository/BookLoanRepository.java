package com.example.library.repository;

import com.example.library.model.BookCopy;
import com.example.library.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    public BookCopy findByBookCopyId(Long bookCopyId);
    public BookLoan findByAccountIdAndBookCopyId(Long accountId, Long bookCopyId);
}