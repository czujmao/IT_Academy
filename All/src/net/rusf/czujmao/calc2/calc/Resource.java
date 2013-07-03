package net.rusf.czujmao.calc2.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Resource {
    private BufferedReader Inner;
    Resource () {
        Inner = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("my.function")));
    }

    public String next() throws IOException {
        String str = null;
        try {
            str = Inner.readLine();
        } catch (IOException ex) {
            System.err.println("Resource file Input error!");
            System.exit(0);
        }
        return str;
    }
}
