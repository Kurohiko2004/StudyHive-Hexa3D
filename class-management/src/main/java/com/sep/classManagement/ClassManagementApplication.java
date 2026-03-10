package com.sep.classManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sep")
public class ClassManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassManagementApplication.class, args);
    }

}
