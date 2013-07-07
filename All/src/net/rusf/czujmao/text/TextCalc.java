package net.rusf.czujmao.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TextCalc {
    public static void main(String[] args) {
        String str = "";
        MyReader reader = null;
        MyWriter writer = null;
        if (0 < args.length) {
            try {
                str = args[0];
                reader = new MyReader(str);
                writer = new MyWriter(str + ".cvs");
            } catch (FileNotFoundException ex) {
                TextCalc.printFileError(str, OperationType.READ);
                System.exit(1);
            } catch (SecurityException ex) {
                TextCalc.printFileError(str, OperationType.WRITE);
                System.exit(1);
            }
        } else {
            MyReader tmpreader =  new MyReader();
            System.out.print("Enter file name: ");
            try {
                try {
                    str = tmpreader.nextLine();
                } catch (IOException ex) {
                    System.out.println("Input error");
                    System.exit(1);
                }
                reader = new MyReader(str);
                writer = new MyWriter(str + ".cvs");
            } catch (FileNotFoundException ex) {
                TextCalc.printFileError(str, OperationType.READ);
                System.exit(1);
            } catch (SecurityException ex) {
                TextCalc.printFileError(str, OperationType.WRITE);
                System.exit(1);
            } finally {
                try {
                    tmpreader.closeReader();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        HashMap<String, Integer> words = new HashMap<>();
        ValueComparator vc =  new ValueComparator(words);
        String word = "";
        do {
            try {
                int ch = reader.nextChar();
                if (-1 == ch) {
                    WordsCount.countByChar(words, word, ' ');
                    break;
                }
                word = WordsCount.countByChar(words, word, (char)ch);
            } catch (IOException | NullPointerException ex) {
                TextCalc.printFileError(str, OperationType.READ);
                break;
            }
        } while (true);

        TreeMap<String, Integer> sorted_words = new TreeMap<>(vc);
        sorted_words.putAll(words);
        try {
            for (Map.Entry<String, Integer> e : sorted_words.entrySet()) {
                writer.writeString(e.getKey() + "," + e.getValue());
            }
        } catch (IOException | NullPointerException ex) {
            TextCalc.printFileError(str, OperationType.WRITE);
        }
        try {
            reader.closeReader();
            writer.closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum OperationType {
        READ, WRITE
    }
    private static void printFileError(String str, OperationType type) {
        System.out.println("File '" + str + ((type == OperationType.READ)?"":".cvs") + "' " + ((type == OperationType.READ)?"read":"write") + " error!");
    }
}

/*
 * for sotring TreeMap
 */
class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
