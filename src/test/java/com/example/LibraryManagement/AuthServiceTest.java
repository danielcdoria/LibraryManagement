package com.example.LibraryManagement;

import com.example.LibraryManagement.dtos.authDtos.AuthResponseDto;
import com.example.LibraryManagement.dtos.authDtos.LoginRequestDto;
import com.example.LibraryManagement.dtos.authDtos.RegisterRequestDto;
import com.example.LibraryManagement.models.User;
import com.example.LibraryManagement.repositories.UserRepository;
import com.example.LibraryManagement.security.JwtUtil;
import com.example.LibraryManagement.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService service;

    @Test
    public void register_whenUserIsNotBeingUsed_register(){
        RegisterRequestDto dto = new RegisterRequestDto("Daniel", "daniel@gmail.com", "senha", User.Role.USER);
        when(repository.findByEmail("daniel@gmail.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("senha")).thenReturn("senhaEncoded");
        when(jwtUtil.generateToken("danie@gmail.com")).thenReturn("token-fake");

        AuthResponseDto result = service.register(dto);
        assertThat(result.getToken()).isEqualTo("token-fake");
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    public void register_whenUserIsBeingUser_throwsException(){
        RegisterRequestDto dto = new RegisterRequestDto("Daniel", "daniel@gmail.com", "senha", User.Role.USER);
        when(repository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(new User()));

        assertThatThrownBy(() -> service.register(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email already beign used.");
    }

    @Test
    public void login_whenPasswordMatch_savesAndLogin(){
        LoginRequestDto dto = new LoginRequestDto("daniel@gmail.com", "senha");
        when(repository.findByEmail("daniel@gmail.com")).thenReturn(Optional.empty());
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(jwtUtil.extractEmail("daniel@gmail.com")).thenReturn("token-fake");

        AuthResponseDto result = service.login(dto);

        assertThat(result.getToken()).isEqualTo("token-fake");
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    public void login_whenPasswordDoesNotMatch_throwsException(){
        LoginRequestDto dto = new LoginRequestDto("daniel@gmail.com", "senha");
        when(repository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(new User()));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThatThrownBy(() -> service.login(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Acess denied");
    }

    @Test
}
