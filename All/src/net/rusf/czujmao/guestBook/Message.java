package net.rusf.czujmao.guestBook;

import java.util.Date;

public class Message {
    public Integer id;
    public Integer threadid;
    public Date datetime;
    public Integer userid;
    public String nickname;
    public String title;
    public String text;

    public Message (Integer id, Integer threadid, Date datetime, Integer userid, String nickname, String title, String text) {
        this.id = id;
        this.threadid = threadid;
        this.datetime = datetime;
        this.userid = userid;
        this.nickname = nickname;
        this.title = title;
        this.text = text;
    }
    public void printmsg() {
        System.out.println("ID = " + this.id.toString());
        System.out.println("Date and time = " + this.datetime.toString());
        System.out.println("Autor = " + this.nickname);
        System.out.println("Title = " + this.title);
        System.out.println(this.text);
    }
    public void printhead() {
        System.out.println("ID = " + this.id.toString() + "Date and time = " + this.datetime.toString() +
            "Autor = " + this.nickname + "Title = " + this.title);
    }
}
