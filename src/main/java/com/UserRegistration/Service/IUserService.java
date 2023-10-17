package com.UserRegistration.Service;

import com.UserRegistration.Model.User;

public interface IUserService {
    User saveUser(User user);
    User findUserByEmail(String email);
}
