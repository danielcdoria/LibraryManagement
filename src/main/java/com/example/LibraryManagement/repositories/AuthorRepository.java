package com.example.LibraryManagement.repositories;

import com.example.LibraryManagement.models.Author;
import com.example.LibraryManagement.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByGenre(Book.Genre genre);
    List<Author> findByNacionality(String nacionality);
}
