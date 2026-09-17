package com.example.LibraryManagement.services;

import com.example.LibraryManagement.dtos.bookDtos.BookRequestDto;
import com.example.LibraryManagement.dtos.bookDtos.BookResponseDto;
import com.example.LibraryManagement.models.Book;
import com.example.LibraryManagement.models.User;
import com.example.LibraryManagement.repositories.BookRepository;
import com.example.LibraryManagement.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    public BookService(UserRepository userRepository,
                       BookRepository bookRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public User getLoggedUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public BookResponseDto convertToDto(Book book){
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.isAvaiable()
        );
    }

    public List<BookResponseDto> list(){
        return bookRepository.findByUsers(getLoggedUser())
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public BookResponseDto create(BookRequestDto dto){
        User user = getLoggedUser();
        Book book = new Book(
                dto.getTitle(),
                dto.getGenre()
        );
        bookRepository.save(book);
        user.getBooks().add(book);
        userRepository.save(user);
        return convertToDto(book);
    }

    public BookResponseDto findById(Long id){
        User user = getLoggedUser();
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        if (!user.getBooks().contains(book)){
            throw new IllegalArgumentException("Acess denied");
        }
        return convertToDto(book);
    }

    public String remove(Long id){
        User user = getLoggedUser();
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if (!user.getBooks().contains(book)){
            throw new IllegalArgumentException("Acess denied");
        }
        bookRepository.delete(book);
        return "The book was removed successfully!";
    }

    public List<BookResponseDto> findByGenre(Book.Genre genre){
        User user = getLoggedUser();
        return bookRepository.findByUsersAndGenre(user, genre)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}
