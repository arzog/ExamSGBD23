package main.java.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private String url = "jdbc:mysql://localhost:3306/construcmgmt?autoReconnect=true&useSSL=false";
    private String usr = "brice";
    private String passwd = "brice";
    private static Connection connection;

    /**
     * 'volatile' rend la variable accessible par plusieurs threads
     */
    private volatile static ConnectionSingleton singleton;

    public ConnectionSingleton() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usr, passwd);
            System.out.println("Connection established");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (singleton == null) {
            synchronized (Connection.class) {
                if (singleton == null) {
                    singleton = new ConnectionSingleton();
                }
            }
        }
        return connection;
    }
}
