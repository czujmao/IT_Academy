package net.rusf.czujmao.calc3.engine;

import java.io.IOException;

/*
* POP command
*/
final class CalcPop extends Calc {
    byte exec() {
        if (stackIsEmpty()) return 2;
        head.pop();
        return 0;
    }
    protected CalcPop() throws IOException {
    }
}

/*
* PUSH command
*/
final class CalcPush extends Calc {
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

    protected CalcPush() throws IOException {
    }
}

/*
* DEFINE command
*/
final class CalcDefine extends Calc {
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
                defined.put(key, head.peek());
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

    protected CalcDefine() throws IOException {
    }
}

/*
* PRINT command
*/
final class CalcPrint extends Calc {
    byte exec() {
        if (stackIsEmpty()) return 2;
        System.out.println(head.peek());
        return 0;
    }

    protected CalcPrint() throws IOException {
    }
}

/*
* QRT command
*/
final class CalcQrt extends Calc {
    byte exec() {
        if (stackIsEmpty()) return 2;
        double d = head.pop();
        head.push(d * d);
        return 0;
    }

    protected CalcQrt() throws IOException {
    }
}

/*
* SQRT command
*/
final class CalcSqrt extends Calc {
    byte exec() {
        if (stackIsEmpty()) return 2;
        if (0 > head.peek()) {
            System.err.println("SQRT works with positive numbers only.");
            return 2;
        }
        double d = head.pop();
        head.push(Math.sqrt(d));
        return 0;
    }

    protected CalcSqrt() throws IOException {
    }
}

/*
* + command
*/
final class CalcPlus extends Calc {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        double d = head.pop();
        head.push(head.pop() + d);
        return 0;
    }

    protected CalcPlus() throws IOException {
    }
}

/*
* - command
*/
final class CalcMinus extends Calc {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        double d = head.pop();
        head.push(head.pop() - d);
        return 0;
    }

    protected CalcMinus() throws IOException {
    }
}

/*
* * command
*/
final class CalcMult extends Calc {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        double d = head.pop();
        head.push(head.pop() * d);
        return 0;
    }

    protected CalcMult() throws IOException {
    }
}

/*
* / command
*/
final class CalcDiv extends Calc {
    byte exec() {
        if (stackIsEmpty() || stackHaveOneItem()) return 2;
        if (0 == head.peek()) {
            System.err.println("Divide by zero.");
            return 2;
        } else {
            double d = head.pop();
            head.push(head.pop() / d);
        }
        return 0;
    }

    protected CalcDiv() throws IOException {
    }
}

/*
* ? command
*/
final class CalcHelp extends Calc {
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

    protected CalcHelp() throws IOException {
    }
}

/*
* # command
*/
final class CalcComment extends Calc {
    byte exec() {
        return 0;
    }

    protected CalcComment() throws IOException {
    }
}

/*
* # command
*/
final class CalcExit extends Calc {
    byte exec() {
        return -1;
    }

    protected CalcExit() throws IOException {
    }
}

