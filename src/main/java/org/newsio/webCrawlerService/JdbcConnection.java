package org.newsio.webCrawlerService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Optional;


public class JdbcConnection {
    private static final Logger LOGGER = Logger.getLogger(JdbcConnection.class.getName());
    private static Optional<Connection> connection = Optional.empty();
    JdbcConnection() {

    }
    public static Optional<Connection> databaseConnect() {
        if(!connection.isPresent()) {
            try {

                connection = Optional.ofNullable(DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/newsio",
                                "postgres", "Hritik9221"));
                System.out.println("connected");


            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                e.printStackTrace();
                System.exit(0);
            }

        }

        return connection;
    }

}
