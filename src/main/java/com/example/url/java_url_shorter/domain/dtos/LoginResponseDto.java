package com.example.url.java_url_shorter.domain.dtos;

public record LoginResponseDto(
        boolean success,
        String role,
        String name,
        Long userId
) {}
