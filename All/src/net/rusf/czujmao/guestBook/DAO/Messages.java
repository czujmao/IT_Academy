package net.rusf.czujmao.guestBook.DAO;

import net.rusf.czujmao.guestBook.Message;
import java.util.Date;

public class Messages {
    static {

    }
    public static Boolean addMessage(Message msg) {
        return Boolean.TRUE;
    }
    public static Boolean setMessage(Message msg) {
        return Boolean.TRUE;
    }
    public static Boolean delMessage(Message msg) {
        return Boolean.TRUE;
    }
    public static Message getMessage(int id) {
        return new Message();
    }
    public static Message[] getMessages() {
        return new Message[]{};
    }
    public static Message[] getMessagesUser(int userID) {
        return new Message[]{};
    }
    public static Message[] getMessagesDate(Date beginDate, Date endDate) {
        return new Message[]{};
    }
    public static Message[] getMessagesThread(int threadID) {
        return new Message[]{};
    }
}
