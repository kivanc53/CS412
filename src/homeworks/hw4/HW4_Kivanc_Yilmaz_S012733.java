package homeworks.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class HW4_Kivanc_Yilmaz_S012733 {
    public static void main(String[] args) {
        Tests.patternTranslationTest();
        Tests.generatingTheoreticalSpectrumTest();
        Tests.cyclicPeptideScoringTest();
    }
}

class Tests {
    public static void patternTranslationTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("AUG", "M");
        hashMap.put("AUA", "I");
        hashMap.put("AUC", "I");
        hashMap.put("AUU", "I");
        hashMap.put("AGG", "R");
        hashMap.put("AGA", "R");
        hashMap.put("AGC", "S");
        hashMap.put("AGU", "S");
        hashMap.put("ACG", "T");
        hashMap.put("ACA", "T");
        hashMap.put("ACC", "T");
        hashMap.put("ACU", "T");
        hashMap.put("AAG", "K");
        hashMap.put("AAA", "K");
        hashMap.put("AAC", "N");
        hashMap.put("AAU", "N");
        hashMap.put("CAU", "H");
        hashMap.put("CAC", "H");
        hashMap.put("CAA", "Q");
        hashMap.put("CAG", "Q");
        hashMap.put("CCU", "P");
        hashMap.put("CCC", "P");
        hashMap.put("CCA", "P");
        hashMap.put("CCG", "P");
        hashMap.put("CGU", "R");
        hashMap.put("CGC", "R");
        hashMap.put("CGA", "R");
        hashMap.put("CGG", "R");
        hashMap.put("CUU", "L");
        hashMap.put("CUC", "L");
        hashMap.put("CUA", "L");
        hashMap.put("CUG", "L");
        hashMap.put("UUG", "L");
        hashMap.put("UUA", "L");
        hashMap.put("UUC", "F");
        hashMap.put("UUU", "F");
        hashMap.put("UGG", "W");
        hashMap.put("UGA", "*");
        hashMap.put("UGC", "C");
        hashMap.put("UGU", "C");
        hashMap.put("UCG", "S");
        hashMap.put("UCA", "S");
        hashMap.put("UCC", "S");
        hashMap.put("UCU", "S");
        hashMap.put("UAG", "*");
        hashMap.put("UAA", "*");
        hashMap.put("UAC", "Y");
        hashMap.put("UAU", "Y");
        hashMap.put("GUG", "V");
        hashMap.put("GUA", "V");
        hashMap.put("GUC", "V");
        hashMap.put("GUU", "V");
        hashMap.put("GGG", "G");
        hashMap.put("GGA", "G");
        hashMap.put("GGC", "G");
        hashMap.put("GGU", "G");
        hashMap.put("GCG", "A");
        hashMap.put("GCA", "A");
        hashMap.put("GCC", "A");
        hashMap.put("GCU", "A");
        hashMap.put("GAG", "E");
        hashMap.put("GAA", "E");
        hashMap.put("GAC", "D");
        hashMap.put("GAU", "D");

        String pattern = "AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA";

        System.out.printf("**********%nQuestion 1%nTransition of pattern is %s%n**********%n", Problems.patternTranslationProblem(pattern, hashMap));
    }

    public static void generatingTheoreticalSpectrumTest() {
        String peptide = "LEQN";
        System.out.printf("**********%nQuestion 2%nTheoretical spectrum of a %s peptide is %s%n", peptide, Problems.generatingTheoreticalSpectrumProblem(peptide));

        peptide = "NQEL";
        System.out.printf("Theoretical spectrum of a %s peptide is %s%n", peptide, Problems.generatingTheoreticalSpectrumProblem(peptide));

        peptide = "IAQMLFYCKVATN";
        System.out.printf("Theoretical spectrum of a %s peptide is %s%n**********%n", peptide, Problems.generatingTheoreticalSpectrumProblem(peptide));
    }

    public static void cyclicPeptideScoringTest() {
        String peptide = "NQEL";
        ArrayList<Integer> spectrum = new ArrayList<>(Arrays.asList(0, 99, 113, 114, 128, 227, 257, 299, 355, 356, 370, 371, 484));

        System.out.printf("**********%nQuestion 3%nThe score of Peptide %s against Spectrum %d%n**********%n", peptide, Problems.cyclicPeptideScoringProblem(peptide, spectrum));
    }
}

class Problems {
    public static String patternTranslationProblem(String pattern, HashMap<String, String> hashMap) {
        String peptide = "", temp;
        for (int i = 0; i < pattern.length() - 3 + 1; i += 3) {
            temp = hashMap.get(pattern.substring(i, i + 3));
            if (!temp.equals("*"))
                peptide += temp;
            else
                break;
        }
        return peptide;
    }

    public static ArrayList<Integer> generatingTheoreticalSpectrumProblem(String peptide) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("G", 57);
        hashMap.put("A", 71);
        hashMap.put("S", 87);
        hashMap.put("P", 97);
        hashMap.put("V", 99);
        hashMap.put("T", 101);
        hashMap.put("C", 103);
        hashMap.put("I", 113);
        hashMap.put("L", 113);
        hashMap.put("N", 114);
        hashMap.put("D", 115);
        hashMap.put("K", 128);
        hashMap.put("Q", 128);
        hashMap.put("E", 129);
        hashMap.put("M", 131);
        hashMap.put("H", 137);
        hashMap.put("F", 147);
        hashMap.put("R", 156);
        hashMap.put("Y", 163);
        hashMap.put("W", 186);

        ArrayList<String> substrings = allPossibleSubstrings(peptide);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        for (String str : substrings) {
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                sum += hashMap.get(str.substring(i, i + 1));
            }
            list.add(sum);
        }
        Collections.sort(list);
        return list;
    }

    public static ArrayList<String> allPossibleSubstrings(String peptide) {
        ArrayList<String> subStrings = new ArrayList<>();
        int length = peptide.length();
        for (int i = 1; i < length; i++)
            for (int j = 0; j < length; j++)
                if (j + i > length)
                    subStrings.add(peptide.substring(j) + peptide.substring(0, i + j - length));
                else
                    subStrings.add(peptide.substring(j, j + i));
        subStrings.add(peptide);
        return subStrings;
    }

    public static int cyclicPeptideScoringProblem(String peptide, ArrayList<Integer> list) {
        ArrayList<Integer> theoreticalSpectrum = generatingTheoreticalSpectrumProblem(peptide);

        int score = 0;
        for (Integer integer : list)
            if (theoreticalSpectrum.contains(integer))
                score++;
        return score;
    }
}
