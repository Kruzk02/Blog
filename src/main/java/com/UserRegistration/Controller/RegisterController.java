package com.UserRegistration.Controller;

import com.UserRegistration.Service.UserService;
import com.UserRegistration.User.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final UserService service;
    @Autowired public RegisterController(UserService service) {this.service = service;}

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("user", new User());
            return "register";
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerUsers(@Valid @ModelAttribute("user")User user,
                                BindingResult result,
                                RedirectAttributes attributes){
        User existingUser = service.findUserByEmail(user.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(!user.getPassword().equals(user.getRetypePassword())){
            result.rejectValue("retypePassword","error.retypePassword","Password do not match");
            return "/register";
        }

        if (result.hasErrors()) {
            return "/register";
        }
        service.saveUser(user);
        attributes.addFlashAttribute("success", "You have successfully registered! Redirecting...");
        return "redirect:/login";
    }

}
