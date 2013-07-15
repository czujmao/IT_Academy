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
//                Runnable r = new ThreadedHandler(incoming, threadCount);
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
//    private int counter;
    /**
     Constructs a handler.
     @param i the incoming socket
//     @param c the counter for the handlers (used in prompts)
     */
//    public ThreadedHandler(Socket i, int c) {
    public ThreadedHandler(Socket i) {
        incoming = i;
//        counter = c;
    }

    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);

                String localPath = "";
                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = URLDecoder.decode(in.nextLine(), "UTF-8").trim();
                    if ("".equals(line))
                        done = true;
                    else {
                        if (line.startsWith("GET")) {
                            localPath = Constants.rootPath + line.replaceAll(Pattern.quote("GET /"), "").replaceAll(Pattern.quote(" HTTP/1.1"), "");
                        }
                    }
                }


                String s = "<html><title>list</title>" +
                        "<meta http-equiv=\"Content-Type\" content = \"text/html;charset=utf-8\">" +
                        "<body>" + FileReader.getFileOrDir(localPath) + "</body></html>";
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html");
                out.println("Content-Encoding: UTF-8");
                out.println("Content-Length: "+s.length());
                out.println("");
                out.println(s);
            } finally {
                incoming.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*

  1. Под линухом проверит, как ведут себя линки и прочие типы файлов
  2. Под виндой тоже
  3. Проверить при выводе списка, куда видут линки, если линки на директории, для безопасности
+  4. Выравнивание по ширине - средствами html, через таблицу, только убрать границы
+  5. index.html отдавать в виде строки, без создания реального файла. Проверить, можно ли создавать виртуальные файлы
  6. Проверка, директория или нет - вынести на уровень выше.
  7. Если в директории есть файл index.html - считать и отдавать его в видео одной строки, если нет - сформировать на лету
+  8. Сразу формировать таблицу со всеми данными файла, на этапе разделения списка на директории и файлы, тут же формировать урл
  9. URLdecoder - использовать класс для обработки русских букв в запросе

  Структура.
+-  0. Из конфиг-файла читаем, что какая директория является корнем
-  1. Слушатель на порту, при возникновении коннекта запускает обработчик в новом потоке. Разобраться с таймаутом
  2. Обработчик получает строку запроса (проверить вид), парсит?, если пусто - отдаём корень, если есть путь -
  суммируем с абсолютным путём корня и проверяем. Если нет такого - 404 (сделать отдельный класс - сообщатель об ошибках,
  не нужно, код ошибки в заголовке ответа, дальше сообщение не короче 512 байт).
  Если простой файл - читаем и отдаём файл (разобраться с заголовками для разных типов, разобраться, почему MIME-type
  одинаковый). Если директория - запрашиваем у текущего класса index.html. Закрываем поток.
+  3. При создании урлов в html не забывать заменять абсолютный путь на адрес сервера

MVC - главный процесс в треде - контроллер
при наличиии входных данных на сокете - получает URL (не забыть отказ при отсуствии GET-а)
Вызываем модель по урлу, модель отдайт строку данных,


*/