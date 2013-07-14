package net.rusf.czujmao.HTTPserver;

//import javax.activation.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;

public class FileReader {
    public static String getFileList (String str) {
        String rStr = "";
        File fl = new File(str);
        if (fl.isDirectory()) {
            String[] list = fl.list();
            TreeSet<String> listOfDir = new TreeSet<String>();
            TreeSet<String> listOfFiles = new TreeSet<String>();
            for (String s : list) {
                fl = new File(str + (str.endsWith(File.separator)?"":File.separator) + s);
                if (fl.isDirectory())
                    listOfDir.add("<tr><td><a href=\"" + convertPathToURL(fl.getPath()) + "\">"
                            + s + "</a></td><td></td><td></td><td></td></tr>");
                else listOfFiles.add(getFileInformation(fl));
            }
            rStr = "<table border=\"0\">";
//            p("..");
            for (String s : listOfDir) {
                rStr = rStr + s;
            }
            for (String s : listOfFiles) {
                rStr = rStr + s;
            }
        }
        return rStr;
    }
    private static String getFileInformation (File fl) {
        return "<tr><td><a href=\"" + convertPathToURL(fl.getPath()) + "\">"
            + fl.getName() + "</a></td><td>"
            + "Size:" + fl.length() + "</td><td>"
            + "Last modified:" + new Date(fl.lastModified()) + "</td><td>"
            + "</td></tr>";
    }
    private static String convertPathToURL (String path) {
        String fullName = Constants.serverURL + ":" + Constants.serverPort;
        try {
//            return URLEncoder.encode(path.replaceAll(Pattern.quote(Constants.rootPath), fullName).replaceAll(Pattern.quote(File.separator), "/"), "UTF-8");
            return URLEncoder.encode(path.replaceAll(Pattern.quote(Constants.rootPath), "").replaceAll(Pattern.quote(File.separator), "/"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}