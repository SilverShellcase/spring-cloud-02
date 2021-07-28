package com.rust.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.rust.demo.util", "com.rust.demo"})
@SpringBootApplication
public class BeetlsqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeetlsqlApplication.class, args);
    }
}
