package net.rusf.czujmao.calc3.engine;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/*
* Stack calculator engine
*/

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
    public static Calc create() throws IOException, ClassNotFoundException {
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
        commands = str.replaceAll("--", "-").replaceAll("#", "# ").split("\\s+");
    }
/*
* Main function
*/
    public Results exec () throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String s = function.get(commands[0].toUpperCase());
        if (null != s) {
            Object e = Class.forName(s).newInstance();
            return ((Calc) e).exec();
        } else {
            System.err.println("Syntax Error: Unknown command.");
            return Results.ERROR;
        }
    }
///////////////////////////////////////////////////////////
/*
* For one argument function
*/
boolean stackIsEmpty () {
    if (0 == head.count()) {
        System.err.println("Runtime Error: Stack is empty.");
        return true;
    }
    return false;
}
    /*
    * For two arguments function
    */
    boolean stackHaveOneItem () {
        if (1 == head.count()) {
            System.err.println("Runtime Error: Stack have one item only.");
            return true;
        }
        return false;
    }
///////////////////////////////////////////////////////////
/*
* Private calculator constructor for Singleton
*/
    protected Calc () throws IOException {
        String pakageName = getClass().getPackage().getName();
        Annotation[] anno = null;
        Resource myreader = new Resource();
        String str = myreader.next();
        while (null != str) {
            try {
                anno = Class.forName(pakageName + "." + str).getAnnotations();
                function.put(((FuncAnno) anno[0]).func(), pakageName + "." + str);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            str = myreader.next();
        }
    }
}