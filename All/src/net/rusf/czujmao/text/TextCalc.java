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
                TextCalc.printFileError(str);
            }
        } else {
            MyReader tmpreader =  new MyReader();
            System.out.print("Enter file name: ");
            try {
                str = tmpreader.nextLine();
                reader = new MyReader(str);
                writer = new MyWriter(str + ".cvs");
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
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        ValueComparator vc =  new ValueComparator(words);
        String word = "";
        do {
            try {
                int ch = reader.nextChar();
                if (-1 == ch) break;
                word = WordsCount.countByChar(words, word, (char)ch);
            } catch (IOException e) {
                TextCalc.printFileError(str);
                break;
            }
        } while (true);

        TreeMap<String, Integer> sorted_words = new TreeMap<String, Integer>(vc);
        sorted_words.putAll(words);
        try {
            for (Map.Entry<String, Integer> e : sorted_words.entrySet()) {
                writer.writeString(e.getKey() + "," + e.getValue());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            reader.closeReader();
            writer.closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFileError(String str) {
        System.out.println("File '" + str + "' read error!");
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