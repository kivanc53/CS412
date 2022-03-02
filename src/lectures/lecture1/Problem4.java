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

        System.out.printf("K-mers in the text %s%n", Problems4.frequentWords(text, k));
    }
}

class Problems4 {
    public static String frequentWords(String text, int k) {

        HashMap<String, Integer> frequentWords = new HashMap<String, Integer>();
        int count = 0;
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
        ArrayList<Integer> list = new ArrayList<Integer>();
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
        for (int i = 0; i < list.size(); i++) {
            index = 0;
            for (String j : frequentWords.keySet()) {
                if (index == list.get(i)) {
                    if (str.equals("")) {
                        str += j;
                    } else {
                        str += " ";
                        str += j;
                    }
                }
                index++;
            }
        }

        return str;
    }
}
