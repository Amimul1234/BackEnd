package com.neocyber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NeoCyberApplication {

    public static void main( String[] args ) {
        SpringApplication.run(NeoCyberApplication.class, args);
    }

}
