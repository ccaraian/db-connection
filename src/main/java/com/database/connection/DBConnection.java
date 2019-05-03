package com.database.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConnection {

    private String username;

    private String password;

    private String url;

    private String driver;

    public DBConnection(){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.url = prop.getProperty("db.url");
            this.username = prop.getProperty("db.user");
            this.password = prop.getProperty("db.password");
            this.driver = prop.getProperty("db.driver");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }
}
