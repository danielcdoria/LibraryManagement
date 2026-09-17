package com.example.LibraryManagement;

import com.example.LibraryManagement.dtos.bookDtos.BookResponseDto;
import com.example.LibraryManagement.models.Book;
import com.example.LibraryManagement.models.User;
import com.example.LibraryManagement.repositories.BookRepository;
import com.example.LibraryManagement.repositories.UserRepository;
import com.example.LibraryManagement.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookRepository  repository;

    @InjectMocks
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new UsernamePasswordAuthenticationToken("daniel@gmail.com", null, List.of())
                )
        );
    }

    @InjectMocks
    private BookService service;

    @Test
    public void findById_whenListContainsBook_find(){
        Book book = new Book("Livro", Book.Genre.FICTION);
        User user = new User();
        user.getBooks().add(book);
        when(userRepository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(user));
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        BookResponseDto result = service.findById(1L);
        assertThat(result.getTitle()).isEqualTo("Livro");
    }

    @Test
    public void findById_whenListDoesNotContainsBook_throwsException(){
        when(userRepository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(new User()));
        when(repository.findById(1L)).thenReturn(Optional.of(new Book()));

        assertThatThrownBy(() -> service.findById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Acess denied");
    }
}
