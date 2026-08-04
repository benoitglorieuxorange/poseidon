package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;

import static org.assertj.core.api.Assertions.assertThat;

class HomeControllerTest {

    private final HomeController homeController = new HomeController();

    @Test
    void home_returnsHomeView() {
        assertThat(homeController.home(new ExtendedModelMap())).isEqualTo("home");
    }

    @Test
    void adminHome_redirectsToBidList() {
        assertThat(homeController.adminHome(new ExtendedModelMap())).isEqualTo("redirect:/bidList/list");
    }
}
