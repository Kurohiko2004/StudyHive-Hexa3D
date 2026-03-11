package com.sep.classManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.sep")
@EnableJpaAuditing
public class ClassManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassManagementApplication.class, args);
    }

}
