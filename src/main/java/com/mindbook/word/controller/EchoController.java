package com.mindbook.word.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/echo")
public class EchoController {

    @PostMapping
    public Mono<Map<String, Object>> echo() {
        Map<String, Object> body = new HashMap<>();
        body.put("hello", "world");
        return Mono.just(body);
    }

}
