package com.example.LibraryManagement.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nacionality;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
