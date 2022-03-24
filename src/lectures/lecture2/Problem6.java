package lectures.lecture2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Problem6 {
    public static void main(String[] args) {
        MedianStringProblemTest.medianStringProblemTest1();
        MedianStringProblemTest.medianStringProblemTest2();
        MedianStringProblemTest.medianStringProblemTest3();
    }
}

class MedianStringProblemTest {
    public static void medianStringProblemTest1() {
        String pattern = "GATTCTCA";
        String DNA = "GCAAAGACGCTGACCAA";
        System.out.printf("Distance between a %s pattern and a %s DNA is %d%n", pattern, DNA, MedianStringProblem.medianStringProblem(pattern, DNA));
    }

    public static void medianStringProblemTest2() {
        String pattern = "AAA";
        String[] setOfStrings = new String[]{"TTACCTTAAC",
                "GATATCTGTC",
                "ACGGCGTTCG",
                "CCCTAAAGAG",
                "CGTCAGAGGT"};
        System.out.printf("Distance between a %s pattern and a %s set of Strings is %d%n", pattern, Arrays.toString(setOfStrings), MedianStringProblem.medianStringProblem(pattern, setOfStrings));
    }

    public static void medianStringProblemTest3() {
        String[] setOfStrings = new String[]{"TTACCTTAAC",
                "GATATCTGTC",
                "ACGGCGTTCG",
                "CCCTAAAGAG",
                "CGTCAGAGGT"};
        int k = 3;
        System.out.printf("A k-mer minimizing distance d(k-mer, Dna) among all k-mers is %s%n", MedianStringProblem.medianStringProblem(setOfStrings, k));
    }
}

class MedianStringProblem {
    public static ArrayList<String> createAllPossibleKmers(int k) {
        ArrayList<String> list = new ArrayList<>();
        Random random = new Random();
        String dna = "ATGC", temp = "";
        int count = 0;
        while (count != Math.pow(4, k)) {
            temp = "";
            for (int i = 0; i < k; i++) {
                temp += dna.charAt(random.nextInt(4));
            }
            if (!list.contains(temp)) {
                list.add(temp);
                count++;
            }
        }
        return list;
    }

    public static String medianStringProblem(String[] setOfStrings, int k) {
        ArrayList<String> allPossibleKmers = createAllPossibleKmers(k);
        int min = Integer.MAX_VALUE, index = 0, temp = min;
        for (int i = 0; i < allPossibleKmers.size(); i++) {
            min = Math.min(min, medianStringProblem(allPossibleKmers.get(i), setOfStrings));
            if (temp != min) {
                index = i;
            }
            temp = min;
        }
        return allPossibleKmers.get(index);
    }

    public static int medianStringProblem(String pattern, String DNA) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < DNA.length() - pattern.length() + 1; i++) {
            int j = 0, distance = 0;
            for (char c : pattern.toCharArray()) {
                if (c != DNA.substring(i, i + pattern.length()).charAt(j))
                    distance++;
                j++;
            }
            if (distance < min)
                min = distance;
        }
        return min;
    }

    public static int medianStringProblem(String pattern, String[] strings) {
        int min = 0;
        for (String str : strings) {
            min += medianStringProblem(pattern, str);
        }
        return min;
    }
}
