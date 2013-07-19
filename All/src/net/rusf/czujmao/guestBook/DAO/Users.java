package net.rusf.czujmao.guestBook.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    public static String getName (int ID) throws SQLException {
        String nickname = null;
        PreparedStatement statement = DBconnector.connection.prepareStatement("SELECT nickname FROM users WHERE id = ?");
        statement.setString(1, ((Integer) ID).toString());
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            nickname = result.getString("name");
        }
        result.close();
        statement.close();
        return nickname;
    }
    public static int getUserID (String nickname)  throws SQLException {
        int userid = -1;
        PreparedStatement statement = DBconnector.connection.prepareStatement("SELECT id FROM users WHERE nickname = ?");
        statement.setString(1, nickname);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            userid = result.getInt("id");
        }
        result.close();
        statement.close();
        return userid;
    }
    public static Boolean addUser (String nickname, int hashPass) {
        return Boolean.TRUE;
    }
    public static Boolean setPass (int oldHashPass, int newHashPass) {
        return Boolean.TRUE;
    }
}
