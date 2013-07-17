package net.rusf.czujmao.HTTPserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/*
 */
public class HTMLform {
    public static void make (HTTPstatus status, OutputStream outStream, byte[] data, String MIMEType) throws IOException {
        String head_str = "";
        switch (status) {
            case OK: {head_str = "HTTP/1.0 200 OK\n"; break;}
            case NOT_FOUND: {head_str = "HTTP/1.0 404 Not Found"; data = "WTF? File is lost!\n".getBytes(); break;}
            case ERROR: {head_str = "HTTP/1.0 500 Internal Error\n"; break;}
            case NOT_IMPLEMENTED: {head_str = "HTTP/1.0 501 Not Implemented\n"; break;}
        }

        head_str += "Content-Type: " + MIMEType + "\n";
        head_str += "Content-Encoding: UTF-8\n";
        head_str += "Content-Length: " + data.length + "\n";
        head_str += "\n";
        
        outStream.write(head_str.getBytes());
        outStream.write(data);
        outStream.write("\n\n".getBytes());
        outStream.flush();
    }
}
