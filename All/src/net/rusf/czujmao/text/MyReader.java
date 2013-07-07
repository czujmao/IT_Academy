package net.rusf.czujmao.text;

import java.io.*;

/**
 * It's one-line-reader from file or StdIO
 * Count read line from file
 */

public class MyReader {
    private BufferedReader inner;
    /*
    * Reader for StdIO
    */
    MyReader () {
        inner = new BufferedReader(new InputStreamReader(System.in));
    }
    /*
    * Reader for file
    */
    MyReader (String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        if (!f.canRead()) {
            throw new FileNotFoundException();
        }
//        inner = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName).getAbsoluteFile())));
        inner = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    }
    /*
    * Return next triming line
    */
    public String nextLine() throws IOException {
        String str;
        try {
            str = inner.readLine();
            if (null != str) {
                str = str.trim();
            }
            return str;
        } catch (IOException ex) {
            return null;
        }
    }
    /*
    * Return next character
    */
    public int nextChar() throws IOException {
        try {
            return inner.read();
        } catch (IOException ex) {
            return -1;
        }
    }
    /*
    * Close file
    */
    public void closeReader() throws IOException {
        inner.close();
    }
}