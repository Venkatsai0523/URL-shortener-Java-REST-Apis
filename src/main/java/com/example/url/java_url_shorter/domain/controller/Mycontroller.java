package com.example.url.java_url_shorter.domain.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.url.java_url_shorter.domain.dtos.CreateShortUrlRequest;
import com.example.url.java_url_shorter.domain.models.CreateShorturlcmd;
import com.example.url.java_url_shorter.domain.models.ShortUrlDto;
import com.example.url.java_url_shorter.domain.service.Myservice;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class Mycontroller {

    @Autowired
    private Myservice myservice;
    public Mycontroller(Myservice myservice){
        this.myservice = myservice;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShortUrlDto>> getAllList() {
        List<ShortUrlDto> list = myservice.getallpublicList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("api/create")
    public ResponseEntity<?> createShortUrl(@RequestBody @Valid CreateShortUrlRequest request) {
        try {
            var cmd = new CreateShorturlcmd(request.originalUrl());
            ShortUrlDto shortUrlDto = myservice.createShortUrl(cmd);

            return ResponseEntity.ok(shortUrlDto);

        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }
    }
}
