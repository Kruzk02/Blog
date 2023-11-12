package com.UserRegistration.Service;

import com.UserRegistration.Repository.RoleRepository;
import com.UserRegistration.Repository.UserRepository;
import com.UserRegistration.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/*
 * Service class responsible for handling user related operations.
 */
@Transactional
@Service
public class UserService implements IUserService{

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    /*
     * Constructor to initialize the UserService with a UserRepository .
     */
    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    /*
     * Saves a User after encoding their password.
     */
    @Override
    public User saveUser(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

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
