package com.example.demo.controller;

import com.example.demo.service.DbHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    private DbHealthService databaseHealthService;

    @GetMapping("/health")
    public String checkHealth() {
        boolean isHealthy = databaseHealthService.isDatabaseHealthy();
        if (isHealthy) {
            return "✅ PostgreSQL Database is UP and running!";
        } else {
            return "❌ PostgreSQL Database is DOWN!";
        }
    }

}
