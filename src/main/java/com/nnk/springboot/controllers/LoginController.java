package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "expired", required = false) String expired) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");

        if (error != null) {
            mav.addObject("errorMessage", "Identifiants invalides.");
        }
        if (expired != null) {
            mav.addObject("expiredMessage", "Votre session a expiré, veuillez vous reconnecter.");
        }

        return mav;
    }
    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "You are not authorized for the requested data.");
        mav.setViewName("403");
        return mav;
    }
}