package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

class LoginControllerTest {

    private final LoginController loginController = new LoginController();

    @Test
    void login_returnsLoginView() {
        ModelAndView modelAndView = loginController.login();

        assertThat(modelAndView.getViewName()).isEqualTo("login");
    }

    @Test
    void error_returns403ViewWithMessage() {
        ModelAndView modelAndView = loginController.error();

        assertThat(modelAndView.getViewName()).isEqualTo("403");
        assertThat(modelAndView.getModel().get("errorMsg")).isEqualTo("You are not authorized for the requested data.");
    }
}
