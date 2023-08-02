package com.UserRegistration.Service;

import com.UserRegistration.Repository.UserRepository;
import com.UserRegistration.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password");
        }
        return new CustomUserDetails(user);
    }
}
