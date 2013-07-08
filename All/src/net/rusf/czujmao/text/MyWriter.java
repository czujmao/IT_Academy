package net.rusf.czujmao.text;

import java.io.*;

public class MyWriter {
    private BufferedWriter outer;
    MyWriter (String fileName) throws IOException {
        outer = new BufferedWriter(new FileWriter(fileName));
    }
    /*
    * Write one string and new-line-code into file
    */
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
