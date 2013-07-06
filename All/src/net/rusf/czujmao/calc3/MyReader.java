package net.rusf.czujmao.calc3;

import java.io.*;

/**
 * It's one-line-reader from file or StdIO
 * Count read line from file
 */

public class MyReader {
    private BufferedReader Inner;
    private long count = 0;
    private boolean fromFile;
    /*
    * Reader for StdIO
    */
    MyReader () {
        Inner = new BufferedReader(new InputStreamReader(System.in));
        fromFile = false;
    }
    /*
    * Reader for file
    */
    MyReader (String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        if (!f.canRead()) {
            throw new FileNotFoundException("File '" + f.getAbsolutePath() + "' not found!");
        }
        Inner = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName).getAbsoluteFile())));
        fromFile = true;
    }
    /*
    * Return next not-empty string
    * If it have eof or std input error - return "EXIT" for normal exit
    */
    public String next() throws IOException {
        String str;
        try {
            do {
                count += fromFile?1:0;
                str = Inner.readLine();
                if (null == str) {
                    str = "EXIT";
                } else {
                    str = str.trim();
                }
            } while (str.isEmpty());
            return str;
        } catch (IOException ex) {
            System.err.println("Input error" + (fromFile?" in line " + count:"") + "!");
            return "EXIT";
        }
    }
    /*
    * Return number of last reading line from file
    */
    public long getCount() {
        return count;
    }
    /*
    * Close file
    */
    public void closeReader() throws IOException {
        Inner.close();
    }
}