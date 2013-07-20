package net.rusf.czujmao;

import java.lang.reflect.Field;

public class Test {
    @MyField
    public int x;
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Object t = null;
        try {
            t = Class.forName("Test").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Field f = t.getClass().getField("x");
    }

}
