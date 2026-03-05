package com.sep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sep")
public class SepApplication {
    public static void main(String[] args) {
        SpringApplication.run(SepApplication.class, args);
    }
}