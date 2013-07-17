package net.rusf.czujmao.HTTPserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This CzujMao WebServer - is very simple multithreaded file HTTP server
 */
public class CMWS {
    public static void main(String[] args ) {
        try {
            int threadCount = 1;
            ServerSocket s = new ServerSocket(Constants.serverPort);

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + threadCount);
                Runnable r = new ThreadedHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                threadCount++;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
/**
 This class handles the client input for one server socket connection.
 */
class ThreadedHandler implements Runnable {
    private Socket incoming;
    public ThreadedHandler(Socket i) {
        incoming = i;
    }

    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                Scanner in = new Scanner(inStream);
                String localPath = null;
                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = URLDecoder.decode(in.nextLine(), "UTF-8").trim();
                    if ("".equals(line))
                        done = true;
                    else {
                        if (line.startsWith("GET")) {
                            localPath = Constants.rootPath + line.replaceAll(Pattern.quote("GET /"), "").replaceAll(Pattern.quote(" HTTP/1.1"), "");
                        } else if (line.startsWith("HEAD")) {
                            localPath = "HEAD";
                        }
                    }
                }
                if (null == localPath) {
                     HTMLform.make(HTTPstatus.NOT_IMPLEMENTED, outStream, "Command not implemented".getBytes(), "text/html");
                } else if ("HEAD".equals(localPath)) {
                    HTMLform.make(HTTPstatus.OK, outStream, new byte[]{}, "text/html");
                } else {
                    if (FileReader.fileExist(localPath)) {
                        HTMLform.make(HTTPstatus.OK, outStream, FileReader.getFileOrDir(localPath), FileReader.fileMIMEType(localPath));
                    } else {
                        HTMLform.make(HTTPstatus.NOT_FOUND, outStream, "".getBytes(), "text/html");
                    }
                }
                inStream.close();
                outStream.close();
            } finally {
                incoming.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}