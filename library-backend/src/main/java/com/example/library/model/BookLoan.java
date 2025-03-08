package com.example.library.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book_loaned")
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name="account_id", insertable = false, updatable = false)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "book_copy_id", nullable = false)
    private BookCopy bookCopy;

    @Column(name="book_copy_id", insertable = false, updatable = false)
    private Long bookCopyId;

    private Date dateLoaned;
    private Date dateDue;
    private Date dateReturned;

    public BookLoan(Long accountId, Long bookCopyId, Date dateLoaned, Date dateDue, Date dateReturned) {
        this.accountId = accountId;
        this.bookCopyId = bookCopyId;
        this.dateLoaned = dateLoaned;
        this.dateDue = dateDue;
        this.dateReturned = dateReturned;
    }
}