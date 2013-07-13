package net.rusf.czujmao.tests;
import javax.activation.*;
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
/*
            Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
            Arrays.sort(list);
            for (String s : list) {
                listOfFiles(str + (str.endsWith("\\")?"":"\\") + s);
            }
            ArrayList<String> listOfDir = new ArrayList<String>();
            ArrayList<String> listOfFiles = new ArrayList<String>();
*/
            TreeSet<String> listOfDir = new TreeSet<String>();
            TreeSet<String> listOfFiles = new TreeSet<String>();
//            int i = 0;
//            int j = 0;
            for (String s : list) {
                fl = new File(str + (str.endsWith(File.separator)?"":File.separator) + s);
                if (fl.isDirectory())
//                    listOfDir.add(i++, s);
//                else listOfFiles.add(j++, s);
                    listOfDir.add(s + '\t' + "Dir");
                else listOfFiles.add(s + '\t' + "file" + '\t'
                        + "Size:" + fl.length()
                        + '\t' + "Last modified:" + new Date(fl.lastModified())
                        + '\t' + "MIME type:" + new MimetypesFileTypeMap().getContentType(fl));

            }
//            Collections.sort(listOfDir, String.CASE_INSENSITIVE_ORDER);
//            Collections.sort(listOfFiles, String.CASE_INSENSITIVE_ORDER);
            p("..");
//            String name = "";
            for (String s : listOfDir) {
//                name = str + (str.endsWith(File.separator)?"":File.separator) + s;
//                fl = new File(name);
//                System.out.println(s + '\t' + "Dir");
                p(s);
            }
            for (String s : listOfFiles) {
//                name = str + (str.endsWith(File.separator)?"":File.separator) + s;
//                fl = new File(name);
//                System.out.println(s);
                p(s);
            }
        }
    }

    public static void main(String args[]) {
        listOfFiles("C:\\temp\\");
    }
}
  /*
  1. Под линухом проверит, как ведут себя линки и прочие типы файлов
  2. Под виндой тоже
  3. Проверить при выводе списка, куда видут линки, если линки на директории, для безопасности
  4. Выравнивание по ширине - средствами html, через таблицу, только убрать границы
  5. index.html отдавать в виде строки, без создания реального файла. Проверить, можно ли создавать виртуальные файлы
  6. Проверка, директория или нет - вынести на уровень выше.
  7. Если в директории есть файл index.html - считать и отдавать его в видео одной строки, если нет - сформировать на лету
  8. Сразу формировать таблицу со всеми данными файла, на этапе разделения списка на директории и файлы, тут же формировать урл

  Структура.
  0. Из конфиг-файла читаем, что какая директория является корнем
  1. Слушатель не порту, при возникновении коннекта запускает обработчик в новом потоке. Разобраться с таймаутом
  2. Обработчик получает строку запроса (проверить вид), парсит?, если пусто - отдаём корень, если есть путь -
  суммируем с абсолютным путём корня и проверяем. Если нет такого - 404 (сделать отдельный класс - сообщатель об ошибках,
  не нужно, код ошибки в заголовке ответа, дальше сообщение не короче 512 байт).
  Если простой файл - читаем и отдаём файл (разобраться с заголовками для разных типов, разобраться, почему MIME-type
  одинаковый). Если директория - запрашиваем у текущего класса index.html. Закрываем поток.
  3. При создании урлов в html не забывать заменять абсолютный путь на адрес сервера

  */