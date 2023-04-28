package main;

import java.sql.*;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBase {

    private static final String CONFIG_FILE_PATH = "config.txt";
    private static final String DB_URL_KEY = "DB_URL";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(getDBUrl() , getUser(), getPassword());
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
    }
    public static Map<String, String> readConfig() {
        Map<String, String> configMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split("=");
                if (keyValue.length == 2) {
                    configMap.put(keyValue[0], keyValue[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configMap;
    }
    public static String getDBUrl() {
        Map<String, String> configMap = readConfig();
        return configMap.get(DB_URL_KEY);
    }

    public static String getUser() {
        Map<String, String> configMap = readConfig();
        return configMap.get(USER_KEY);
    }

    public static String getPassword() {
        Map<String, String> configMap = readConfig();
        return configMap.get(PASSWORD_KEY);
    }

    public static  void closeConnection( Connection c) throws SQLException {
        if( c!= null){
            c.close();
        }
    }

}
