package com.example.url.java_url_shorter.domain.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.url.java_url_shorter.ApplicationProperties;
import com.example.url.java_url_shorter.domain.entity.ShortUrl;
import com.example.url.java_url_shorter.domain.models.CreateShorturlcmd;
import com.example.url.java_url_shorter.domain.models.ShortUrlDto;
import com.example.url.java_url_shorter.domain.repository.Shortrepo;
import com.example.url.java_url_shorter.domain.utility.ExeValidator;

@Service
public class Myservice {

    private final Shortrepo shortrepo;
    private final EntityMapper entityMapper;
    private final ApplicationProperties properties;

    // Spring automatically injects dependencies here
    public Myservice(Shortrepo shortrepo,EntityMapper entityMapper,ApplicationProperties properties) {
        this.shortrepo = shortrepo;
        this.entityMapper = entityMapper;
        this.properties=properties;
    }

    public List<ShortUrlDto> getallpublicList() {
        return shortrepo.getAllPublicUrls().stream().map(entityMapper::toshortDto).toList();
    }

    public ShortUrlDto createShortUrl(CreateShorturlcmd cmd) {

        if (properties.ValidateOriginalUrl()) {
            boolean urlexiste = ExeValidator.isUrlExists(cmd.originalUrl());
            if(!urlexiste){
                throw new RuntimeErrorException(null, "invalid URL"+ cmd.originalUrl());
            }
        }
        var shortkey = GenUniqueKey();
        var shorturl = new ShortUrl();
        shorturl.setOriginalUrl(cmd.originalUrl());
        shorturl.setShortKey(shortkey);
        shorturl.setCreatedBy(null);
        shorturl.setIsPrivate(false);
        shorturl.setClickCount(0L);
        shorturl.setExpiresAt(Instant.now().plus(30, java.time.temporal.ChronoUnit.DAYS));
        shorturl.setCreatedAt(Instant.now());
        shortrepo.save(shorturl);
        return entityMapper.toshortDto(shorturl);
    }

    public String GenUniqueKey(){
        String shortkey;
        do{
            shortkey = GenRandomkey();
        }while(shortrepo.existsByShortKey(shortkey));
        return shortkey;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static final int SHORT_KEY_LEN = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String GenRandomkey(){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<SHORT_KEY_LEN;i++){
            str.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return str.toString();
    }


}
