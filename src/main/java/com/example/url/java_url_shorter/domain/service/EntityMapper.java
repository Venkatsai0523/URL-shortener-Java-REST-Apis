package com.example.url.java_url_shorter.domain.service;

import org.springframework.stereotype.Component;

import com.example.url.java_url_shorter.domain.entity.ShortUrl;
import com.example.url.java_url_shorter.domain.entity.User;
import com.example.url.java_url_shorter.domain.models.ShortUrlDto;
import com.example.url.java_url_shorter.domain.models.UserResponseDto;

@Component
public class EntityMapper {

    public ShortUrlDto toshortDto(ShortUrl shortUrl){
       
    UserResponseDto userDto;

    // default guest user
    if (shortUrl.getCreatedBy() == null) {
        // default guest user
        userDto = new UserResponseDto(
            0L,
            "guest",
            "Guest"
        );
    } else {
        userDto = new UserResponseDto(
            shortUrl.getCreatedBy().getEmail(),
            shortUrl.getCreatedBy().getId(),
            shortUrl.getCreatedBy().getName()
        );
    }

         return new ShortUrlDto(
            shortUrl.getId(),
            shortUrl.getShortKey(),
            shortUrl.getOriginalUrl(),
            shortUrl.getIsPrivate(),
            shortUrl.getExpiresAt(),
            userDto,
            shortUrl.getClickCount(),
            shortUrl.getCreatedAt()
         );
    }

    public UserResponseDto toUserDto(User user) {
        return new UserResponseDto(user.getId(),user.getEmail(),user.getName());
    }

}
