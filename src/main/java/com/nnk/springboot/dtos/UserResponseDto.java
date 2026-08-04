package com.nnk.springboot.dtos;

public record UserResponseDto(
        Long id,
        String username,
        String fullname,
        String role
) {}
