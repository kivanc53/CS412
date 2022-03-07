package lectures.lecture1;


/*
    Mininum skew problem

        input = a dna string genome
        output = minimum value of Skew[k] for genome
        Skew[k] = number of G - number C for the first k nucleotides of genome

 */


import java.util.ArrayList;

public class Problem8 {
    public static void main(String[] args) {
        MinSkewProblemTest.run();
    }
}

class MinSkewProblemTest{
    public static void run() {
        String genome = "CATGGGCATCGGCCATACGCC";
        System.out.printf("%d minimum value of Skew[k] for genome %s%n", MinSkewProblem.minSkew(genome), genome);

        genome = "CCTATCGGTGGATTAGCATGTCCCTGTACGTTTCGCCGCGAACTAGTTCACACGGCTTGATGGCAAATGGTTTTTCCGGCGACCGTAATCGTCCACCGAG";
        System.out.printf("%d minimum value of Skew[k] for genome %s%n", MinSkewProblem.minSkew(genome), genome);
    }
}

class  MinSkewProblem{
    public static int minSkew(String genome) {
        int countC = 0, countG = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < genome.length(); i++){
            if (genome.charAt(i) == 'C')
                countC++;
            else if (genome.charAt(i) == 'G')
                countG++;
            list.add(countG - countC);
        }

        return list.stream().min(Integer::compare).hashCode();
    }
}
