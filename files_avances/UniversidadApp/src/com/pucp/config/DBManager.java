package com.pucp.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heide
 */
public class DBManager {
    private static String url;
    private static String user;
    private static String password;
    
    static {
        String pathFile = "com/pucp/config/config.properties";
        try {
            InputStream input = DBManager.class.getClassLoader().getResourceAsStream(pathFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String linea;        
            linea = reader.readLine();        
            url = linea.split("=")[1];
            linea = reader.readLine();        
            user = linea.split("=")[1];
            linea = reader.readLine();        
            password = linea.split("=")[1];  
            // cargar driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
   public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(url, user, password);
   } 
}
