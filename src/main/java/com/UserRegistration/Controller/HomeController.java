package com.UserRegistration.Controller;

import com.UserRegistration.Service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String firstName = ((CustomUserDetails) authentication.getPrincipal()).getFullName();
            model.addAttribute("firstName", firstName);
        }
        return "index";
    }
}
