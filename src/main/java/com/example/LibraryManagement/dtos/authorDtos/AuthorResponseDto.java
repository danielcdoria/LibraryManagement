package com.example.LibraryManagement.dtos.authorDtos;

public class AuthorResponseDto {
    private Long id;
    private String name;
    private String nacionality;

    public AuthorResponseDto(Long id,
                             String name,
                             String nacionality){
        this.id = id;
        this.name = name;
        this.nacionality = nacionality;
    }

    public String getNacionality() {
        return nacionality;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
