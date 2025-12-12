package com.example.url.java_url_shorter.domain.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.example.url.java_url_shorter.domain.dtos.*;
import com.example.url.java_url_shorter.domain.models.CreateShorturlcmd;
import com.example.url.java_url_shorter.domain.models.ShortUrlDto;
import com.example.url.java_url_shorter.domain.service.AuthService;
import com.example.url.java_url_shorter.domain.service.Myservice;

import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Mycontroller {

    private final Myservice myservice;
    private final AuthService authService;

    public Mycontroller(Myservice myservice, AuthService authService) {
        this.myservice = myservice;
        this.authService = authService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShortUrlDto>> getAllList() {
        return ResponseEntity.ok(myservice.getallpublicList());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createShortUrl(@RequestBody @Valid CreateShortUrlRequest request) {
        try {
            var cmd = new CreateShorturlcmd(request.originalUrl());
            ShortUrlDto dto = myservice.createShortUrl(cmd);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<?> redirectToOriginal(@PathVariable String shortKey) {
        var dtoOpt = myservice.getOriginalUrlByShortKey(shortKey);

        if (dtoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Short URL not found or expired"));
        }

        return ResponseEntity.ok(Map.of(
            "success", true,
            "url", dtoOpt.get().originalUrl()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            LoginResponseDto res = authService.login(request);
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", ex.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto request) {
        RegisterResponseDto res = authService.register(request);
        return ResponseEntity.ok(res);
    }
}
