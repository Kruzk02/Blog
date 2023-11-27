package com.UserRegistration.Service;

import com.UserRegistration.Model.Post;

import java.util.Collection;
import java.util.Optional;

public interface IPostService {
    Collection<Post> getAllPost();
    Post getByID(long id);
    Post save(Post post);
    void delete(Post post);
}
