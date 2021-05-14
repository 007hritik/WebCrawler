package org.newsio.webCrawlerService;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;

public class DatabaseConnection {
    private static Connection conn = null;
    public static final Logger databaseConnectionLogger = LoggerFactory.getLogger(DatabaseConnection.class);
    static {
        String url = "jdbc:postgresql://localhost:5432/newsio";
        String userName = "postgres";
        String password = "Hritik9221";

        try {
            conn = DriverManager.getConnection(url,userName,password);
            databaseConnectionLogger.info("Database connected => {} on port : 5432",conn.getClientInfo());
        }catch ( SQLException exception) {
            exception.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }
}
