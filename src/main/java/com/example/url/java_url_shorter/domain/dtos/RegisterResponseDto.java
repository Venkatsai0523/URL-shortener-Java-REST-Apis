package com.example.url.java_url_shorter.domain.dtos;

public record RegisterResponseDto(
        boolean success,
        String message,
        Long userId
) {}
