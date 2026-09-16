package com.example.LibraryManagement.services;

import com.example.LibraryManagement.dtos.authDtos.AuthResponseDto;
import com.example.LibraryManagement.dtos.authDtos.LoginRequestDto;
import com.example.LibraryManagement.dtos.authDtos.RegisterRequestDto;
import com.example.LibraryManagement.models.User;
import com.example.LibraryManagement.repositories.UserRepository;
import com.example.LibraryManagement.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository repository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDto register(RegisterRequestDto dto){
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already beign used.");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(
                dto.getName(),
                dto.getEmail(),
                encodedPassword,
                dto.getRole()
        );
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto dto){
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Acess denied");
        }
        if (!repository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("User not found");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
}
