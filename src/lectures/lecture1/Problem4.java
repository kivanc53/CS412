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

        System.out.printf("K-mers in the text %s%n", FrequentWordsProblem.frequentWords(text, k, -1));
    }
}

class FrequentWordsProblem {
    public static ArrayList<String> frequentWords(String text, int k, int count2) {

        HashMap<String, Integer> frequentWordsAndCounts = new HashMap<>();
        int count;
        for (int i = 0; i < text.length() - k + 1; i++) {
            String pattern = text.substring(i, i + k);
            count = Problems3.countingWordsProblem(pattern, text);
            if (!frequentWordsAndCounts.containsKey(pattern)) {
                if (count2 != -1 && count == count2) {
                    frequentWordsAndCounts.put(pattern, count);
                } else if (count2 == -1) {
                    frequentWordsAndCounts.put(pattern, count);
                }
            }
        }

        return findIndexOfMostFrequentValues(frequentWordsAndCounts);
    }

    public static ArrayList<String> findIndexOfMostFrequentValues(HashMap<String, Integer> frequentWordsAndCounts) {

        int max = 0, index = 0;
        ArrayList<Integer> indexOfFrequentWords = new ArrayList<>();
        for (Integer e : frequentWordsAndCounts.values()) {
            if (e > max) {
                max = e;
                indexOfFrequentWords.removeAll(indexOfFrequentWords);
                indexOfFrequentWords.add(index);
            } else if (e == max) {
                indexOfFrequentWords.add(index);
            }
            index++;
        }

        return findMostFrequentKey(frequentWordsAndCounts, indexOfFrequentWords);
    }

    public static ArrayList<String> findMostFrequentKey(HashMap<String, Integer> frequentWordsAndCounts, ArrayList<Integer> indexOfFrequentWords) {

        int index;
        ArrayList<String> results = new ArrayList<>();
        for (Integer integer : indexOfFrequentWords) {
            index = 0;
            for (String j : frequentWordsAndCounts.keySet()) {
                if (index == integer && !results.contains(j)) {
                    results.add(j);
                }
                index++;
            }
        }

        return results;
    }
}
