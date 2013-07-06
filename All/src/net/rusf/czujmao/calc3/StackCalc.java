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
        Results result = Results.OK;
        do {
            calc.init(reader.next());
            try {
                result = calc.exec();
                switch (result) {
                    case OK: if (!fromFile) System.out.println("Ok"); break;
                    case ERROR: if (fromFile) {
                                    System.err.println("Error in line " + reader.getCount());
                                    result = Results.EXIT;
                                } else {
                                    break;
                                }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                ex.printStackTrace();
                result = Results.EXIT;;
            }
        } while (Results.EXIT != result);
        try {
            reader.closeReader();
        } catch (IOException e) {
            System.err.println("Close error!");
        }
    }
}
