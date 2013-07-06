package net.rusf.czujmao.calc3;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.rusf.czujmao.calc3.engine.Calc;
import net.rusf.czujmao.calc3.engine.Results;

/**
 * It's stack calculator
 */

public class StackCalc {
    public static void main(String[] args) throws IOException {
        MyReader reader = null;
        boolean fromFile = false;
        if (args.length > 0) {
            try {
                reader = new MyReader(args[0]);
                fromFile = true;
            } catch (FileNotFoundException ex) {
                System.exit(0);
            }
        } else {
            reader = new MyReader();
        }
        if (fromFile) {
            System.out.println("Execute the program from file " + args[0]);
        } else {
            System.out.println("Welcome to Sack Calculator!");
            System.out.println("Use '?' for help.");
        }
        Calc calc = Calc.create();
        while (true) {
            calc.init(reader.next());
            try {
                switch (calc.exec()) {
                    case Results.OK: if (!fromFile) System.out.println("Ok"); break;
                    case 1: System.err.println("Syntax error" + (fromFile?" in line " + reader.getCount():"")); break;
                    case 2: System.err.println("Runtime error" + (fromFile?" in line " + reader.getCount():"")); break;
                }
                if ((-1 == res) || (res > 0 && fromFile)) {
                    try {
                        reader.closeReader();
                    } catch (IOException e) {
                        System.err.println("Close error!");
                    }
                    System.exit(0);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        }
    }
}
