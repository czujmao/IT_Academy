package net.rusf.czujmao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;

public class Game {

    public static void main (String[] args) {
        int max_int = 100;
        if (args.length > 0) {
            try {
                max_int = Integer.parseInt(args[0]);
            } catch (NumberFormatException eX) {
                System.out.println("На входе может быть только число!");
                System.exit(0);
            }
        }

        BufferedReader Inner = new BufferedReader(new InputStreamReader(System.in));
        Random generator = new Random(new Date().getTime());
        int curr_int, max_try, curr_try, myint;
        String mystr = "";
        while (!"q".equals(mystr)) {
            curr_int = generator.nextInt(max_int);
            max_try = (int) Math.sqrt(max_int);
            curr_try = 1;
            System.out.println("Загадано случайное число, больше 0 и меньше " + max_int);
            System.out.println("У вас не более " + max_try + " попыток");
            System.out.println("Введите число (q для выхода):");
            while (curr_try <= max_try) {
                System.out.println("Попытка № " + curr_try);
                try {
                    mystr = Inner.readLine();
                } catch (IOException eX) {
                    System.out.println("Ошибка ввода!");
                }
                if ("q".equals(mystr)) {
                    break;
                }
                try {
                    myint = Integer.parseInt(mystr);
                    if (myint == curr_int) {
                        System.out.println("Бинго!");
                        curr_try = max_try + 10;
                    } else if (myint > curr_int) {
                        System.out.println("Ваше число больше");
                        curr_try += 1;
                    } else {
                        System.out.println("Ваше число меньше");
                        curr_try += 1;
                    }
                } catch (NumberFormatException eX) {
                    System.out.println("Это не число!");
                }
                if (curr_try == max_try + 1) {
                    System.out.println("Увы, вы проиграли");
                }
            }
        }
    }
}
