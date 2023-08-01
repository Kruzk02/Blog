package com.UserRegistration.Service;

import com.UserRegistration.User.User;

public interface IUserService {
    User saveUser(User user);
    User findUserByEmail(String email);
}
