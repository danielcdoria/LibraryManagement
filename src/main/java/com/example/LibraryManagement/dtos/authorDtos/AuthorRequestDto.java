package com.example.LibraryManagement.dtos.authorDtos;

import jakarta.validation.constraints.NotBlank;

public class AuthorRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Nacionatility cannot be blank")
    private String nacionality;

    public String getName() {
        return name;
    }

    public String getNacionality() {
        return nacionality;
    }
}
