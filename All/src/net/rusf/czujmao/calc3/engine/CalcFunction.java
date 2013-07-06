package net.rusf.czujmao.calc3.engine;

import java.io.IOException;

/*
* POP command
*/
final class CalcPop extends Calc {
    public Results exec()  {
        if (stackIsEmpty()) return Results.ERROR;
        head.pop();
        return Results.OK;
    }
    protected CalcPop() throws IOException {
    }
}

/*
* PUSH command
*/
final class CalcPush extends Calc {
    public Results exec()  {
        if (1 == commands.length) {
            System.err.println("Syntax Error: PUSH must have argument.");
            return Results.ERROR;
        } else {
            try {
                Double d = Double.parseDouble(commands[1]);
                head.push(d);
            } catch (NumberFormatException ex) {
                System.err.println("Syntax Error: PUSH have error argument.");
                return Results.ERROR;
            }
        }
        return Results.OK;
    }

    protected CalcPush() throws IOException {
    }
}

/*
* DEFINE command
*/
final class CalcDefine extends Calc {
    public Results exec()  {
        if (1 == commands.length) {
            System.err.println("Syntax Error: DEFINE must have arguments.");
            return Results.ERROR;
        } else {
            if (Character.isDigit(commands[1].charAt(0))) {
                System.err.println("Syntax Error: DEFINE have error argument.");
                return Results.ERROR;
            }
            String key = commands[1];
            if (2 == commands.length) {
                if (stackIsEmpty()) return Results.ERROR;
                defined.put(key, head.peek());
            } else {
                try {
                    Double d = Double.parseDouble(commands[2]);
                    defined.put(key, d);
                } catch (NumberFormatException ex) {
                    System.err.println("Syntax Error: DEFINE have error argument.");
                    return Results.ERROR;
                }
            }
        }
        return Results.OK;
    }

    protected CalcDefine() throws IOException {
    }
}

/*
* PRINT command
*/
final class CalcPrint extends Calc {
    public Results exec()  {
        if (stackIsEmpty()) return Results.ERROR;
        System.out.println(head.peek());
        return Results.OK;
    }

    protected CalcPrint() throws IOException {
    }
}

/*
* QRT command
*/
final class CalcQrt extends Calc {
    public Results exec()  {
        if (stackIsEmpty()) return Results.ERROR;
        double d = head.pop();
        head.push(d * d);
        return Results.OK;
    }

    protected CalcQrt() throws IOException {
    }
}

/*
* SQRT command
*/
final class CalcSqrt extends Calc {
    public Results exec()  {
        if (stackIsEmpty()) return Results.ERROR;
        if (0 > head.peek()) {
            System.err.println("Runtime Error: SQRT works with positive numbers only.");
            return Results.ERROR;
        }
        double d = head.pop();
        head.push(Math.sqrt(d));
        return Results.OK;
    }

    protected CalcSqrt() throws IOException {
    }
}

/*
* + command
*/
final class CalcPlus extends Calc {
    public Results exec()  {
        if (stackIsEmpty() || stackHaveOneItem()) return Results.ERROR;
        double d = head.pop();
        head.push(head.pop() + d);
        return Results.OK;
    }

    protected CalcPlus() throws IOException {
    }
}

/*
* - command
*/
final class CalcMinus extends Calc {
    public Results exec()  {
        if (stackIsEmpty() || stackHaveOneItem()) return Results.ERROR;
        double d = head.pop();
        head.push(head.pop() - d);
        return Results.OK;
    }

    protected CalcMinus() throws IOException {
    }
}

/*
* * command
*/
final class CalcMult extends Calc {
    public Results exec()  {
        if (stackIsEmpty() || stackHaveOneItem()) return Results.ERROR;
        double d = head.pop();
        head.push(head.pop() * d);
        return Results.OK;
    }

    protected CalcMult() throws IOException {
    }
}

/*
* / command
*/
final class CalcDiv extends Calc {
    public Results exec()  {
        if (stackIsEmpty() || stackHaveOneItem()) return Results.ERROR;
        if (0 == head.peek()) {
            System.err.println("Runtime Error: Divide by zero.");
            return Results.ERROR;
        } else {
            double d = head.pop();
            head.push(head.pop() / d);
        }
        return Results.OK;
    }

    protected CalcDiv() throws IOException {
    }
}

/*
* ? command
*/
final class CalcHelp extends Calc {
    public Results exec()  {
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
        return Results.OK;
    }

    protected CalcHelp() throws IOException {
    }
}

/*
* # command
*/
final class CalcComment extends Calc {
    public Results exec()  {
        return Results.OK;
    }

    protected CalcComment() throws IOException {
    }
}

/*
* # command
*/
final class CalcExit extends Calc {
    public Results exec()  {
        return Results.EXIT;
    }

    protected CalcExit() throws IOException {
    }
}

