package com.UserRegistration.Controller;

import com.UserRegistration.Model.Post;
import com.UserRegistration.Model.User;
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
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable("id") long id, Model model){
        Post post = postService.getByID(id);
        if(post == null){
            return "postNotFound";
        }
        model.addAttribute("post",post);
        return "post";
    }

    @GetMapping("/post/new")
    public String showCreatePost(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/";
        }
        model.addAttribute("post",new Post());
        return "postForm";
    }

    @PostMapping("/post/new")
    public String createPost(@Valid @ModelAttribute("post") Post post,Model model,BindingResult bindingResult,Principal principal){
        if (bindingResult.hasErrors()) {
            model.addAttribute("post",post);
            return "postForm";
        }
        User user = userService.findUserByUsername(principal.getName());

        post.setUser(user);
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id){
        Post optionalPost = postService.getByID(id);
        if(optionalPost != null){
            postService.delete(optionalPost);
            return "redirect:/";
        }else{
            return "error";
        }
    }
}
