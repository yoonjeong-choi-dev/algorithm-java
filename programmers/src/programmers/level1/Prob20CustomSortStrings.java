package programmers.level1;

import java.util.Arrays;
import java.util.Comparator;


// https://programmers.co.kr/learn/courses/30/lessons/12915
public class Prob20CustomSortStrings {
    public String[] solution(String[] strings, int n) {

        Comparator<String> comp = makeComparator(n);

        Arrays.sort(strings, comp);
        return strings;
    }

    Comparator<String> makeComparator(int n) {
        // Comparator 메서드 정의에 대해서 오름차순
        // 움스를 내뱉은 경우, 첫번째 인자가 더 먼저 온다는 뜻
        return new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {

                if (s1.charAt(n) < s2.charAt(n)) {
                    // s1이 s2보다 먼저 와야함
                    return -1;
                } else if (s1.charAt(n) > s2.charAt(n)) {
                    // s2이 s1보다 먼저 와야함
                    return 1;
                } else {
                    // s1 < s2 : s1 이 먼저 와야함
                    return s1.compareTo(s2);
                }
            }
        };
    }


    public static void main(String[] args) {
        Prob20CustomSortStrings sol = new Prob20CustomSortStrings();

        String[][] strings = {
                {"sun", "bed", "car"},
                {"abce", "abcd", "cdx"}
        };

        int[] n = {1, 2};

        String[][] ans = {
                {"car", "bed", "sun"},
                {"abcd", "abce", "cdx"}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            String[] mySol = sol.solution(strings[i], n[i]);

            if (checkArrayStr(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }

    }

    static boolean checkArrayStr(String[] arr1, String[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }
}
