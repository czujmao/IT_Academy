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
        String[] commands = str.split("\u0020");
        for (String s: map.keySet()) {
            str = str.replaceAll(" " + s + " "," " + map.get(s) + " ");
        }
//  заменить все подстановки из хэша подстановок
        String command = commands[0];
        switch (command.toUpperCase()) {
            case "#": break;
            case "POP": {
                if (0 == head.count()) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else {
                    head.pop();
                }
            } break;
            case "PUSH": {
                if (1 == commands.length) {
                    System.out.println("PUSH mast have argument.");
                    res = 1;
                } else {
                    Double d = Double.parseDouble(commands[1]);
                    if (null == d) {
                        System.out.println("PUSH have error argument.");
                        res = 1;
                    } else {
                        head.push(d);
                    }
                }
            } break;
            case "DEFINE": {

            } break;
            case "PRINT": {
                if (0 == head.count()) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else {
                    System.out.println(head.get());
                }
            } break;
            case "QRT": {
                if (0 == head.count()) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else {
                    double d = head.pop();
                    head.push(d * d);
                }
            } break;
            case "SQRT": {
                if (0 == head.count()) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else {
                    double d = head.pop();
                    head.push(Math.sqrt(d));
                }
            } break;
            case "+": {
                long l = head.count();
                if (0 == l) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else if (1 == l) {
                    System.out.println("Stack have only one item.");
                    res = 2;
                } else {
                    double d = head.pop();
                    head.push(head.pop() + d);
                }
            } break;
            case "-": {
                long l = head.count();
                if (0 == l) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else if (1 == l) {
                    System.out.println("Stack have only one item.");
                    res = 2;
                } else {
                    double d = head.pop();
                    head.push(head.pop() - d);
                }
            } break;
            case "*": {
                long l = head.count();
                if (0 == l) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else if (1 == l) {
                    System.out.println("Stack have only one item.");
                    res = 2;
                } else {
                    double d = head.pop();
                    head.push(head.pop() * d);
                }
            } break;
            case "/": {
                long l = head.count();
                if (0 == l) {
                    System.out.println("Stack is empty.");
                    res = 2;
                } else if (1 == l) {
                    System.out.println("Stack have only one item.");
                    res = 2;
                } else {
                    if (0 == head.get()) {
                        System.out.println("Divide by zero.");
                        res = 2;
                    } else {
                        double d = head.pop();
                        head.push(head.pop() + d);
                    }
                }
            } break;
            case "EXIT": {
                res = -1;
            } break;
            default:
                System.out.println("Unknown command.");
                res = 1;
        }

        return res;
    }

}
