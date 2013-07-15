package net.rusf.czujmao.HTTPserver;

//import javax.activation.*;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;

public class FileReader {
    public static String getFileOrDir (String str) {
        File fl = new File(str);
        if (fl.isDirectory())
            return getFileList(fl);
        else
            return "";
    }
    public static String getFileList (File fl) {
        String rStr = "";
        String dirName = fl.getPath() + File.separator;
        String[] list = fl.list();
        TreeSet<String> listOfDir = new TreeSet<String>();
        TreeSet<String> listOfFiles = new TreeSet<String>();
        File fll = null;
        for (String s : list) {
            fll = new File(dirName + s);
            String mt = new MimetypesFileTypeMap().getContentType(fll);
            if (fll.isDirectory())
                listOfDir.add("<tr><td><a href=\"" + convertPathToURL(fll.getPath(), Boolean.FALSE) + "\">"
                        + s + "</a></td><td></td><td></td><td></td></tr>");
            else listOfFiles.add(getFileInformation(fll));
        }
        rStr = "<table border=\"0\">";

        if (!Constants.rootPath.equals(dirName))
            rStr = rStr + "<tr><td><a href=\"" + convertPathToURL(fl.getParent() + File.separator, Boolean.TRUE) + "\">"
                    + ".." + "</a></td><td></td><td></td><td></td></tr>";
        for (String s : listOfDir) {
            rStr = rStr + s;
        }
        for (String s : listOfFiles) {
            rStr = rStr + s;
        }
        rStr = rStr + "</table>";
        return rStr;
    }
    private static String getFileInformation (File fl) {
        return "<tr><td><a href=\"" + convertPathToURL(fl.getPath(), Boolean.FALSE) + "\">"
            + fl.getName() + "</a></td><td>"
            + "Size:" + fl.length() + "</td><td>"
            + "Last modified:" + new Date(fl.lastModified()) + "</td><td>"
            + "</td></tr>";
    }
    private static String convertPathToURL (String path, Boolean itsParent) {
        String fullName = Constants.serverURL + ":" + Constants.serverPort;
        try {
//            return URLEncoder.encode(path.replaceAll(Pattern.quote(Constants.rootPath), fullName).replaceAll(Pattern.quote(File.separator), "/"), "UTF-8");
            return (itsParent)?"/":"" +
                    URLEncoder.encode(path.replaceAll(Pattern.quote(Constants.rootPath), "").replaceAll(Pattern.quote(File.separator), "/"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}