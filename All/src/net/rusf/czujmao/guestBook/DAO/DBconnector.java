package net.rusf.czujmao.guestBook.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
    static Connection connection;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?user=user&password=password");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

    }
}
