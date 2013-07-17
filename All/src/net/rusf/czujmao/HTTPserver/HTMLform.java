package net.rusf.czujmao.HTTPserver;

import java.io.OutputStream;
import java.io.PrintWriter;

/*
 */
public class HTMLform {
    public static void make (HTTPstatus status, OutputStream outStream, String data, String MIMEType) {
        String status_str = "";
        PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
        switch (status) {
            case OK: {status_str = "HTTP/1.0 200 OK"; break;}
            case NOT_FOUND: {status_str = "HTTP/1.0 404 Not Found"; data = "WTF? File is lost!"; break;}
            case ERROR: {status_str = "HTTP/1.0 500 Internal Error"; break;}
            case NOT_IMPLEMENTED: {status_str = "HTTP/1.0 501 Not Implemented"; break;}
        }

        String s = "<html><title>list</title>" +
                "<meta http-equiv=\"Content-Type\" content = \"text/html;charset=utf-8\">" +
                "<body>" + data + "</body></html>";
        out.println(status_str);
        out.println("Content-Type: " + MIMEType);
        out.println("Content-Encoding: UTF-8");
        out.println("Content-Length: "+s.length());
        out.println("");
        out.println(s);
    }
}
