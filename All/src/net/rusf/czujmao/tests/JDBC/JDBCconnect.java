package net.rusf.czujmao.tests.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCconnect {
    public static void main (String[] arg) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {

        }
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jbdc:mysql//localhost:9000", "user", "password");
            ps = connection.prepareStatement("INSERT INTO `table`(mytable) value(?)");
            ps.setString(1, "aaa");
            ps.execute();
        } catch (SQLException ex) {

        } finally {
            if (null != ps) ps.close();
            if (null != connection) connection.close();
        }
    }
}
