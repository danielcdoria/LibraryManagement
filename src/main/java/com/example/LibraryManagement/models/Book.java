package com.example.LibraryManagement.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Genre genre;
    private boolean avaiable;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId")
    )
    private List<Author> authors;

    public enum Genre{
        FICTION, NON_FICTION, SCIENCE, HISTORY
    }

    public Book(){}

    public Book(String title,
                Genre genre){
        this.title = title;
        this.genre = genre;
        this.avaiable = true;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<User> getUsers() {
        return users;
    }
}
