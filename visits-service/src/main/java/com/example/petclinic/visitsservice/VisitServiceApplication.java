package com.example.petclinic.visitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VisitServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisitServiceApplication.class, args);
    }
}
