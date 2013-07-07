package net.rusf.czujmao.text;

import java.util.HashMap;

public class WordsCount {
/*    private static String word = "";
    static void countByString(HashMap<String, Integer> words, String str) {
        char[] chars = str.toCharArray();
        word = "";
        for (int i = 0; chars.length > i; i++) {
            if (Character.isLetter(chars[i])) {
                word = word + chars[i];
            } else {
                if (0 < word.length()) {
                    WordsCount.addWord(words, word.toLowerCase());
                    word = "";
                }
            }
        }
        if (0 < word.length()) {
            WordsCount.addWord(words, word.toLowerCase());
            word = "";
        }
    }
*/
    static String countByChar(HashMap<String, Integer> words, String word, char ch) {
        if (Character.isLetter(ch)) {
            word = word + ch;
        } else {
            if (0 < word.length()) {
                WordsCount.addWord(words, word.toLowerCase());
                word = "";
            }
        }
        return word;
    }

    private static void addWord(HashMap<String, Integer> words, String word) {
        Integer count = words.get((Object) word);
        if (null == count) {
            count = 0;
        }
        words.put(word, count + 1);
    }
}
