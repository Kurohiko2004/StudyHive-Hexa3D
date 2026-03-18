package com.sep.learningContents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.sep.learningContents",
        "com.sep.commonModule"
})
public class LearningContentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningContentsApplication.class, args);
    }

}
