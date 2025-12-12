package com.example.url.java_url_shorter.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.url.java_url_shorter.domain.dtos.LoginRequestDto;
import com.example.url.java_url_shorter.domain.dtos.LoginResponseDto;
import com.example.url.java_url_shorter.domain.dtos.RegisterRequestDto;
import com.example.url.java_url_shorter.domain.dtos.RegisterResponseDto;
import com.example.url.java_url_shorter.domain.entity.User;
import com.example.url.java_url_shorter.domain.models.Role;
import com.example.url.java_url_shorter.domain.repository.Userrepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService {

    private final Userrepo userrepo;
    @Autowired
    private final PasswordEncoder encoder;

    public AuthService(Userrepo userrepo,PasswordEncoder encoder){
        this.userrepo=userrepo;
        this.encoder = encoder;
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto request) {

        var user = userrepo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return new LoginResponseDto(
                true,
                user.getRole().name(),
                user.getName(),
                user.getId()
        );
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto request) {

        if (userrepo.findByEmail(request.email()).isPresent()) {
            return new RegisterResponseDto(false, "Email already exists", null);
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setRole(Role.ROLE_USER);
        user.setCreatedAt(LocalDateTime.now());
        userrepo.save(user);

        return new RegisterResponseDto(true, "Registration successful",user.getId());
    }

}
