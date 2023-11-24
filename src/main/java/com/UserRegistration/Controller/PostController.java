package com.UserRegistration.Controller;

import com.UserRegistration.Model.Post;
import com.UserRegistration.Model.User;
import com.UserRegistration.Service.PostService;
import com.UserRegistration.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<Post> post = postService.getByID(id);
        if(post.isEmpty()){
            return "postNotFound";
        }
        model.addAttribute("post",post);
        return "post";
    }

    @GetMapping("/post/new")
    public String showCreatePost(Model model){
        model.addAttribute("post",new Post());
        return "newPost";
    }

    @PostMapping("/post/new")
    public String createPost(@Valid @ModelAttribute Post post,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "newPost";
        }
        postService.save(post);
        return "redirect:/" + post.getId();
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id){
        Optional<Post> optionalPost = postService.getByID(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            postService.delete(post);
            return "redirect:/";
        }else{
            return "error";
        }
    }
}
