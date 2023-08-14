package com.UserRegistration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodedPassword {

    public static void main(String[] args){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "HELLO,WORLD";
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println(encodedPassword);
    }
}
