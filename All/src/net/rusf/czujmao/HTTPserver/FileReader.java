package net.rusf.czujmao.HTTPserver;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class FileReader {
    public static Boolean fileExist(String str) {
        return new File(str).exists();
    }

    public static String fileMIMEType (String str) {
        File fl = new File(str);
        if (fl.isDirectory())
            return "text/html";
        else
            return new MimetypesFileTypeMap().getContentType(fl);
    }

    public static byte[] getFileOrDir (String str) {
        File fl = new File(str);
        if (fl.isDirectory())
            return getFileList(fl);
        else
            return getFile(fl);
    }

    public static byte[] getFileList (File fl) {
        String rStr = "";
        String dirName = fl.getPath() + File.separator;
        String index = dirName + "index.html";
        if (fileExist(index)) {
            return getFile(new File (index));
        }
        String[] list = fl.list();
        TreeSet<String> listOfDir = new TreeSet<String>();
        TreeSet<String> listOfFiles = new TreeSet<String>();
        File fll = null;
        for (String s : list) {
            fll = new File(dirName + s);
            if (fll.isDirectory())
                listOfDir.add("<tr><td><a href=\"" + convertPathToURL(fll.getPath(), Boolean.FALSE) + "\">"
                        + s + "</a></td><td></td><td></td><td></td></tr>");
            else listOfFiles.add(getFileInformation(fll));
        }
        rStr = "<html><title>list</title>" +
                "<meta http-equiv=\"Content-Type\" content = \"text/html;charset=utf-8\">" +
                "<body><table border=\"0\">";

        if (!Constants.rootPath.equals(dirName))
            rStr = rStr + "<tr><td><a href=\"" + convertPathToURL(fl.getParent() + File.separator, Boolean.TRUE) + "\">"
                    + ".." + "</a></td><td></td><td></td><td></td></tr>";
        for (String s : listOfDir) {
            rStr = rStr + s;
        }
        for (String s : listOfFiles) {
            rStr = rStr + s;
        }
        rStr = rStr + "</table></body></html>";
        return rStr.getBytes();
    }

    private static String getFileInformation (File fl) {
        return "<tr><td><a href=\"" + convertPathToURL(fl.getPath(), Boolean.FALSE) + "\">"
            + fl.getName() + "</a></td><td>"
            + "Size:" + fl.length() + "</td><td>"
            + "Last modified:" + new Date(fl.lastModified()) + "</td><td>"
            + "</td></tr>";
    }
    private static String convertPathToURL (String path, Boolean itsParent) {
        try {
            return (itsParent)?"/":"" +
                    URLEncoder.encode(path.replaceAll(Pattern.quote(Constants.rootPath), "").replaceAll(Pattern.quote(File.separator), "/"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
    public static byte[] getFile(File fl) {
        InputStream is = null;
        try {
            is = new FileInputStream(fl);
        } catch (FileNotFoundException ex) {
            return ex.getMessage().getBytes();
        }
        long length = fl.length();
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;

        try {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            return bytes;
        } catch (IOException ex) {
            return ex.getMessage().getBytes();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                //do something
            }
        }
    }
}