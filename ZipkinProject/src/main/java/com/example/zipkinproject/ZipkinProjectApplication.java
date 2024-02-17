package com.example.zipkinproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinProjectApplication.class, args);
    }

}
