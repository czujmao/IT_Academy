package net.rusf.czujmao.calc;

import java.util.HashMap;
import java.util.Map;

/**
 * It's main engine of calculator
 */

public class Calculator {
    static Stack head = new Stack();
    static Map<String, Double> map = new HashMap();
    Calculator () {

    }
    byte exec (String str) {
        byte res = 0;
        str = str.trim();
        str = str.replaceAll("\\s+", "\u0020");
        for (String s: map.keySet()) {
            str = str.replaceAll(s, map.get(s).toString());
        }
        String[] commands = str.split("\u0020");
        if (0 < commands.length) {
            String command = commands[0];
            switch (command.toUpperCase()) {
                case "#": break;
                case "POP": {
                    if (0 == head.count()) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else {
                        double d = head.pop();
                    }
                } break;
                case "PUSH": {
                    if (1 == commands.length) {
                        System.err.println("PUSH mast have argument.");
                        res = 1;
                    } else {
                        try {
                            Double d = Double.parseDouble(commands[1]);
                            head.push(d);
                        } catch (NumberFormatException ex) {
                            System.err.println("PUSH have error argument.");
                            res = 2;
                        }
                    }
                } break;
                case "DEFINE": {
                    int i = commands.length;
                    if (1 == i) {
                        System.err.println("DEFINE mast have arguments.");
                        res = 1;
                    } else if (2 == i) {
                        if (0 == head.count()) {
                            System.err.println("Stack is empty.");
                            res = 2;
                        } else {
                            String key = commands[1];
                            map.put(key, head.get());
                        }
                    } else {
                        String key = commands[1];
                        try {
                            Double d = Double.parseDouble(commands[2]);
                            map.put(key, d);
                        } catch (NumberFormatException ex) {
                            System.err.println("DEFINE have error argument.");
                            res = 2;
                        }
                    }
                } break;
                case "PRINT": {
                    if (0 == head.count()) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else {
                        System.err.println(head.get());
                    }
                } break;
                case "QRT": {
                    if (0 == head.count()) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else {
                        double d = head.pop();
                        head.push(d * d);
                    }
                } break;
                case "SQRT": {
                    if (0 == head.count()) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else {
                        double d = head.pop();
                        head.push(Math.sqrt(d));
                    }
                } break;
                case "+": {
                    long l = head.count();
                    if (0 == l) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else if (1 == l) {
                        System.err.println("Stack have only one item.");
                        res = 2;
                    } else {
                        double d = head.pop();
                        head.push(head.pop() + d);
                    }
                } break;
                case "-": {
                    long l = head.count();
                    if (0 == l) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else if (1 == l) {
                        System.err.println("Stack have only one item.");
                        res = 2;
                    } else {
                        double d = head.pop();
                        head.push(head.pop() - d);
                    }
                } break;
                case "*": {
                    long l = head.count();
                    if (0 == l) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else if (1 == l) {
                        System.err.println("Stack have only one item.");
                        res = 2;
                    } else {
                        double d = head.pop();
                        head.push(head.pop() * d);
                    }
                } break;
                case "/": {
                    long l = head.count();
                    if (0 == l) {
                        System.err.println("Stack is empty.");
                        res = 2;
                    } else if (1 == l) {
                        System.err.println("Stack have only one item.");
                        res = 2;
                    } else {
                        if (0 == head.get()) {
                            System.err.println("Divide by zero.");
                            res = 2;
                        } else {
                            double d = head.pop();
                            head.push(head.pop() + d);
                        }
                    }
                } break;
                case "?": {
                    System.out.println("You can use the following command:");
                    System.out.println("EXIT");
                    System.out.println("POP");
                    System.out.println("PUSH");
                    System.out.println("DEFINE <string>");
                    System.out.println("DEFINE <string> <double>");
                    System.out.println("PRINT");
                    System.out.println("+");
                    System.out.println("-");
                    System.out.println("*");
                    System.out.println("/");
                } break;
                case "EXIT": {
                    res = -1;
                } break;
                default:
                    System.err.println("Unknown command.");
                    res = 1;
            }
        } else {
            System.err.println("Unknown command.");
            res = 1;
        }

        return res;
    }

}
