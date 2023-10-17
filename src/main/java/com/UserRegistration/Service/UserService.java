package com.UserRegistration.Service;

import com.UserRegistration.Repository.UserRepository;
import com.UserRegistration.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Service class responsible for handling user related operations.
 */
@Transactional
@Service
public class UserService implements IUserService{

    private final UserRepository repository;
    /*
     * Constructor to initialize the UserService with a UserRepository .
     */
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /*
     * Saves a User after encoding their password.
     */
    @Override
    public User saveUser(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repository.save(user);
    }

    /*
     * Find a User by Email.
     */
    @Override
    public User findUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }

}
