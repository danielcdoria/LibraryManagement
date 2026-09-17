package com.example.LibraryManagement.services;

import com.example.LibraryManagement.dtos.authorDtos.AuthorRequestDto;
import com.example.LibraryManagement.dtos.authorDtos.AuthorResponseDto;
import com.example.LibraryManagement.models.Author;
import com.example.LibraryManagement.repositories.AuthorRepository;
import com.example.LibraryManagement.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    public AuthorService(AuthorRepository authorRepository,
                         BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public AuthorResponseDto convertToDto(Author author){
        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                author.getNacionality()
        );
    }

    public List<AuthorResponseDto> list(){
        return authorRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public AuthorResponseDto create(AuthorRequestDto dto){
        Author author = new Author(
                dto.getName(),
                dto.getNacionality()
        );
        authorRepository.save(author);
        return convertToDto(author);
    }

    public AuthorResponseDto findById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        return convertToDto(author);
    }
}
