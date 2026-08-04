package com.nnk.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank
        String username,
        String password,
        @NotBlank
        String fullname,
        @NotBlank
        String role
) {}
