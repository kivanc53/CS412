package lectures.lecture1;


/*
     Pattern Matching Problem

        input = DNA Genome,
                pattern
        output = All starting positions in Genome where Pattern appears as a substring

    goal is try to find starting indexes of pattern in genome
 */
import java.util.ArrayList;

public class Problem7 {
    public static void main(String[] args) {
        PatternMatchingProblemTest.run();
    }
}

class PatternMatchingProblemTest {
    public static void run() {
        String pattern = "ATAT";
        String text = "GATATATGCATATACTT";
        System.out.printf("%s pattern %d times in this genome and indexes like these: %s%n",pattern, PatternMathcingProblem.patternMatching(pattern, text).size(), PatternMathcingProblem.patternMatching(pattern, text));
    }
}

class PatternMathcingProblem{
    public static ArrayList<Integer> patternMatching(String pattern, String text) {
        ArrayList<Integer> indexOfPattern = new ArrayList<>();
        for (int i = 0; i < text.length() - pattern.length() + 1; i++)
            if (pattern.equals(text.substring(i, i + pattern.length())))
                indexOfPattern.add(i);

        return indexOfPattern;
    }
}



