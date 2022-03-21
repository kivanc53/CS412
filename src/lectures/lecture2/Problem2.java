package lectures.lecture2;


/*
    Implanted motif problem
        input: set of strings DNA, int k(motif length), int d(maximal number of mismatches in a motif)
        output: all (k, d)-motifs in DNA
    goal is finding (k, d)-motifs in a set of strings.
 */

public class Problem2 {
    public static void main(String[] args) {
        ImplantedMotifsProblemTest.run();
    }
}

class ImplantedMotifsProblemTest {
    public static void run() {
        String[] strings = new String[]{"atgaccgggatactgatagaagaaaggttgggggcgtacacattagataaacgtatgaagtacgttagactcggcgccgccg",
                "acccctattttttgagcagatttagtgacctggaaaaaaaatttgagtacaaaacttttccgaatacaataaaacggcggga",
                "tgagtatccctgggatgacttaaaataatggagtggtgctctcccgatttttgaatatgtaggatcattcgccagggtccga",
                "gctgagaattggatgcaaaaaaagggattgtccacgcaatcgcgaaccaacgcggacccaaaggcaagaccgataaaggaga",
                "tcccttttgcggtaatgtgccgggaggctggttacgtagggaagccctaacggacttaatataataaaggaagggcttatag",
                "gtcaatcatgttcttgtgaatggatttaacaataagggctgggaccgcttggcgcacccaaattcagtgtgggcgagcgcaa",
                "cggttttggcccttgttagaggcccccgtataaacaaggagggccaattatgagagagctaatctatcgcgtgcgtgttcat",
                "aacttgagttaaaaaatagggagccctggggcacatacaagaggagtcttccttatcagttaatgctgtatgacactatgta",
                "ttggcccattggctaaaagcccaacttgacaaatggaagatagaatccttgcatactaaaaaggagcggaccgaaagggaag",
                "ctggtgagcaacgacagattcttacgtgcattagctcgcttccggggatctaatagcacgaagcttactaaaaaggagcgga"};
        ImplantedMotifsProblem.implamentedMotifsProblem(strings, 15, 4);
    }
}

class ImplantedMotifsProblem {
    public static void implamentedMotifsProblem(String[] strings, int k, int d) {

        for (int i = 0; i < strings[0].length() - k + 1; i++) {
            String pattern = strings[0].substring(i, i + k);
            countingWordsWithMismatch(pattern, strings[0], d);

        }


    }

    public static void countingWordsWithMismatch(String pattern, String text, int d) {

        int count = 0, mismatchCount = 0;
        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            int j = 0;
            for (char c : pattern.toCharArray()) {
                if (c != text.substring(i, i + pattern.length()).charAt(j))
                    mismatchCount++;
                j++;
            }

            if (mismatchCount == d)
                count++;

        }
    }
}
