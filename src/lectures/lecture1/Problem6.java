package lectures.lecture1;


/*
    Clump finding problem

        input = DNA Genome,
                length of pattern (k),
                window length(l),
                number of pattern in clump(t)
        output = all k-mers in clump

    goal is try to find true k-mers which has k-length and t-number in l-area of DNA genome
 */


import java.util.ArrayList;

public class Problem6 {
    public static void main(String[] args) {
        ClumpFindingProblemTest.run();
    }
}

class ClumpFindingProblemTest {
    public static void run() {
        String genome = "GATCAGCATAAGGGTCCCTGCAATGCATGACAAGCCTGCAGTTGTTTTAC";
        int pl = 4;
        int wl = 25;
        int count = 3;

        System.out.printf("K-mers in DNA genome with L size %s%n", ClumpFindingProblem.clumpFinding(genome, pl, wl, count));

        genome = "CGGACTCGACAGATGTGAAGAAATGTGAAGACTGAGTGAAGAGAAGAGGAAACACGACACGACATTGCGACATAATGTACGAATGTAATGTGCCTATGGC";
        pl = 5;
        wl = 75;
        count = 4;

        System.out.printf("K-mers in DNA genome with L size %s%n", ClumpFindingProblem.clumpFinding(genome, pl, wl, count));

        genome = "AAAACGTCGAAAAA";
        pl = 2;
        wl = 4;
        count = 2;

        System.out.printf("K-mers in DNA genome with L size %s%n", ClumpFindingProblem.clumpFinding(genome, pl, wl, count));
    }
}

class ClumpFindingProblem {
    public static ArrayList<String> clumpFinding(String text, int pl, int wl, int count) {

        ArrayList<String> temp;
        ArrayList<String> result = new ArrayList<>();
        String str;
        for (int i = 0; i < text.length() - wl + 1; i++) {
            str = text.substring(i, i + wl);
            temp = FrequentWordsProblem.frequentWords(str, pl, count);
            for (String s : temp) {
                if (!result.contains(s))
                    result.add(s);
            }
        }

        return result;
    }
}

