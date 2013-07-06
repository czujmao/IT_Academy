package net.rusf.czujmao.text;

import java.io.*;

/**
 * It's one-line-reader from file or StdIO
 * Count read line from file
 */

public class MyReader {
    private BufferedReader Inner;
    /*
    * Reader for StdIO
    */
    MyReader () {
        Inner = new BufferedReader(new InputStreamReader(System.in));
    }
    /*
    * Reader for file
    */
    MyReader (String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        if (!f.canRead()) {
            throw new FileNotFoundException();
        }
        Inner = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName).getAbsoluteFile())));
    }
    /*
    * Return next not-empty string
    * If it have eof or std input error - return "EXIT" for normal exit
    */
    public String next() throws IOException {
        String str;
        try {
            str = Inner.readLine();
            if (null != str) {
                str = str.trim();
            }
            return str;
        } catch (IOException ex) {
            return null;
        }
    }
    /*
    * Close file
    */
    public void closeReader() throws IOException {
        Inner.close();
    }
}