package com.example.url.java_url_shorter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtity {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("encoder:"+passwordEncoder.encode("secret"));
        System.out.println("encoder:"+passwordEncoder.encode("admin"));
    }

}
