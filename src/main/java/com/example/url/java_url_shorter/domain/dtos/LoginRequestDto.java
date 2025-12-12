package com.example.url.java_url_shorter.domain.dtos;

import java.io.Serializable;

public record LoginRequestDto(
        String email,
        String password
) implements Serializable{}
