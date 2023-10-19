package com.UserRegistration.Controller;

import com.UserRegistration.Service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof DefaultOidcUser) {
                DefaultOidcUser oidcUser = (DefaultOidcUser) principal;
                CustomUserDetails customUserDetails = new CustomUserDetails(oidcUser);
                String user = customUserDetails.getUsername();
                model.addAttribute("firstName", user);
            } else if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String user = userDetails.getUsername();
                model.addAttribute("firstName", user);
            }
        }
        return "index";
    }
}
