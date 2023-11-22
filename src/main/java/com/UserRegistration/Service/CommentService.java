package com.UserRegistration.Service;

import com.UserRegistration.Model.Comment;
import com.UserRegistration.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService{
    private final CommentRepository repository;
    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        repository.delete(comment);
    }
}
