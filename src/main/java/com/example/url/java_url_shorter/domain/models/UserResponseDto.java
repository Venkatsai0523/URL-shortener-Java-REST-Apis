package com.example.url.java_url_shorter.domain.models;

import java.io.Serializable;

public record UserResponseDto(
        Long id,
        String email,
        String name
) implements Serializable {
    public UserResponseDto(String email2, Long id2, String name2) {
        this(id2, email2, name2);
    }}
