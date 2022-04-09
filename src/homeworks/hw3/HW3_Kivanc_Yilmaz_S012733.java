package homeworks.hw3;

import java.util.*;

public class HW3_Kivanc_Yilmaz_S012733 {
    public static void main(String[] args) {
        Tests.stringCompositionProblemTest();
        Tests.overlapGraphProblemTest();
        Tests.deBruijnGraphProblemTest();
        System.out.println("Question 4 in python file");
    }
}

class Tests {
    public static void stringCompositionProblemTest() {
        int k = 3;
        String str = "TATGGGGTGC";
        System.out.printf("**********%nQuestion 1%nString composition of %s is %s%n**********%n", str, Problems.stringCompositionProblem(str, k));
    }

    public static void overlapGraphProblemTest() {
        String[] setOfStrings = new String[]{"ATGCG",
                "GCATG",
                "CATGC",
                "AGGCA",
                "GGCAT"};

        System.out.printf("**********%nQuestion 2%nOverlap graph of %s is %s%n**********%n", Arrays.toString(setOfStrings), Problems.overlapGraphProblem(setOfStrings));
    }

    public static void deBruijnGraphProblemTest() {
        String[] setOfStrings = new String[]{"GAGG",
                "CAGG",
                "GGGG",
                "GGGA",
                "CAGG",
                "AGGG",
                "GGAG"};

        System.out.printf("**********%nQuestion 3%nDe Bruijn graph of %s is %s%n**********%n", Arrays.toString(setOfStrings), Problems.deBruijnGraphProblem(setOfStrings));
    }

}

class Problems {
    public static ArrayList<String> stringCompositionProblem(String text, int k) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < text.length() - k + 1; i++) {
            list.add(text.substring(i, i + k));
        }
        Collections.sort(list);
        return list;
    }

    //suffix(last k - 1 letter) = prefix(first k - 1 letter)
    public static ArrayList<String> overlapGraphProblem(String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        int length = strings[0].length();
        for (String str1 : strings) {
            for (String str2 : strings) {
                if (str1.substring(length - (length - 1), length).equals(str2.substring(0, length - 1)))
                    list.add(str1 + " -> " + str2);
            }
        }
        return list;
    }

    public static Map<String, String> deBruijnGraphProblem(String[] strings) {
        Map<String, String> listMap = new HashMap<>();
        int length = strings[0].length();
        for (String str : strings) {
            String suffix = str.substring(length - (length - 1), length);
            String prefix = str.substring(0, length - 1);
            if (!listMap.containsKey(str.substring(0, length - 1))) {
                listMap.put(prefix, suffix);
            } else {
                listMap.put(prefix, listMap.get(prefix) + ", " + suffix);
            }
        }
        return listMap;
    }

}
