package com.example.LibraryManagement.controllers;

import com.example.LibraryManagement.dtos.bookDtos.BookRequestDto;
import com.example.LibraryManagement.dtos.bookDtos.BookResponseDto;
import com.example.LibraryManagement.models.Book;
import com.example.LibraryManagement.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService service;
    public BookController(BookService service){
        this.service = service;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDto>> list(){
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody BookRequestDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id){
        return ResponseEntity.ok(service.remove(id));
    }

    @GetMapping("/books/genre")
    public ResponseEntity<List<BookResponseDto>> findByGenre(@RequestParam Book.Genre genre){
        return ResponseEntity.ok(service.findByGenre(genre));
    }
}
