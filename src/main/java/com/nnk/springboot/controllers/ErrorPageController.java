package com.nnk.springboot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.AccessDeniedException;

@Controller
public class ErrorPageController {

    @GetMapping("/403")
    public String accessDenied(HttpServletRequest request, Model model) {
        Object exception = request.getSession()
                .getAttribute(WebAttributes.ACCESS_DENIED_403);
        String errorMsg = exception instanceof AccessDeniedException
                ? ((AccessDeniedException) exception).getMessage()
                : "Vous n'avez pas les droits pour accéder à cette page";
        model.addAttribute("errorMsg", errorMsg);
        return "403";
    }
}