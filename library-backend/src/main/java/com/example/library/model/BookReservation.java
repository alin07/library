package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "book_reservation")
public class BookReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "book_copy_id", nullable = false)
    private BookCopy bookCopy;

    @Column(name = "book_copy_id", insertable = false, updatable = false)
    private Long bookCopyId;

    private Date dateReserved;
    private Date dateUpdated;

    public BookReservation(Long account_id, Long bookCopyId, Date dateReserved, Date dateUpdated){
        this.accountId = account_id;
        this.bookCopyId = bookCopyId;
        this.dateReserved = dateReserved;
        this.dateUpdated = dateUpdated;
    }
}