package net.rusf.czujmao.calc2.calc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * It's main engine of calculator
 */

public class Calculator {
    protected static final Stack head = new Stack();
    protected static final Map<String, Double> defined = new HashMap<String, Double>();
    protected static final Map<String, String> function = new HashMap<String, String>();
    protected static String[] commands;
    private static Calculator i = null;
/*
* Private calculator constructor for Singleton
*/
    protected Calculator () {
    }
/*
* "Constructor"
*/
    static Calculator create() throws IOException {
        if (i == null) {
            i = new Calculator();
        }
        Resource myreader = new Resource();
        String str = myreader.next();
        while (str != null) {
            str = str.replaceAll("\\s+", "\u0020");

        }
        return i;
    }
/*
* Fill array "commands"
*/
    void init (String str) {
        str = str.replaceAll("\\s+", "\u0020");
        for (String s: defined.keySet()) {
            str = str.replaceAll(s, defined.get(s).toString());
        }
        str = str.replaceAll("--", "");
        commands = str.split("\u0020");
    }
/*
* For one argument function
*/
    protected boolean stackIsEmpty () {
        if (0 == head.count()) {
            System.err.println("Stack is empty.");
            return true;
        }
        return false;
    }
/*
* For two arguments function
*/
    protected boolean stackHaveOneItem () {
        if (1 == head.count()) {
            System.err.println("Stack have one item only.");
            return true;
        }
        return false;
    }
/*
* Main function
*/
    byte exec () throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        byte res = 0;
        if (0 < commands.length) {
            Object e = null;
            for (String s: function.keySet()) {
                if (s.equals(commands[0].toUpperCase())) {
                    e = Class.forName(function.get(s)).newInstance();
                }
            }
            if (null == e) {
                System.err.println("Unknown command.");
                res = 1;
            } else {
                res = ((Calculator) e).exec();
            }
        } else {
            System.err.println("Unknown command.");
            res = 1;
        }
        return res;
    }
}

/*
* POP command
*/
class C_POP extends Calculator {
    byte exec() {
        if (stackIsEmpty()) return 2;
        head.pop();
        return 0;
    }
}

/*
* PUSH command
*/
class C_PUSH extends Calculator {
    byte exec() {
        if (1 == commands.length) {
            System.err.println("PUSH must have argument.");
            return 1;
        } else {
            try {
                Double d = Double.parseDouble(commands[1]);
                head.push(d);
            } catch (NumberFormatException ex) {
                System.err.println("PUSH have error argument.");
                return 1;
            }
        }
        return 0;
    }
}
/*
* DEFINE command
*/
class C_DEFINE extends Calculator {
    byte exec() {
        if (1 == commands.length) {
            System.err.println("DEFINE must have arguments.");
            return 1;
        } else {
            if (Character.isDigit(commands[1].charAt(0))) {
                System.err.println("DEFINE have error argument.");
                return 1;
            }
            String key = commands[1];
            if (2 == commands.length) {
                if (stackIsEmpty()) return 2;
                defined.put(key, head.get());
            } else {
                try {
                    Double d = Double.parseDouble(commands[2]);
                    defined.put(key, d);
                } catch (NumberFormatException ex) {
                    System.err.println("DEFINE have error argument.");
                    return 1;
                }
            }
        }
        return 0;
    }
}

/*
* PRINT command
*/
class C_PRINT extends Calculator {
    byte exec() {
        if (stackIsEmpty()) return 2;
        System.out.println(head.get());
        return 0;
    }
}

/*
* QRT command
*/
class C_QRT extends Calculator {
    byte exec() {
        if (stackIsEmpty()) return 2;
        double d = head.pop();
        head.push(d * d);
        return 0;
    }
}

/*
* SQRT command
*/
class C_SQRT extends Calculator {
    byte exec() {
        if (stackIsEmpty()) return 2;
        if (0 > head.get()) {
            System.err.println("SQRT works with positive numbers only.");
            return 2;
        }
        double d = head.pop();
        head.push(Math.sqrt(d));
        return 0;
    }
}

/*
* + command
*/
class C_PLUS extends Calculator {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        double d = head.pop();
        head.push(head.pop() + d);
        return 0;
    }
}

/*
* - command
*/
class C_MINUS extends Calculator {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        double d = head.pop();
        head.push(head.pop() - d);
        return 0;
    }
}

/*
* * command
*/
class C_MULT extends Calculator {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        double d = head.pop();
        head.push(head.pop() * d);
        return 0;
    }
}

/*
* / command
*/
class C_DIV extends Calculator {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        if (0 == head.get()) {
            System.err.println("Divide by zero.");
            return 2;
        } else {
            double d = head.pop();
            head.push(head.pop() / d);
        }
        return 0;
    }
}

/*
* ? command
*/
class C_HELP extends Calculator {
    byte exec() {
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
        return 0;
    }
}

/*
* # command
*/
class C_COMMENT extends Calculator {
    byte exec() {
        return 0;
    }
}
/*
* # command
*/
class C_EXIT extends Calculator {
    byte exec() {
        return -1;
    }
}

