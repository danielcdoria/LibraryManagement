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

    public Author(){}

    public Author(String name,
                  String nacionality){
        this.name = name;
        this.nacionality = nacionality;
    }

    public Long getId() {
        return id;
    }

    public String getNacionality() {
        return nacionality;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }
}
