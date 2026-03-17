package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/generic";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

}
