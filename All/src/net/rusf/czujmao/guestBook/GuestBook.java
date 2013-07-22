package net.rusf.czujmao.guestBook;


import net.rusf.czujmao.guestBook.DAO.DBconnector;
import net.rusf.czujmao.guestBook.DAO.Messages;
import net.rusf.czujmao.guestBook.DAO.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GuestBook {
    public static void main(String[] args) {
        DBconnector.init();
        System.out.println("Welcome to my GuestBook!");
        System.out.println("Use '?' for help.");
        BufferedReader Inner = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Integer userid = null;
        try {
            do {
                System.out.print(":");
                str = Inner.readLine();
                if (null == str) continue;
                String[] cmd = str.toUpperCase().split("\\s+");
                switch (cmd[0]) {
                    case "LIST": {
                        ArrayList<Message> mylist = Messages.getMessages();
                        if (mylist.isEmpty()) {
                            System.out.println("Is empty");
                            break;
                        }
                        for (Message msg : mylist) msg.printhead();
                        break;
                    }
                    case "ADDMSG": {
                        if (null == userid) {
                            System.out.print("Login, pleas!");
                            break;
                        }
                        System.out.print("Enter message's title:");
                        str = Inner.readLine();
                        String title = str;
                        System.out.print("Enter message's text:");
                        str = Inner.readLine();
                        String text = str;
                        if (Messages.addMessage(new Message(0, 0, new Date(System.currentTimeMillis()), userid, "", title, text))) {
                            System.out.print("Message added");
                        }
                        break;
                    }
                    case "LOGIN": {
                        System.out.print("Enter nickname:");
                        str = Inner.readLine();
                        String nickname = str;
                        System.out.print("Enter password:");
                        str = Inner.readLine();
                        Integer hashPass = str.hashCode();
                        userid = Users.checkPass(nickname, hashPass);
                        if (null == userid) {
                            System.out.print("Login failed");
                        } else {
                            System.out.print("Welcome to GuestBook, " + nickname + "!");
                        }
                        break;
                    }
                    case "LOGOUT": {
                        System.out.print("Logging out");
                        userid = null;
                        break;
                    }
                    case "ADDUSER": {
                        if (null != userid) {
                            System.out.println("You must logout!");
                            break;
                        }
                        System.out.print("Enter nickname:");
                        str = Inner.readLine();
                        String nickname = str;
                        System.out.print("Enter password:");
                        str = Inner.readLine();
                        Integer hashPass1 = str.hashCode();
                        System.out.print("Enter password again:");
                        str = Inner.readLine();
                        Integer hashPass2 = str.hashCode();
                        if (hashPass1 != hashPass2) {
                            System.out.println("Password repeat error!");
                            break;
                        }
                        if (Users.addUser(nickname, hashPass1)) {
                            System.out.println("Ok, user added, now you may logging in.");
                        }
                        break;
                    }
                    case "GETMSG": {
                        Integer num = null;
                        try {
                            num = Integer.parseInt(cmd[1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Syntax Error: you must enter message's number");
                            break;
                        }
                        Message msg = Messages.getMessage(num);
                        if (null != msg) msg.printmsg();
                        else System.out.println("Message not found!");
                        break;
                    }
                    case "EDITMSG": break;
                    case "GETTHREAD": break;
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

        } finally {
            DBconnector.close();
        }
    }
}
