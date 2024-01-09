package com.UserRegistration.Controller;

import com.UserRegistration.Model.Comment;
import com.UserRegistration.Model.Post;
import com.UserRegistration.Model.User;
import com.UserRegistration.Service.CommentService;
import com.UserRegistration.Service.PostService;
import com.UserRegistration.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CommentController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    @Autowired
    public CommentController(UserService userService, PostService postService, CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/";
        }
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if (principal != null){
                UserDetails userDetails = (UserDetails) principal;
                String user = userDetails.getUsername();
                model.addAttribute("username", user);
            }
        }
        User user = this.userService.findUserByUsername(authentication.getName());
        Post post = this.postService.getByID(id);
        if(post != null && user != null){
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            model.addAttribute("comment",comment);
            System.err.println("GET comment/"+id);
            return "comment";
        }else {
            System.err.println("Could not find a post by id: " + id + " or user by logged in username: " + user);
            return "error";
        }
    }

    @PostMapping("/comment")
    public String comment(@Valid @ModelAttribute Comment comment, BindingResult bindingResult, Principal principal){
        System.err.println("POST comment: " + comment);
        System.err.println("Binding Result: " + bindingResult);
        System.err.println("Principal: " + principal);

        if (bindingResult.hasErrors()) {
            System.err.println("Errors found. Cannot save comment: " + comment.getUser() + comment.getPost());
            return "comment";
        }

        this.commentService.save(comment);
        return "redirect:/post/" + comment.getPost().getId();
    }
}
