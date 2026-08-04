package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.UserRequestDto;
import com.nnk.springboot.dtos.UserResponseDto;
import com.nnk.springboot.mappers.UserMapper;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(@ModelAttribute("user") UserRequestDto user) {
        return "user/add";
    }

//    @PostMapping("/user/validate")
//    public String validate(@Valid @ModelAttribute("user") UserRequestDto user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "user/add";
//        }
//        userService.createUser(user);
//        return "redirect:/user/list";
//    }

    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") UserRequestDto user,
                           BindingResult result, Model model) {
        if (user.password() == null || user.password().isBlank()) {
            result.rejectValue("password", "error.user", "Password is mandatory");
        }
        if (result.hasErrors()) {
            return "user/add";
        }
        userService.createUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        UserResponseDto existing = userService.findByIdUser(id);
        model.addAttribute("user", userMapper.toRequestDto(existing));
        model.addAttribute("userId", id);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid @ModelAttribute("user") UserRequestDto user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userId", id);
            return "user/update";
        }
        userService.updateUser(id, user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
}
