package net.rusf.czujmao.guestBook.DAO;

import net.rusf.czujmao.guestBook.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Messages {
    static {

    }
    public static Boolean addMessage(Message msg) throws SQLException {
        Boolean rez = Boolean.FALSE;
        Statement statement = DBconnector.connection.createStatement();
        if (0 != statement.executeUpdate("INSERT INTO messages (threadid, datatime, userid, title, text) VALUES (" +
                msg.threadid + ", " + msg.datetime.toString() + ", " +
                msg.userid.toString() + ", '" + msg.title + "', '" + msg.text + "')")) {
            rez = Boolean.TRUE;
        }
        statement.close();
        return rez;
    }
    public static Boolean setMessage(Message msg) throws SQLException {
        Boolean rez = Boolean.FALSE;
        Statement statement = DBconnector.connection.createStatement();
        if (0 != statement.executeUpdate("UPDATE messages SET text = '" +
                msg.text + "' WHERE id = " + msg.id.toString())) {
            rez = Boolean.TRUE;
        }
        statement.close();
        return rez;
    }
    public static Boolean delMessage(Message msg) throws SQLException {
        Boolean rez = Boolean.FALSE;
        Statement statement = DBconnector.connection.createStatement();
        if (0 != statement.executeUpdate("DELETE FROM messages WHERE id = " + msg.id.toString())) {
            rez = Boolean.TRUE;
        }
        statement.close();
        return rez;
    }
    public static Message getMessage(Integer id) throws SQLException {
        Message msg = null;
        PreparedStatement statement = DBconnector.connection.prepareStatement("SELECT * FROM messages WHERE id = ? " +
                "ORDER BY datetime DESC");
        statement.setString(1, id.toString());
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            msg = new Message(result.getInt("id"), result.getInt("threadid"), result.getDate("datetime"),
                    result.getInt("userid"), result.getString("title"), result.getString("text"));
        }
        result.close();
        statement.close();
        return msg;
    }
    public static ArrayList<Message> getMessages() throws SQLException {
        ArrayList<Message> msg = new ArrayList<Message>();
        PreparedStatement statement = DBconnector.connection.prepareStatement("SELECT * FROM messages WHERE id = threadid " +
                "ORDER BY datetime DESC");
        ResultSet result = statement.executeQuery();
        int count = 0;
        while (result.next()) {
            msg.add(count++, new Message(result.getInt("id"), result.getInt("threadid"), result.getDate("datetime"),
                    result.getInt("userid"), result.getString("title"), result.getString("text")));
        }
        result.close();
        statement.close();
        return msg;
    }
    public static ArrayList<Message> getMessagesUser(Integer userid) throws SQLException {
        ArrayList<Message> msg = new ArrayList<Message>();
        PreparedStatement statement = DBconnector.connection.prepareStatement("SELECT * FROM messages WHERE userid = ? " +
                "ORDER BY datetime DESC");
        statement.setString(1, userid.toString());
        ResultSet result = statement.executeQuery();
        int count = 0;
        while (result.next()) {
            msg.add(count++, new Message(result.getInt("id"), result.getInt("threadid"), result.getDate("datetime"),
                    result.getInt("userid"), result.getString("title"), result.getString("text")));
        }
        result.close();
        statement.close();
        return msg;
    }
    public static ArrayList<Message> getMessagesDate(Date beginDate, Date endDate) throws SQLException {
        ArrayList<Message> msg = new ArrayList<Message>();
        PreparedStatement statement = DBconnector.connection.prepareStatement("SELECT * FROM messages WHERE datetime >= ? " +
                "AND datetime <= ? ORDER BY datetime DESC");
        statement.setString(1, beginDate.toString());
        statement.setString(2, endDate.toString());
        ResultSet result = statement.executeQuery();
        int count = 0;
        while (result.next()) {
            msg.add(count++, new Message(result.getInt("id"), result.getInt("threadid"), result.getDate("datetime"),
                    result.getInt("userid"), result.getString("title"), result.getString("text")));
        }
        result.close();
        statement.close();
        return msg;
    }
    public static ArrayList<Message> getMessagesThread(Integer threadid) throws SQLException {
        ArrayList<Message> msg = new ArrayList<Message>();
        PreparedStatement statement = DBconnector.connection.prepareStatement("SELECT * FROM messages WHERE threadid = ? " +
                "ORDER BY datetime DESC");
        statement.setString(1, threadid.toString());
        ResultSet result = statement.executeQuery();
        int count = 0;
        while (result.next()) {
            msg.add(count++, new Message(result.getInt("id"), result.getInt("threadid"), result.getDate("datetime"),
                    result.getInt("userid"), result.getString("title"), result.getString("text")));
        }
        result.close();
        statement.close();
        return msg;
    }
}
