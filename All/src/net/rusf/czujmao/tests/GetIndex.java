package net.rusf.czujmao.tests;
import java.io.File;
import java.util.*;

public class GetIndex {
    static void p(String s) {
        System.out.println(s);
    }
    static private void listOfFiles (String str) {
        File fl = new File(str);
/*
        p("Имя файла:" + fl .getName());
        p("Путь:" + fl.getPath());
        p("Полный путь:" + fl.getAbsolutePath());
        p("Родительский каталог:" + fl.getParent());
        p(fl.exists() ? "существует" : "не существует");
        p(fl.canWrite() ? "можно записывать" : "нельзя записывать");
        p(fl.canRead() ? "можно читать" : "нельзя читать");
        p("Директория? " + (fl.isDirectory()? "да": " нет"));
        p(fl.isFile() ? "обычный файл" : "не обычный файл");
        Date fileDate = new Date(fl.lastModified());
        p("Последняя модификация файла:" + fileDate);
        p("Размер файла:" + fl.length() + " Bytes");
*/
        if (fl.isDirectory()) {
            String[] list = fl.list();
//            Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
//            Arrays.sort(list);
//            for (String s : list) {
//                listOfFiles(str + (str.endsWith("\\")?"":"\\") + s);
//            }
            ArrayList<String> listOfDir = new ArrayList<String>();
            ArrayList<String> listOfFiles = new ArrayList<String>();
            int i = 0;
            int j = 0;
            for (String s : list) {
                if (new File(str + (str.endsWith("\\")?"":"\\") + s).isDirectory())
                    listOfDir.add(i++, s);
                else listOfFiles.add(j++, s);
            }
            Collections.sort(listOfDir);
            Collections.sort(listOfFiles);
            p("..");
            String name = "";
            for (String s : listOfDir) {
                name = str + (str.endsWith("\\")?"":"\\") + s;
                fl = new File(name);
                System.out.println(name + '\t' + "Dir");
            }
            for (String s : listOfFiles) {
                name = str + (str.endsWith("\\")?"":"\\") + s;
                fl = new File(name);
                System.out.println(name + '\t' + "file" + '\t' + "Size:" + fl.length());
            }
        }
    }

    public static void main(String args[]) {
        listOfFiles("C:\\temp\\");
    }
}
