
package com.autodemia.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String redireccion(Authentication auth) {
        if (auth.getAuthorities().toString().contains("ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (auth.getAuthorities().toString().contains("PROFESOR")) {
            return "redirect:/profesor/dashboard";
        } else {
            return "redirect:/";
        }
    }
}