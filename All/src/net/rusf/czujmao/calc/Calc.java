package net.rusf.czujmao.calc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * It's stack calculator
 */

public class Calc {
    public static void main(String[] args) throws IOException {
        MyReader reader;
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
        Stack head = new Stack();
        while (true) {
            byte res = exec(reader.next());
            switch (res) {
                case 0: if (fromFile) System.out.println("Ok"); break;
                case 1: System.out.println("Syntax error" + (fromFile?" in line " + reader.getCount():"")); break;
                case 2: System.out.println("Runtime error" + (fromFile?" in line " + reader.getCount():"")); break;
            }
            if (res > 0 && fromFile) {
                try {
                    reader.closeReader();
                } catch (IOException e) {
                    System.err.println("Close error!");
                }
                System.exit(0);
            }
        }
    }
    private byte exec(String str) {
        return 0;
    }
}
