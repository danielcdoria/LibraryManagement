package com.example.LibraryManagement.dtos.bookDtos;

import com.example.LibraryManagement.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotNull(message = "Genre cannot be null")
    private Book.Genre genre;

    public Book.Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }
}
