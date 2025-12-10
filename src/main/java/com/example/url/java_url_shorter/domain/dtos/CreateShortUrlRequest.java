package com.example.url.java_url_shorter.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateShortUrlRequest(
    @NotBlank(message = "invalid url")
    String originalUrl) {

}
