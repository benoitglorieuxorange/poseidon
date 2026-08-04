package com.nnk.springboot.services;

import com.nnk.springboot.dtos.UserRequestDto;
import com.nnk.springboot.dtos.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAllUsers();
    UserResponseDto findByIdUser(Long id);
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    void deleteUser(Long id);
}
