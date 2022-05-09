package homeworks.hw5;

import java.util.Arrays;

public class HW5_Kivanc_Yilmaz_S012733 {
    public static void main(String[] args) {
        Tests.longestCommonSubsequenceTest();
        System.out.println("Question 2 in python file");
        Tests.numberOfBreakpointsTest();
    }
}

class Tests {

    public static void longestCommonSubsequenceTest() {
        String str1 = "AACCTTGG";
        String str2 = "ACACTGTGA";

        System.out.printf("**********%nQuestion 1%nA longest common subsequence of %s and %s is %s%n**********%n", str1, str2, Problems.longestCommonSubsequenceProblem(str1, str2));
    }

    public static void numberOfBreakpointsTest() {
        int[] array = new int[]{3, 4, 5, -12, -8, -7, -6, 1, 2, 10, 9, -11, 13, 14};
        System.out.printf("**********%nQuestion 3%nNumber of breakpoints in permutation %s is %d%n**********%n", Arrays.toString(array), Problems.numberOfBreakpointsProblem(array));
    }
}

class Problems {
    public static String longestCommonSubsequenceProblem(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0)
                    matrix[i][j] = 0;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i - 1][j],
                            matrix[i][j - 1]);
            }
        }

        int index = matrix[str1.length()][str2.length()];

        char[] array = new char[index + 1];
        array[index] = '\u0000';
        int i = str1.length();
        int j = str2.length();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                array[index - 1] = str1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else {
                if (matrix[i - 1][j] > matrix[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return Arrays.toString(Arrays.copyOfRange(array, 0, array.length - 1));
    }

    public static int numberOfBreakpointsProblem(int[] array) {
        int count = 0;
        boolean breakCheck = false;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > 0) {
                breakCheck = array[i] + 1 == array[i + 1];
            } else {
                breakCheck = array[i] + 1 == array[i + 1];
            }
            if (!breakCheck)
                count++;
        }
        if (breakCheck)
            count++;

        return count;

    }
}
