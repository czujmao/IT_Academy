package net.rusf.czujmao.text;

import java.io.*;

public class MyWriter {
    private BufferedWriter outer;
    MyWriter (String fileName) throws SecurityException {
        try {
            outer = new BufferedWriter(new FileWriter(fileName));

        } catch (IOException ex) {
            throw new SecurityException();
        }
    }
    public void writeString(String string) throws IOException {
        outer.write(string);
        outer.newLine();
    }
    /*
    * Close file
    */
    public void closeWriter() throws IOException {
        outer.close();
    }
}
