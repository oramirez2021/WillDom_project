package com.hulkStore_ms.DBCon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	// MySql library
    public String driver = "com.mysql.cj.jdbc.Driver";
    
    // DataBase name
    public String database = "HulkStore";
    
    // Host
    public String hostname = "localhost";
    
    // Puerto
    public String port = "3306";
    
    //DataBase string connection path 
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false";
    
    // User name
    public String username = "admin";
    
    // User pass
    public String password = "1";
    
    public Connection mySQLConnect() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
