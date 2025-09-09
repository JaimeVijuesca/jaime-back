package com.example.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

        // Know ram usage
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used memory in bytes: " + memory);
        System.out.println("Used memory in megabytes: " + memory / 1024 / 1024);
        System.out.println("Used memory in gigabytes: " + memory / 1024 / 1024 / 1024);
    }


}