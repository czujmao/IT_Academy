package net.rusf.czujmao.calc3.engine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Calc {
    protected static final Stack head = new Stack();
    protected static final Map<String, Double> defined = new HashMap<>();
    protected static final Map<String, String> function = new HashMap<>();
    protected static String[] commands;
    private static Calc i = null;
///////////////////////////////////////////////////////////
/*
* "Constructor"
*/
    public static Calc create() throws IOException {
        if (null == i) {
            i = new Calc();
        }
        return i;
    }
/*
* Fill array "commands"
*/
    public void init (String str) {
        for (String s: defined.keySet()) {
            str = str.replaceAll(s, defined.get(s).toString());
        }
        commands = str.replaceAll("\\s+", "\u0020").replaceAll("--", "").split("\u0020");
    }
/*
* Main function
*/
    public Results exec () throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        byte res;
        Object e = null;
        String s = function.get(commands[0].toUpperCase());
        if (null != s) {
            e = Class.forName(s).newInstance();
        }
        if (null == e) {
            System.err.println("Unknown command.");
            res = 1;
        } else {
            res = ((Calc) e).exec();
        }
        return res;
    }
///////////////////////////////////////////////////////////
/*
* For one argument function
*/
boolean stackIsEmpty () {
    if (0 == head.count()) {
        System.err.println("Stack is empty.");
        return true;
    }
    return false;
}
    /*
    * For two arguments function
    */
    boolean stackHaveOneItem () {
        if (1 == head.count()) {
            System.err.println("Stack have one item only.");
            return true;
        }
        return false;
    }
///////////////////////////////////////////////////////////
/*
* Private calculator constructor for Singleton
*/
    protected Calc () throws IOException {
        Resource myreader = new Resource();
        String pakageName = getClass().getPackage().getName();
        String str = myreader.next();
        while (null != str) {
            String[] funcs = str.replaceAll("\\s+", "\u0020").split("\u0020");
            if (2 == funcs.length) {
                function.put(funcs[0], pakageName + "." + funcs[1]);
            }
            str = myreader.next();
        }
    }
}