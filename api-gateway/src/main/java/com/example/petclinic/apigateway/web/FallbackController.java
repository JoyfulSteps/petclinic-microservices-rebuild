package com.example.petclinic.apigateway.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/visits")
    public Mono<Map<String, Object>> visitsFallback() {
        Map<String, Object> fallbackResponse = Map.of("items", Collections.emptyList());
        return Mono.just(fallbackResponse);
    }
}
