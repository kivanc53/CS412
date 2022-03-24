package homeworks.hw2;

import java.util.*;

public class HW2_Kivanc_Yilmaz_S012733 {
    public static void main(String[] args) {
        Tests.medianStringProblemTest();
        Tests.greedyMotifSearchProblemTest();
        Tests.randomizedMotifSearchTest();
        Tests.gibbsSamplerTest();
    }
}

class Tests {
    public static void medianStringProblemTest() {
        String[] setOfStrings = new String[]{"TTACCTTAAC",
                "GATATCTGTC",
                "ACGGCGTTCG",
                "CCCTAAAGAG",
                "CGTCAGAGGT"};
        int k = 3;
        System.out.printf("**********************%nQUESTION1%nA k-mer minimizing distance d(k-mer, Dna) among all k-mers is %s%n**********************%n", Problems.medianStringProblem(setOfStrings, k));
    }

    public static void greedyMotifSearchProblemTest() {
        String[] setOfStrings = new String[]{"GGCGTTCAGGCA",
                "AAGAATCAGTCA",
                "CAAGGAGTTCGC",
                "CACGTCAATCAC",
                "CAATAATATTCG"};
        int k = 3, t = 5;
        System.out.printf("**********************%nQUESTION2%nA collection of strings BestMotifs resulting from %s%n**********************%n", Problems.greedyMotifSearch(setOfStrings, k, t));
    }

    public static void randomizedMotifSearchTest() {
        String[] setOfStrings = new String[]{"CGCCCCTCTCGGGGGTGTTCAGTAAACGGCCA", "GGGCGAGGTATGTGTAAGTGCCAAGGTGCCAG", "TAGTACCGAGACCGAAAGAAGTATACAGGCGT", "TAGATCAAGTTTCAGGTGCACGTCGGTGAACC", "AATCCACCAGCTCCACGTGCAATGTTGGCCTA"};
        int k = 8, t = 5;
        System.out.printf("**********************%nQUESTION3%nA collection of strings BestMotifs resulting from %s%n**********************%n", Problems.randomizedMotifSearch(setOfStrings, k, t));
    }

    public static void gibbsSamplerTest() {
        String[] setOfStrings = new String[]{"CGCCCCTCTCGGGGGTGTTCAGTAACCGGCCA",
                "GGGCGAGGTATGTGTAAGTGCCAAGGTGCCAG",
                "TAGTACCGAGACCGAAAGAAGTATACAGGCGT",
                "TAGATCAAGTTTCAGGTGCACGTCGGTGAACC",
                "AATCCACCAGCTCCACGTGCAATGTTGGCCTA"};
        int k = 8, t = 5;
        System.out.printf("**********************%nQUESTION4%nA collection of strings BestMotifs resulting from %s%n**********************%n", Problems.gibbsSamplerProblem(setOfStrings, k, t));
    }
}

class Problems {
    public static ArrayList<String> createAllPossibleKmers(int k) {
        ArrayList<String> list = new ArrayList<>();
        Random random = new Random();
        String dna = "ATGC", temp;
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
            min = Math.min(min, medianStringProblemWithSetOfStrings(allPossibleKmers.get(i), setOfStrings));
            if (temp != min) {
                index = i;
            }
            temp = min;
        }
        return allPossibleKmers.get(index);
    }

    public static int medianStringProblemWithSetOfStrings(String pattern, String[] strings) {
        int min = 0;
        for (String str : strings) {
            min += medianStringProblem(pattern, str);
        }
        return min;
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

    public static int converterSymbolNumber(String symbol) {
        return switch (symbol) {
            case "A" -> 0;
            case "C" -> 1;
            case "G" -> 2;
            case "T" -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + symbol);
        };
    }

    public static String converterNumberSymbol(int index) {
        return switch (index) {
            case 0 -> "A";
            case 1 -> "C";
            case 2 -> "G";
            case 3 -> "T";
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public static int hammingDistance(String str, String str2) {
        int count = 0;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) != str2.charAt(i))
                count++;
        return count;
    }

    public static ArrayList<String> window(String str, int k) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length() - k + 1; i++) {
            list.add(str.substring(i, i + k));
        }
        return list;
    }

    public static int findMax(String[] args) {
        int min = Integer.MIN_VALUE;
        for (String arg : args) {
            try {
                min = Math.max(Integer.parseInt(arg), min);
            } catch (Exception e) {
                System.out.println("error");
            }
        }

        return min;
    }

    public static int findMax(ArrayList<String> list) {
        int min = Integer.MIN_VALUE;
        for (int i = 1; i < list.size(); i += 2) {
            try {
                min = Math.max(Integer.parseInt(list.get(i)), min);
            } catch (Exception e) {
                System.out.println("error");
            }
        }

        return min;
    }

    public static int findIndex(String[] args, int value) {
        for (int i = 0; i < args.length; i++)
            if (Integer.parseInt(args[i]) == value)
                return i;
        return -1;
    }

    public static String profileMostProbable(String text, int k, String[][] profile) {
        String probable = "", s = "ACGT", finalWord = "";
        ArrayList<String> list = new ArrayList();
        int index = 1;
        String[][] letter = new String[k][4];


        for (int i = 0; i < k; i++) {
            for (int j = 0; j < s.length(); j++) {
                letter[i][j] = String.valueOf(profile[j][1].charAt(3 * i + 1));
            }
        }

        for (String[] strings : letter) {
            int max = findMax(strings);
            probable += converterNumberSymbol(findIndex(strings, max));
        }

        for (String str : window(text, k)) {
            for (int m = 0; m < str.length(); m++) {
                int val = converterSymbolNumber(String.valueOf(str.charAt(m)));
                index *= Integer.parseInt(letter[m][val]);
            }
            list.add(str);
            list.add(String.valueOf(index));
            index = 1;
        }
        for (int i = 0; i < list.size(); i += 2) {
            if (Integer.parseInt(list.get(i + 1)) == findMax(list)) {
                finalWord = list.get(i);
                break;
            }
        }
        return finalWord;
    }

    public static String findConsensus(ArrayList<String> motifs) {
        String consensus = "";
        for (int i = 0; i < motifs.get(0).length(); i++) {
            int countA = 0, countC = 0, countG = 0, countT = 0;
            for (String str : motifs) {
                if (!str.equals(""))
                    switch (str.charAt(i)) {
                        case 'A' -> countA += 1;
                        case 'C' -> countC += 1;
                        case 'G' -> countG += 1;
                        case 'T' -> countT += 1;
                        default -> throw new IllegalStateException("Unexpected value: " + str.charAt(i));
                    }
            }
            if (countA >= Math.max(Math.max(countC, countG), countT))
                consensus += "A";
            else if (countC >= Math.max(Math.max(countA, countG), countT))
                consensus += "C";
            else if (countG >= Math.max(Math.max(countC, countA), countT))
                consensus += "G";
            else consensus += "T";
        }
        return consensus;
    }

    public static String[][] profileMatrix(ArrayList<String> motifs) {
        String[][] profile = new String[4][2];
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> c = new ArrayList<>();
        ArrayList<String> g = new ArrayList<>();
        ArrayList<String> t = new ArrayList<>();
        for (int i = 0; i < motifs.get(0).length(); i++) {
            int countA = 0, countC = 0, countG = 0, countT = 0;
            for (String str : motifs) {
                if (!str.equals(""))
                    switch (str.charAt(i)) {
                        case 'A' -> countA += 1;
                        case 'C' -> countC += 1;
                        case 'G' -> countG += 1;
                        case 'T' -> countT += 1;

                    }
            }
            a.add(String.valueOf(countA));
            c.add(String.valueOf(countC));
            g.add(String.valueOf(countG));
            t.add(String.valueOf(countT));
        }
        profile[0][0] = "A";
        profile[0][1] = String.valueOf(a);
        profile[1][0] = "C";
        profile[1][1] = String.valueOf(c);
        profile[2][0] = "G";
        profile[2][1] = String.valueOf(g);
        profile[3][0] = "T";
        profile[3][1] = String.valueOf(t);

        return profile;
    }

    public static int score(ArrayList<String> motifs) {
        String consensus = findConsensus(motifs);
        int score = 0;
        for (String str : motifs) {
            score += hammingDistance(consensus, str);
        }
        return score;
    }

    public static ArrayList<String> greedyMotifSearch(String[] setOfStrings, int k, int t) {
        ArrayList<String> bestMotifs = new ArrayList<>();
        String[][] profile = new String[4][2];
        int bestScore = Integer.MAX_VALUE;
        for (String str : setOfStrings) {
            bestMotifs.add(str.substring(0, k));
        }
        String base = setOfStrings[0];
        for (String str : window(base, k)) {
            ArrayList<String> newMotifs = new ArrayList<>();
            newMotifs.add(str);
            for (int i = 1; i < t; i++) {
                profile = profileMatrix(newMotifs);
                String probable = profileMostProbable(setOfStrings[i], k, profile);
                newMotifs.add(probable);


            }

            if (score(newMotifs) < bestScore) {
                bestScore = score(newMotifs);
                bestMotifs = newMotifs;
            }
        }
        System.out.println("A Profile-most probable motif matrix in Dna is " + Arrays.deepToString(profile));
        return bestMotifs;
    }

    public static int pr(String str, String[][] profile) {
        int p = 1;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < 4; j++) {
                if (!(str.charAt(i) == ' '))
                    switch (str.charAt(i)) {
                        case 'A' -> p *= Double.parseDouble(profile[j][1].substring(20 * i + 1, 20 * i + 19));
                        case 'C' -> p *= Double.parseDouble(profile[j][1].substring(20 * i + 1, 20 * i + 19));
                        case 'G' -> p *= Double.parseDouble(profile[j][1].substring(20 * i + 1, 20 * i + 19));
                        case 'T' -> p *= Double.parseDouble(profile[j][1].substring(20 * i + 1, 20 * i + 19));
                    }
            }
        }
        return p;
    }

    public static String profileMostProbablePattern(String str, int k, String[][] profile) {
        int max = -1, n = str.length();
        String probPattern = "";
        for (int i = 0; i < n - k + 1; i++) {
            String pattern = str.substring(i, i + k);
            int prob = pr(pattern, profile);
            if (prob > max) {
                max = prob;
                probPattern = pattern;
            }
        }
        if (max == -1)
            return str.substring(0, k);
        else
            return probPattern;
    }

    public static String[][] countWithPseudocounts(ArrayList<String> motifs) {
        int k = motifs.get(0).length();
        String[][] count = new String[4][2];
        count[0][0] = "A";
        count[1][0] = "C";
        count[2][0] = "G";
        count[3][0] = "T";

        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> c = new ArrayList<>();
        ArrayList<String> g = new ArrayList<>();
        ArrayList<String> t = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int countA = 1, countC = 1, countG = 1, countT = 1;
            for (String str : motifs) {
                if (!str.equals(""))
                    switch (str.charAt(i)) {
                        case 'A' -> countA += 1;
                        case 'C' -> countC += 1;
                        case 'G' -> countG += 1;
                        case 'T' -> countT += 1;
                    }
            }
            a.add(String.valueOf(countA));
            c.add(String.valueOf(countC));
            g.add(String.valueOf(countG));
            t.add(String.valueOf(countT));
        }

        count[0][1] = String.valueOf(a);
        count[1][1] = String.valueOf(c);
        count[2][1] = String.valueOf(g);
        count[3][1] = String.valueOf(t);

        return count;
    }

    public static String[][] profileWithPseudocounts(ArrayList<String> motifs) {
        int m = motifs.size();
        String[][] profile = new String[4][2];
        String[][] count = countWithPseudocounts(motifs);
        for (int i = 0; i < 4; i++)
            profile[i][0] = count[i][0];

        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> c = new ArrayList<>();
        ArrayList<String> g = new ArrayList<>();
        ArrayList<String> t = new ArrayList<>();


        for (int i = 0; i < 4; i++) {
            String str = count[i][1];
            for (int j = 0; j < str.length(); j += 3) {
                String e = String.valueOf(Double.parseDouble(String.valueOf(str.charAt(j + 1))) / (m + 4));
                switch (i) {
                    case 0 -> a.add(e);
                    case 1 -> c.add(e);
                    case 2 -> g.add(e);
                    case 3 -> t.add(e);
                }
            }
        }

        profile[0][1] = String.valueOf(a);
        profile[1][1] = String.valueOf(c);
        profile[2][1] = String.valueOf(g);
        profile[3][1] = String.valueOf(t);

        return profile;
    }

    public static int score2(ArrayList<String> motifs) {
        int count = 0;
        String consensus = findConsensus(motifs);       //?
        for (String str : motifs) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != consensus.charAt(i))
                    count++;
            }
        }
        return count;
    }

    public static ArrayList<String> motifs(String[][] profile, String[] setOfStrings, int k) {
        ArrayList<String> probKmer = new ArrayList<>();
        for (String str : setOfStrings) {
            probKmer.add(profileMostProbablePattern(str, k, profile));
        }
        return probKmer;
    }

    public static ArrayList<String> randomMotifs(String[] setOfStrings, int k, int t) {
        Random random = new Random();
        int n = setOfStrings[0].length();
        ArrayList<String> randomMotifs = new ArrayList<>();
        for (String str : setOfStrings) {
            int start = random.nextInt(n - k);
            randomMotifs.add(str.substring(start, start + k));
        }
        return randomMotifs;
    }

    public static ArrayList<String> randomizedMotifSearch(String[] setOfStrings, int k, int t) {
        ArrayList<String> randomMotifs = randomMotifs(setOfStrings, k, t);
        ArrayList<String> bestMotifs = randomMotifs;
        while (true) {
            String[][] profile = profileWithPseudocounts(randomMotifs);
            ArrayList<String> checkRandom = motifs(profile, setOfStrings, k);
            if (score2(checkRandom) < score2(bestMotifs))
                bestMotifs = checkRandom;
            else
                return bestMotifs;
        }
    }

    public static ArrayList<String> gibbsSamplerProblem(String[] setOfStrings, int k, int t) {
        ArrayList<String> randomMotifs = randomMotifs(setOfStrings, k, t);
        ArrayList<String> bestMotifs = randomMotifs;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int j = random.nextInt(t);
            String[][] profile = profileWithPseudocounts(randomMotifs);
            ArrayList<String> checkRandom = motifs(profile, setOfStrings, k);
            if (score2(checkRandom) > score2(bestMotifs)) {
                bestMotifs = checkRandom;
            } else {
                return bestMotifs;
            }
        }
        return bestMotifs;
    }

}
