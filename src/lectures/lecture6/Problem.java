package lectures.lecture6;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Problem {
    /*
    https://rosalind.info/problems/ba4j/
     */

    public static void main(String[] args) {
        generatingLinearSpectrumTest();
    }

    public static void generatingLinearSpectrumTest() {
        String peptide = "NQEL";
        System.out.printf("Linear spectrum of a %s peptide is %s%n", peptide, generatingLinearSpectrumProblem(peptide));
    }

    public static ArrayList<Integer> generatingLinearSpectrumProblem(String peptide) {
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

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        int sum = 0;
        list.add(sum);
        for (int i = 0; i < peptide.length(); i++)
            list.add(sum += hashMap.get(String.valueOf(peptide.charAt(i))));


        for (int j = 1; j < list.size() - 1; j++)
            for (int k = j + 1; k < list.size(); k++)
                list2.add(Math.abs(list.get(j) - list.get(k)));

        list.addAll(list2);
        Collections.sort(list);

        return list;
    }
}
