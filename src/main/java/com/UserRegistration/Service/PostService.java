package com.UserRegistration.Service;

import com.UserRegistration.Model.Post;
import com.UserRegistration.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService implements IPostService{
    private final PostRepository repository;
    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Post> getAllPost() {
        return repository.findAll();
    }

    @Override
    public Optional<Post> getByID(long id) {
        return repository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public void delete(Post post) {
        repository.delete(post);
    }
}
