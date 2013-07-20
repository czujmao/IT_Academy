package net.rusf.czujmao.guestBook;

import java.util.Date;

public class Message {
    public Integer id;
    public Integer threadid;
    public Date datetime;
    public Integer userid;
    public String title;
    public String text;

    public Message (Integer id, Integer threadid, Date datetime, Integer userid, String title, String text) {
        this.id = id;
        this.threadid = threadid;
        this.datetime = datetime;
        this.userid = userid;
        this.title = title;
        this.text = text;
    }
}
