package homeworks.hw1;


import java.util.ArrayList;
import java.util.HashMap;

public class HW1_Kivanc_Yilmaz_S012733 {
    public static void main(String[] args) {
        Tests.frequentWordsProblemTest();
        Tests.reverseComplementProblemTest();
        Tests.patternMatchingProblemTest();
        Tests.clumpFindingProblemTest();
    }
}

class Tests {
    public static void frequentWordsProblemTest() {

        String text = "ACGTTTCACGTTTTACGG";
        int k = 3;

        System.out.printf("***************%nQuestion 1%nK-mers in the text %s%n", Problems.frequentWords(text, k, -1));

        text = "ACGTTTCACGTTTTACGG";
        k = 4;

        System.out.printf("K-mers in the text %s%n***************%n", Problems.frequentWords(text, k, -1));
    }

    public static void reverseComplementProblemTest() {
        String text = "AGTCGCATAGT";
        System.out.printf("***************%nQuestion 2%nReverse Complement of %s is %s%n***************%n", text, Problems.reverseComplement(text));
    }

    public static void patternMatchingProblemTest() {
        String pattern = "ATAT";
        String text = "GATATATGCATATACTT";
        System.out.printf("\"***************%nQuestion 3%n%s pattern %d times in this genome and indexes like these: %s%n***************%n", pattern, Problems.patternMatching(pattern, text).size(), Problems.patternMatching(pattern, text));
    }

    public static void clumpFindingProblemTest() {
        String genome = "GATCAGCATAAGGGTCCCTGCAATGCATGACAAGCCTGCAGTTGTTTTAC";
        int pl = 4;
        int wl = 25;
        int count = 3;

        System.out.printf("***************%nQuestion 4%nK-mers in DNA genome with L size %s%n", Problems.clumpFinding(genome, pl, wl, count));

        genome = "CGGACTCGACAGATGTGAAGAAATGTGAAGACTGAGTGAAGAGAAGAGGAAACACGACACGACATTGCGACATAATGTACGAATGTAATGTGCCTATGGC";
        pl = 5;
        wl = 75;
        count = 4;

        System.out.printf("K-mers in DNA genome with L size %s%n", Problems.clumpFinding(genome, pl, wl, count));

        genome = "AAAACGTCGAAAAA";
        pl = 2;
        wl = 4;
        count = 2;

        System.out.printf("K-mers in DNA genome with L size %s%n***************%n", Problems.clumpFinding(genome, pl, wl, count));
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

    public static ArrayList<String> frequentWords(String text, int k, int count2) {

        HashMap<String, Integer> frequentWordsAndCounts = new HashMap<>();
        int count;
        for (int i = 0; i < text.length() - k + 1; i++) {
            String pattern = text.substring(i, i + k);
            count = countingWordsProblem(pattern, text);
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

    public static String reverseComplement(String text) {
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            dnaComplement(ch, newText);
        }

        return newText.reverse().toString();
    }

    public static void dnaComplement(char ch, StringBuilder str) {
        if (ch == 'A')
            str.append("T");
        else if (ch == 'G')
            str.append("C");
        else if (ch == 'C')
            str.append("G");
        else
            str.append("A");
    }

    public static ArrayList<Integer> patternMatching(String pattern, String text) {
        ArrayList<Integer> indexOfPattern = new ArrayList<>();
        for (int i = 0; i < text.length() - pattern.length() + 1; i++)
            if (pattern.equals(text.substring(i, i + pattern.length())))
                indexOfPattern.add(i);

        return indexOfPattern;
    }

    public static ArrayList<String> clumpFinding(String text, int pl, int wl, int count) {

        ArrayList<String> temp;
        ArrayList<String> result = new ArrayList<>();
        String str;
        for (int i = 0; i < text.length() - wl + 1; i++) {
            str = text.substring(i, i + wl);
            temp = frequentWords(str, pl, count);
            for (String s : temp) {
                if (!result.contains(s))
                    result.add(s);
            }
        }

        return result;
    }
}