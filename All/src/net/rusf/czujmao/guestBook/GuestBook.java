package net.rusf.czujmao.guestBook;


import net.rusf.czujmao.guestBook.DAO.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GuestBook {
    public static void main(String[] args) {
        System.out.println("Welcome to my GuestBook!");
        System.out.println("Use '?' for help.");
        System.out.print(":");
        BufferedReader Inner = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            do {
                str = Inner.readLine();
                switch (str.toUpperCase()) {
                    case "LIST": {
                        ArrayList<Message> mylist = Messages.getMessages();
                        if (mylist.isEmpty()) System.out.println("Is empty");
                        System.out.print(":");
                        break;
                    }
                    case "ADDMSG": {
                        System.out.print("Enter message's title:");
                        str = Inner.readLine();
                        String title = str;
                        System.out.print("Enter message's text:");
                        str = Inner.readLine();
                        String text = str;
                        if (Messages.addMessage(new Message(0, 0, new Date(System.currentTimeMillis()), 0, title, text))) {
                            System.out.print("Message added");
                        };
                        break;
                    }
                    case "THREAD": break;
                    case "ADDUSER": break;
                }


            } while (!"EXIT".equals(str.toUpperCase()));
        } catch (IOException e) {
            System.err.println("Input error!");
            try {
                Inner.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(-1);
        } catch (SQLException sqlex) {

        }
    }
}
