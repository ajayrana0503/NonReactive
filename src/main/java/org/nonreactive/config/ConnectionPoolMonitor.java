package org.nonreactive.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConnectionPoolMonitor {

    @Autowired
    private HikariDataSource dataSource;

    @Scheduled(fixedDelay = 1000) // every 1 seconds
    public void logConnectionPoolMetrics() {
        int idleConnections = dataSource.getHikariPoolMXBean().getIdleConnections();
        int activeConnections = dataSource.getHikariPoolMXBean().getActiveConnections();
        int totalConnections = dataSource.getHikariPoolMXBean().getTotalConnections();
        int maxConnections = dataSource.getMaximumPoolSize();

        System.out.println("Idle Connections: " + idleConnections + ", Active Connections: " + activeConnections + ", Total Connections: " + totalConnections + ", Max Connections: " + maxConnections + "\n");
    }
}
