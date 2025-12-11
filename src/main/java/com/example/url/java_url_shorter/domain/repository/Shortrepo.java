package com.example.url.java_url_shorter.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.url.java_url_shorter.domain.entity.ShortUrl;

public interface Shortrepo extends JpaRepository<ShortUrl,Long>{

    @Query("select su from ShortUrl su left join fetch su.createdBy where su.isPrivate = false order by su.createdAt desc")
    List<ShortUrl> getAllPublicUrls();

    boolean existsByShortKey(String shortKey);

    Optional<ShortUrl> findByshortKey(String shortKey);

}
