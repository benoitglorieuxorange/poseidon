package com.nnk.springboot.mappers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dtos.UserRequestDto;
import com.nnk.springboot.dtos.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toResponseDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRole()
        );
    }

    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        updateFromDto(dto, user);
        return user;
    }

    public void updateFromDto(UserRequestDto dto, User user) {
        if (dto == null || user == null) {
            return;
        }
        user.setUsername(dto.username());
        user.setFullName(dto.fullname());
        user.setRole(dto.role());
    }

    public UserRequestDto toRequestDto(UserResponseDto responseDto) {
        if (responseDto == null) {
            return null;
        }
        return new UserRequestDto(
                responseDto.username(),
                null,
                responseDto.fullname(),
                responseDto.role()
        );
    }
}
