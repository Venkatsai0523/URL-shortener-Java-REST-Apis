package com.example.url.java_url_shorter.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.url.java_url_shorter.domain.entity.User;

public interface Userrepo extends JpaRepository<User,Long> {
}
