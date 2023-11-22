package com.UserRegistration.Service;

import com.UserRegistration.Model.Comment;

public interface ICommentService {
    Comment save(Comment comment);
    void delete(Comment comment);
}
