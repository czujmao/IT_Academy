package net.rusf.czujmao.text;

import java.util.HashMap;

public class WordsCount {
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
