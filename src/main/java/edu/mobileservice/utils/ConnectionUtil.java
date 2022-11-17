package edu.mobileservice.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtil {

    private static Connection connection = null;
    private static Logger logger = LogManager.getLogger("ConnectionUtil");

    public static Connection getConnection() {
        FileInputStream dbPropertyFile;
        Properties dbProperties = new Properties();
        if (connection == null) {
            try {
                dbPropertyFile = new FileInputStream("src/main/resources/db.properties");
                dbProperties.load(dbPropertyFile);
                connection = DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties.getProperty("login"),
                        dbProperties.getProperty("password"));
            } catch (SQLException | IOException e) {
                logger.error("Exception: " + e.getMessage());
            }
        }
        return connection;
    }
}
