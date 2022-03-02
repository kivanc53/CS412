package lectures.lecture1;


/*
    Reverse complement problem

        input = DNA string text
        output = reverse complement of text

        Reverse complement is like this:
            DNA =           AGTCGCATAGT
            Comp =          TCAGCGTATCA
            Reverse comp =  ACTATGCGACT
 */

public class Problem5 {
    public static void main(String[] args) {
        ReverseComplementProblemTest.run();
    }
}

class ReverseComplementProblemTest {
    public static void run() {
        String text = "AGTCGCATAGT";
        System.out.printf("Reverse Complement of %s is %s%n", text, ReverseComplementProblem.reverseComplement(text));
    }
}


class ReverseComplementProblem {
    public static String reverseComplement(String text) {
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            newText = dnaComplement(ch, newText);
        }
        newText.reverse();
        return newText.toString();
    }

    public static StringBuilder dnaComplement(char ch, StringBuilder str) {
        if (ch == 'A')
            str.append("T");
        else if (ch == 'G')
            str.append("C");
        else if (ch == 'C')
            str.append("G");
        else
            str.append("A");
        return str;
    }
}