package homeworks.hw1;


import java.util.ArrayList;
import java.util.HashMap;

public class HW1 {
    public static void main(String[] args) {
        Tests.frequentWordsProblemTest();
        Tests.reverseComplementProblemTest();
    }
}

class Tests {
    public static void frequentWordsProblemTest() {

        String text = "ACGTTTCACGTTTTACGG";
        int k = 3;

        System.out.printf("***************%nQuestion 1%nK-mers in the text %s%n", Problems.frequentWords(text, k));

        text = "ACGTTTCACGTTTTACGG";
        k = 4;

        System.out.printf("K-mers in the text %s%n***************%n", Problems.frequentWords(text, k));
    }

    public static void reverseComplementProblemTest() {
        String text = "AGTCGCATAGT";
        System.out.printf("***************%nQuestion 2%nReverse Complement of %s is %s%n***************%n", text, Problems.reverseComplement(text));
    }
}

class Problems {

    public static int countingWordsProblem(String pattern, String text) {
        int count = 0;
        for (int i = 0; i < text.length() - pattern.length() + 1; i++)
            if (pattern.equals(text.substring(i, i + pattern.length())))
                count++;

        return count;
    }

    public static String frequentWords(String text, int k) {

        HashMap<String, Integer> frequentWords = new HashMap<>();
        int count;
        for (int i = 0; i < text.length() - k + 1; i++) {
            String pattern = text.substring(i, i + k);
            count = countingWordsProblem(pattern, text);
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

    public static String findMostFrequentKey(HashMap<String, Integer> frequentWords, ArrayList<Integer> list) {

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

    public static String reverseComplement(String text) {
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            newText = dnaComplement(ch, newText);
        }
        newText.reverse();
        return newText.toString();
    }

    public static StringBuilder dnaComplement(char ch, StringBuilder str) {
        if (ch == 'A')
            str.append("T");
        else if (ch == 'G')
            str.append("C");
        else if (ch == 'C')
            str.append("G");
        else
            str.append("A");
        return str;
    }
}