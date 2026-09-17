package com.example.LibraryManagement.controllers;

import com.example.LibraryManagement.dtos.authorDtos.AuthorRequestDto;
import com.example.LibraryManagement.dtos.authorDtos.AuthorResponseDto;
import com.example.LibraryManagement.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService service;
    public AuthorController(AuthorService service){
        this.service = service;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponseDto>> list(){
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponseDto> create(@Valid @RequestBody AuthorRequestDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
