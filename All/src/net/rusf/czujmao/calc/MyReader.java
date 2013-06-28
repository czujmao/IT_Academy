package net.rusf.czujmao.calc;

import java.io.*;

public class MyReader {
    private BufferedReader Inner;
    private long count = 0;
    private boolean fromFile;
    MyReader () {
        Inner = new BufferedReader(new InputStreamReader(System.in));
        fromFile = false;
    }
    MyReader (String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.canRead()) {
            throw new FileNotFoundException("File '" + f.getAbsolutePath() + "' not found!");
        }
        Inner = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        fromFile = true;
    }

    public String next() throws IOException {
        String str;
        try {
            do {
                count += fromFile?1:0;
                str = Inner.readLine();
            } while (str.isEmpty());
            return str;
        } catch (IOException ex) {
            System.err.println("Input error" + (fromFile?" in line " + count:"") + "!");
            return null;
        }
    }
    public long getCount() {
        return count;
    }
    public void closeReader() throws IOException {
        Inner.close();
    }
}
