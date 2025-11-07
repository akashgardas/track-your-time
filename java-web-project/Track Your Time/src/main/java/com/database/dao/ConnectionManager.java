package com.database.dao;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    private static HikariDataSource dataSource;

    // Static block: This code runs ONCE when the class is first loaded.
    static {
        try {
            // 1. Manually load the driver (the fix from before)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Read the credentials from the environment
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPass = System.getenv("DB_PASSWORD");
            
            String jdbcUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?sslMode=REQUIRED";

            // 3. Configure Hikari (set a small pool size for the free DB)
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(dbUser);
            config.setPassword(dbPass);
            config.setMaximumPoolSize(3); // Set this to a small number, like 3 or 5
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            // 4. Create the single, shared data source
            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            // If this fails, the whole app is broken
            throw new RuntimeException("Could not initialize ConnectionManager", e);
        }
    }

    // Public method for any DAO to get the shared connection pool
    public static DataSource getDataSource() {
        return dataSource;
    }
}