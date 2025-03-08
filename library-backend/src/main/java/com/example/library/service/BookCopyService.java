package com.example.library.service;

import com.example.library.model.BookCopy;
import com.example.library.model.BookLoan;
import com.example.library.model.BookReservation;
import com.example.library.repository.BookCopyRepository;
import com.example.library.repository.BookLoanRepository;
import com.example.library.repository.BookReservationRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private BookLoanRepository bookLoanRepository;
    @Autowired 
    private BookReservationRepository bookReservationRepository;

    public BookCopy getBookCopyById(Long bookCopyId) {
        return bookCopyRepository.findById(bookCopyId).orElse(null);
    }

    public List<BookCopy> getBookCopiesByBook(String isbn) {
        return bookCopyRepository.findByBook(isbn);
    }

    public BookCopy createBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public boolean isBookCopyAvailable(Long bookCopyId) {
        BookCopy bookCopy = getBookCopyById(bookCopyId);
        if (bookCopy == null) return false;
        return bookLoanRepository.findByBookCopyId(bookCopyId) == null &&
            bookReservationRepository.findByBookCopyId(bookCopyId) == null;
    }

    public BookLoan loanBookCopy(Long bookCopyId, Long accountId) {
        if (!isBookCopyAvailable(bookCopyId)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        Date current = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 14);

        BookLoan bookLoan = new BookLoan(
            accountId, 
            bookCopyId,
            current,
            cal.getTime(),
            null
        );
        return bookLoanRepository.save(bookLoan);
    }

    public BookReservation reserveBookCopy(Long bookCopyId, Long accountId) {
        if (!isBookCopyAvailable(bookCopyId)) return null;
        
        BookReservation bookReservation = new BookReservation(
            accountId, 
            bookCopyId, 
            new Date(),
            new Date()
        );
        return bookReservationRepository.save(bookReservation);
    }

    public BookLoan returnBookLoan(Long bookCopyId, Long accountId) {
        BookLoan bookLoan = bookLoanRepository.findByAccountIdAndBookCopyId(accountId, bookCopyId);
        if (bookLoan.getDateReturned() != null) {
            return null;
        }
        bookLoan.setDateReturned(new Date());
        return bookLoanRepository.save(bookLoan);
    }

    @Transactional(timeout = 10)
    public BookLoan loanBookReservation(Long bookCopyId, Long accountId) {
        BookReservation bookReservation = bookReservationRepository.findByAccountIdAndBookCopyId(accountId, bookCopyId); 
        if (bookReservation == null || 
            bookReservation.getDateReserved().before(bookReservation.getDateUpdated())) {
                return null;
        }
        
        bookReservation.setDateUpdated(new Date());
        bookReservationRepository.save(bookReservation);
        return loanBookCopy(bookCopyId, accountId);
    }

    public void deleteBookCopy(Long bookCopyId) throws UnavailableBookException {
        if (!isBookCopyAvailable(bookCopyId)) {
            throw new UnavailableBookException("Can't delete book copy when it's not available.");
        }
        bookCopyRepository.deleteById(bookCopyId);
    }
}

class UnavailableBookException extends Exception {
    public UnavailableBookException(String m) {
        super(m);   //message
    }
}