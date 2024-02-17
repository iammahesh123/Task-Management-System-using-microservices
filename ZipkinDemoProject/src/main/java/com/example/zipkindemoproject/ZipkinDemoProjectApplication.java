package com.example.zipkindemoproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
public class ZipkinDemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinDemoProjectApplication.class, args);
    }

}
