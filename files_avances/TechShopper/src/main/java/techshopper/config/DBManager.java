/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techshopper.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
    private static String url = "jdbc:mysql://localhost:3306/universidad";
    private static String user = "root";
    private static String password = "root";
    
    
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
