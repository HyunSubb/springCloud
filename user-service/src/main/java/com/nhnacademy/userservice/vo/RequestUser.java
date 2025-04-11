package com.nhnacademy.userservice.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than 2 characters")
    @Email
    private String email;

    @NotNull(message = "Nmae cannot be null")
    @Size(min = 2, message = "Nmae not be less than 2 characters")
    private String name;

    @NotNull(message = "pwd cannot be null")
    @Size(min = 8, message = "pwd not be less than 8 characters")
    private String pwd;
}
