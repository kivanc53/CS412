package lectures.lecture1;


/*
    Counting words problem
        input = string pattern and longer string text
        output = number of times pattern occurs in text

    The goal is finding number of the given pattern in the text.
     */

public class Problem3 {
    public static void main(String[] args) {
        CountingProblemTest.run();
    }
}

class CountingProblemTest {
    public static void run() {

        String pattern = "ATA";
        String text = "CGATATATCCATAGATA";

        System.out.printf("Pattern %s is found %d times%n", pattern, Problems3.countingWordsProblem(pattern, text));

    }
}

class Problems3 {
    public static int countingWordsProblem(String pattern, String text) {
        int count = 0;
        for (int i = 0; i < text.length() - pattern.length() + 1; i++)
            if (pattern.equals(text.substring(i, i + pattern.length())))
                count++;

        return count;
    }
}