package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dtos.UserRequestDto;
import com.nnk.springboot.dtos.UserResponseDto;
import com.nnk.springboot.mappers.UserMapper;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findAllUsers_returnsMappedDtos() {
        User first = user(1L, "user-1", "encoded-1", "User One", "ADMIN");
        User second = user(2L, "user-2", "encoded-2", "User Two", "USER");
        UserResponseDto firstDto = userDto(1L, "user-1", "User One", "ADMIN");
        UserResponseDto secondDto = userDto(2L, "user-2", "User Two", "USER");

        when(userRepository.findAll()).thenReturn(List.of(first, second));
        when(userMapper.toResponseDto(first)).thenReturn(firstDto);
        when(userMapper.toResponseDto(second)).thenReturn(secondDto);

        assertThat(userService.findAllUsers()).containsExactly(firstDto, secondDto);
    }

    @Test
    void findByIdUser_returnsMappedDto() {
        User entity = user(3L, "user", "encoded", "User", "ADMIN");
        UserResponseDto dto = userDto(3L, "user", "User", "ADMIN");

        when(userRepository.findById(3L)).thenReturn(Optional.of(entity));
        when(userMapper.toResponseDto(entity)).thenReturn(dto);

        assertThat(userService.findByIdUser(3L)).isEqualTo(dto);
    }

    @Test
    void findByIdUser_throwsWhenMissing() {
        when(userRepository.findById(7L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findByIdUser(7L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User not found with id: 7");
    }

    @Test
    void createUser_encodesPasswordBeforeSaving() {
        UserRequestDto request = new UserRequestDto("user", "secret", "User Name", "ADMIN");
        User entity = user(null, "user", null, "User Name", "ADMIN");
        User saved = user(10L, "user", "encoded-secret", "User Name", "ADMIN");
        UserResponseDto dto = userDto(10L, "user", "User Name", "ADMIN");

        when(userMapper.toEntity(request)).thenReturn(entity);
        when(passwordEncoder.encode("secret")).thenReturn("encoded-secret");
        when(userRepository.save(entity)).thenReturn(saved);
        when(userMapper.toResponseDto(saved)).thenReturn(dto);

        assertThat(userService.createUser(request)).isEqualTo(dto);
        assertThat(entity.getPassword()).isEqualTo("encoded-secret");
    }

    @Test
    void updateUser_encodesPasswordWhenProvided() {
        User existing = user(5L, "user", "old-password", "Old Name", "USER");
        UserRequestDto request = new UserRequestDto("user", "new-secret", "New Name", "ADMIN");
        UserResponseDto dto = userDto(5L, "user", "New Name", "ADMIN");

        when(userRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(passwordEncoder.encode("new-secret")).thenReturn("encoded-secret");
        when(userRepository.save(existing)).thenReturn(existing);
        when(userMapper.toResponseDto(existing)).thenReturn(dto);

        assertThat(userService.updateUser(5L, request)).isEqualTo(dto);
        verify(userMapper).updateFromDto(request, existing);
        assertThat(existing.getPassword()).isEqualTo("encoded-secret");
    }

    @Test
    void updateUser_keepsPasswordWhenBlank() {
        User existing = user(6L, "user", "current-password", "Old Name", "USER");
        UserRequestDto request = new UserRequestDto("user", "   ", "New Name", "ADMIN");
        UserResponseDto dto = userDto(6L, "user", "New Name", "ADMIN");

        when(userRepository.findById(6L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(existing);
        when(userMapper.toResponseDto(existing)).thenReturn(dto);

        assertThat(userService.updateUser(6L, request)).isEqualTo(dto);
        verify(userMapper).updateFromDto(request, existing);
        verifyNoInteractions(passwordEncoder);
        assertThat(existing.getPassword()).isEqualTo("current-password");
    }

    @Test
    void updateUser_throwsWhenMissing() {
        when(userRepository.findById(8L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateUser(8L, new UserRequestDto("user", "x", "User", "ADMIN")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User not found with id: 8");
    }

    @Test
    void deleteUser_deletesEntity() {
        User existing = user(9L, "user", "encoded", "User", "ADMIN");
        when(userRepository.findById(9L)).thenReturn(Optional.of(existing));

        userService.deleteUser(9L);

        verify(userRepository).delete(existing);
    }

    @Test
    void deleteUser_throwsWhenMissing() {
        when(userRepository.findById(10L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteUser(10L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User not found with id: 10");
    }

    private static User user(Long id, String username, String password, String fullname, String role) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setRole(role);
        return user;
    }

    private static UserResponseDto userDto(Long id, String username, String fullname, String role) {
        return new UserResponseDto(id, username, fullname, role);
    }
}
