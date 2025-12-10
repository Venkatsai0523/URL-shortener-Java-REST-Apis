package com.example.url.java_url_shorter.domain.models;

import java.io.Serializable;
import java.time.Instant;

public record ShortUrlDto(
        Long id,
        String shortKey,
        String originalUrl,
        Boolean isPrivate,
        Instant expiresAt,
        Long clickCount,
        Instant createdAt,
        UserResponseDto createdBy
) implements Serializable {

    // Custom constructor (optional)
    public ShortUrlDto(
            Long id,
            String shortKey,
            String originalUrl,
            Boolean isPrivate,
            Instant expiresAt,
            UserResponseDto createdBy,
            Long clickCount,
            Instant createdAt
    ) {
        this(
            id,
            shortKey,
            originalUrl,
            isPrivate,
            expiresAt,
            clickCount,
            createdAt,
            createdBy               // <-- last parameter is the full DTO
        );
    }
}
