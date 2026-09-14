package com.example.LibraryManagement.dtos.bookDtos;

import com.example.LibraryManagement.models.Book;

public class BookResponseDto {
    private Long id;
    private String title;
    private Book.Genre genre;
    private boolean avaiable;

    public BookResponseDto(Long id,
                           String title,
                           Book.Genre genre,
                           boolean avaiable){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.avaiable = avaiable;
    }

    public Book.Genre getGenre() {
        return genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setGenre(Book.Genre genre) {
        this.genre = genre;
    }
}
