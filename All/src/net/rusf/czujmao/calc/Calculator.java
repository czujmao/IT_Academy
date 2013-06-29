package net.rusf.czujmao.calc;

import java.util.HashMap;
import java.util.Map;

/**
 * It's main engine of calculator
 */

public class Calculator {
    static Stack head = new Stack();
    static Map<String, Double> map = new HashMap();
    static String[] commands;
    Calculator () {
    }
    Calculator (String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", "\u0020");
        for (String s: map.keySet()) {
            str = str.replaceAll(s, map.get(s).toString());
        }
        commands = str.split("\u0020");
    }
    byte exec () {
        byte res = 0;
        if (0 < commands.length) {
            Calculator e = new Calculator();
            switch (commands[0].toUpperCase()) {
                case "#": break;
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
                case "POP": {
                    e = new C_POP();
                } break;
                case "PUSH": {
                    e = new C_PUSH();
                } break;
                case "DEFINE": {
                    e = new C_DEFINE();
                } break;
                case "PRINT": {
                    e = new C_PRINT();
                } break;
                case "QRT": {
                    e = new C_QRT();
                } break;
                case "SQRT": {
                    e = new C_SQRT();
                } break;
                case "+": {
                    e = new C_PLUS();
                } break;
                case "-": {
                    e = new C_MINUS();
                } break;
                case "*": {
                    e = new C_MULT();
                } break;
                case "/": {
                    e = new C_DIV();
                } break;
                default:
                    System.err.println("Unknown command.");
                    res = 1;
            }
            if (!"Calculator".equals(e.getClass().getName())) {
                res = e.exec();
            }
        } else {
            System.err.println("Unknown command.");
            res = 1;
        }
        return res;
    }

}
 class C_POP extends Calculator {
     byte exec() {
         if (0 == head.count()) {
             System.err.println("Stack is empty.");
             return 2;
         } else {
             double d = head.pop();
             return 0;
         }
     }
 }
class C_PUSH extends Calculator {
    byte exec() {
        if (1 == commands.length) {
            System.err.println("PUSH mast have argument.");
            return 1;
        } else {
            try {
                Double d = Double.parseDouble(commands[1]);
                head.push(d);
                return 0;
            } catch (NumberFormatException ex) {
                System.err.println("PUSH have error argument.");
                return 2;
            }
        }
    }
}  class C_DEFINE extends Calculator {
    byte exec() {
        int i = commands.length;
        if (1 == i) {
            System.err.println("DEFINE mast have arguments.");
            return 1;
        } else if (2 == i) {
            if (0 == head.count()) {
                System.err.println("Stack is empty.");
                return 2;
            } else {
                String key = commands[1];
                map.put(key, head.get());
                return 0;
            }
        } else {
            String key = commands[1];
            try {
                Double d = Double.parseDouble(commands[2]);
                map.put(key, d);
                return 0;
            } catch (NumberFormatException ex) {
                System.err.println("DEFINE have error argument.");
                return 2;
            }
        }
    }
}  class C_PRINT extends Calculator {
    byte exec() {
        if (0 == head.count()) {
            System.err.println("Stack is empty.");
            return 2;
        } else {
            System.out.println(head.get());
            return 0;
        }
    }
}  class C_QRT extends Calculator {
    byte exec() {
        if (0 == head.count()) {
            System.err.println("Stack is empty.");
            return 2;
        } else {
            double d = head.pop();
            head.push(d * d);
            return 0;
        }
    }
}  class C_SQRT extends Calculator {
    byte exec() {
        if (0 == head.count()) {
            System.err.println("Stack is empty.");
            return 2;
        } else {
            double d = head.pop();
            head.push(Math.sqrt(d));
            return 0;
        }
    }
}  class C_PLUS extends Calculator {
    byte exec() {
        long l = head.count();
        if (0 == l) {
            System.err.println("Stack is empty.");
            return 2;
        } else if (1 == l) {
            System.err.println("Stack have only one item.");
            return 2;
        } else {
            double d = head.pop();
            head.push(head.pop() + d);
            return 0;
        }
    }
}  class C_MINUS extends Calculator {
    byte exec() {
        long l = head.count();
        if (0 == l) {
            System.err.println("Stack is empty.");
            return 2;
        } else if (1 == l) {
            System.err.println("Stack have only one item.");
            return 2;
        } else {
            double d = head.pop();
            head.push(head.pop() - d);
            return 0;
        }
    }
}  class C_MULT extends Calculator {
    byte exec() {
        long l = head.count();
        if (0 == l) {
            System.err.println("Stack is empty.");
            return 2;
        } else if (1 == l) {
            System.err.println("Stack have only one item.");
            return 2;
        } else {
            double d = head.pop();
            head.push(head.pop() * d);
            return 0;
        }
    }
}  class C_DIV extends Calculator {
    byte exec() {
        long l = head.count();
        if (0 == l) {
            System.err.println("Stack is empty.");
            return 2;
        } else if (1 == l) {
            System.err.println("Stack have only one item.");
            return 2;
        } else {
            if (0 == head.get()) {
                System.err.println("Divide by zero.");
                return 2;
            } else {
                double d = head.pop();
                head.push(head.pop() + d);
                return 0;
            }
        }
    }
}