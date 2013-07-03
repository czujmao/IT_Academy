package net.rusf.czujmao.calc;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * It's stack calculator
 */

public class Calc {
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
        Calculator calc = new Calculator();
        while (true) {
            calc.init(reader.next());
            byte res = calc.exec();
            switch (res) {
                case 0: if (!fromFile) System.out.println("Ok"); break;
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
        }
    }
}