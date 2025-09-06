package com.example.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DbHealthService {
    private static final Logger logger = LoggerFactory.getLogger(DbHealthService.class);

    final public DataSource dataSource;

    DbHealthService(DataSource dataSrc)
    {
        dataSource = dataSrc;
    }

    public boolean isDatabaseHealthy() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                logger.info("✅ PostgreSQL Database is healthy!");
                return true;
            }
        }
        catch (SQLException e) {
            logger.error("❌ PostgreSQL Database is down: {}", e.getMessage());
            return false;
        }
        logger.warn("⚠️ PostgreSQL Database connection issue");
        return false;
    }
}
