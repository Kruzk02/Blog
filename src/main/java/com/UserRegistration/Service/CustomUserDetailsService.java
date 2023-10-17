package com.UserRegistration.Service;

import com.UserRegistration.Model.Privilege;
import com.UserRegistration.Model.Role;
import com.UserRegistration.Repository.RoleRepository;
import com.UserRegistration.Repository.UserRepository;
import com.UserRegistration.Model.User;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
 * Custom implementation of Spring Security's UserDetailsService.
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UserRepository UserRepository;
    @Autowired private RoleRepository roleRepository;
    /*
     * Load user details by email for authentication and authorization.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserRepository.findUserByEmail(username);
        if(user == null){
            return new org.springframework.security.core.userdetails.User(
                    " "," ",true,true,true,true,
                    getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER")))
            );
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),true,true,true,true
                ,getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String>  getPrivileges(Collection<Role> roles){
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for(Role role : roles){
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for(Privilege item : collection){
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
