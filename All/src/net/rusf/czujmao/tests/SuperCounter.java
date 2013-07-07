package net.rusf.czujmao.tests;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;

public class SuperCounter implements ICounter {

    private class MutableInteger {
        int value;
        public String toString() {
            return String.valueOf(value);
        }
    }

    private HashMap words = new HashMap();

    public ICounter addWord(String word) {
        MutableInteger count = (MutableInteger) words.get(word);
        if (count == null) {
            count = new MutableInteger();
            words.put(word, count);
        }
        count.value++;
        return this;
    }

    public Object byCount() {
        TreeMap sortedMap = new TreeMap(new Comparator() {
            public int compare(Object o1, Object o2) {
                int i1 = ((MutableInteger) words.get(o1)).value;
                int i2 = ((MutableInteger) words.get(o2)).value;
                if (i1 > i2) return -1;
                if (i1 < i2) return 1;
                return ((String) o1).compareTo((String) o2);
            }
        });
        sortedMap.putAll(words);
        return sortedMap;
    }
}