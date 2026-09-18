package com.example.LibraryManagement.dtos.authDtos;

import com.example.LibraryManagement.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Email(message = "Email must have @ and .com")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotNull(message = "Role cannot be null")
    private User.Role role;

    public RegisterRequestDto(String name,
                              String email,
                              String password,
                              User.Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User.Role getRole() {
        return role;
    }
}
