package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.UserRequestDto;
import com.nnk.springboot.dtos.UserResponseDto;
import com.nnk.springboot.mappers.UserMapper;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    void home_addsUsersAndReturnsListView() {
        Model model = new ExtendedModelMap();
        List<UserResponseDto> users = List.of(new UserResponseDto(1L, "user", "User Name", "ADMIN"));
        when(userService.findAllUsers()).thenReturn(users);

        assertThat(userController.home(model)).isEqualTo("user/list");
        assertThat(model.asMap().get("users")).isEqualTo(users);
    }

    @Test
    void addUser_returnsAddView() {
        assertThat(userController.addUser(new UserRequestDto("user", "secret12", "User Name", "ADMIN"))).isEqualTo("user/add");
    }

    @Test
    void validate_rejectsBlankPasswordAndReturnsAddView() {
        BindingResult result = mock(BindingResult.class);
        UserRequestDto request = new UserRequestDto("user", "   ", "User Name", "ADMIN");
        when(result.hasErrors()).thenReturn(true);

        assertThat(userController.validate(request, result, new ExtendedModelMap())).isEqualTo("user/add");
        verify(result).rejectValue("password", "error.user", "Password is mandatory");
        verifyNoMoreInteractions(userService);
    }

    @Test
    void validate_createsUserAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        UserRequestDto request = new UserRequestDto("user", "secret12", "User Name", "ADMIN");
        when(result.hasErrors()).thenReturn(false);

        assertThat(userController.validate(request, result, new ExtendedModelMap())).isEqualTo("redirect:/user/list");
        verify(userService).createUser(request);
    }

    @Test
    void validate_rejectsShortPasswordAndReturnsAddView() {
        BindingResult result = mock(BindingResult.class);
        UserRequestDto request = new UserRequestDto("user", "short", "User Name", "ADMIN");
        when(result.hasErrors()).thenReturn(true);

        assertThat(userController.validate(request, result, new ExtendedModelMap())).isEqualTo("user/add");
        verify(result).rejectValue("password", "error.user", "Password must be at least 8 characters long");
        verifyNoMoreInteractions(userService);
    }

    @Test
    void showUpdateForm_populatesModelAndReturnsUpdateView() {
        Model model = new ExtendedModelMap();
        UserResponseDto response = new UserResponseDto(2L, "user", "User Name", "ADMIN");
        UserRequestDto request = new UserRequestDto("user", null, "User Name", "ADMIN");
        when(userService.findByIdUser(2L)).thenReturn(response);
        when(userMapper.toRequestDto(response)).thenReturn(request);

        assertThat(userController.showUpdateForm(2L, model)).isEqualTo("user/update");
        assertThat(model.asMap().get("user")).isEqualTo(request);
        assertThat(model.asMap().get("userId")).isEqualTo(2L);
    }

    @Test
    void updateUser_returnsUpdateViewWhenValidationFails() {
        Model model = new ExtendedModelMap();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(userController.updateUser(3L, new UserRequestDto("user", null, "User Name", "ADMIN"), result, model))
                .isEqualTo("user/update");
        assertThat(model.asMap().get("userId")).isEqualTo(3L);
    }

    @Test
    void updateUser_updatesAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        UserRequestDto request = new UserRequestDto("user", "secret12", "User Name", "ADMIN");
        when(result.hasErrors()).thenReturn(false);

        assertThat(userController.updateUser(3L, request, result, new ExtendedModelMap())).isEqualTo("redirect:/user/list");
        verify(userService).updateUser(3L, request);
    }

    @Test
    void updateUser_rejectsShortPasswordAndReturnsUpdateView() {
        Model model = new ExtendedModelMap();
        BindingResult result = mock(BindingResult.class);
        UserRequestDto request = new UserRequestDto("user", "short", "User Name", "ADMIN");
        when(result.hasErrors()).thenReturn(true);

        assertThat(userController.updateUser(3L, request, result, model)).isEqualTo("user/update");
        verify(result).rejectValue("password", "error.user", "Password must be at least 8 characters long");
        assertThat(model.asMap().get("userId")).isEqualTo(3L);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void deleteUser_deletesAndRedirects() {
        assertThat(userController.deleteUser(4L, new ExtendedModelMap())).isEqualTo("redirect:/user/list");
        verify(userService).deleteUser(4L);
    }
}
