package net.rusf.czujmao.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class TextCalc {
    public static void main(String[] args) {
        String str = "";
        MyReader reader = null;
        if (0 < args.length) {
            try {
                str = args[0];
                reader = new MyReader(str);
            } catch (FileNotFoundException ex) {
                TextCalc.printFileError(str);
            }
        } else {
            MyReader tmpreader =  new MyReader();
            TextCalc.printFileError(str);
            try {
                str = tmpreader.next();
                reader = new MyReader(str);
            } catch (FileNotFoundException ex) {
                TextCalc.printFileError(str);
            } catch (IOException ex) {
                TextCalc.printFileError(str);
            } finally {
                try {
                    tmpreader.closeReader();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (null == reader) System.exit(0);
///////////////////////////////////////////////////////////
//        TreeMap<String word, Long count> =
        do {
            try {
                str = reader.next();
                if (null == str) break;

            } catch (IOException e) {
                TextCalc.printFileError(str);
                break;
            }
        } while (true);
///////////////////////////////////////////////////////////
        try {
            reader.closeReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void printFileError(String str) {
        System.out.println("File '" + str + "' read error!");
    }
}
