package com.example.auth.controller;

import com.example.auth.service.DbHealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final DbHealthService databaseHealthService;

    public HealthController(DbHealthService databaseHealthService) {
        this.databaseHealthService = databaseHealthService;
    }

    @GetMapping("/dbhealth")
    public String checkHealth() {
        boolean isHealthy = databaseHealthService.isDatabaseHealthy();
        if (isHealthy) {
            return "✅ - PostgreSQL Database is UP and running!!!";
        } else {
            return "❌ PostgreSQL Database is DOWN!";
        }
    }
}
