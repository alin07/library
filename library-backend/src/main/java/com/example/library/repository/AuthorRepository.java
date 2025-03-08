package com.example.library.repository;

import com.example.library.model.Author;

import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
    List<Author> findByBooksIsbn(String isbn);
    // List<Author> findAllById(List<Long> authorIds);

    List<Author> findAllByAuthorId(List<Long> authorIds);
}
