package lectures.lecture1;


/*
    Frequent words problem

        input = string text and integer k
        output = all most frequent k-mers in text(it can be more than 1)
        **k-mer = string of length k

    The goal is finding most frequent k-mer in a string.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Problem4 {
    public static void main(String[] args) {
        FrequentWordsProblemTest.run();
    }
}

class FrequentWordsProblemTest {
    public static void run() {

        String text = "ACGTTTCACGTTTTACGG";
        int k = 3;

        System.out.printf("K-mers in the text %s%n", FrequentWordsProblem.frequentWords(text, k));
    }
}

class FrequentWordsProblem {
    public static String frequentWords(String text, int k) {

        HashMap<String, Integer> frequentWords = new HashMap<>();
        int count;
        for (int i = 0; i < text.length() - k + 1; i++) {
            String pattern = text.substring(i, i + k);
            count = Problems3.countingWordsProblem(pattern, text);
            if (!frequentWords.containsKey(pattern)) {
                frequentWords.put(pattern, count);
            }
        }

        return findIndexOfMostFrequentValues(frequentWords);
    }

    public static String findIndexOfMostFrequentValues(HashMap<String, Integer> frequentWords) {

        int max = 0, index = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer e : frequentWords.values()) {
            if (e > max) {
                max = e;
                list.removeAll(list);
                list.add(index);
            } else if (e == max) {
                list.add(index);
            }
            index++;
        }

        return findMostFrequentKey(frequentWords, list);
    }

    public static String findMostFrequentKey(HashMap<String, Integer> frequentWords ,ArrayList<Integer> list){

        String str = "";
        int index;
        for (Integer integer : list) {
            index = 0;
            for (String j : frequentWords.keySet()) {
                if (index == integer) {
                    if (!str.equals("")) {
                        str += " ";
                    }
                    str += j;
                }
                index++;
            }
        }

        return str;
    }
}
