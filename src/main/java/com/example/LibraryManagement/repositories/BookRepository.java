package com.example.LibraryManagement.repositories;

import com.example.LibraryManagement.models.Book;
import com.example.LibraryManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUsersAndGenre(User user, Book.Genre genre);
    List<Book> findByUsers(User user);
}
