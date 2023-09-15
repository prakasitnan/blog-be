package com.github.prakasitnan.blogbe.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthCheck")
public class HealthCheckController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
