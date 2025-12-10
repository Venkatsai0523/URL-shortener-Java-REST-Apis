package com.example.url.java_url_shorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JavaUrlShorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaUrlShorterApplication.class, args);
	}

}
