package com.UserRegistration.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final DefaultOidcUser oidcUser;
    @Autowired
    public CustomUserDetails(DefaultOidcUser oidcUser) {
        this.oidcUser = oidcUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oidcUser.getAuthorities();
    }

    @Override
    public String getPassword() {
        return " ";
    }

    @Override
    public String getUsername() {
        return oidcUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public DefaultOidcUser getOidcUser() {
        return oidcUser;
    }
}
