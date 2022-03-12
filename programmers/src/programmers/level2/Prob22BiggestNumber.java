package programmers.level2;


import java.util.Arrays;
import java.util.Comparator;

// // https://programmers.co.kr/learn/courses/30/lessons/42746
public class Prob22BiggestNumber {
    public String solution(int[] numbers) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // 두 문자열을 붙였을 때 더 큰 수가 나오도록 비교
                int left = Integer.parseInt(s1 + s2);
                int right = Integer.parseInt(s2 + s1);
                return right - left;
            }
        };

        int len = numbers.length;
        boolean isAllZero = true;

        // 숫자들을 붙이기 쉽게 하기 위해 문자열로 변환
        String[] strings = new String[len];
        for (int i = 0; i < len; i++) {
            strings[i] = String.valueOf(numbers[i]);
            if (numbers[i] != 0) isAllZero = false;
        }
        if (isAllZero) return "0";

        Arrays.sort(strings, comparator);


        StringBuilder answer = new StringBuilder();
        for (String s : strings) answer.append(s);
        return answer.toString();
    }

    public static void main(String[] args) {
        Prob22BiggestNumber sol = new Prob22BiggestNumber();

        int[][] numbers = {
                {6,10,2}, {3,30,34,5,9}
        };
        String[] ans = {"6210", "9534330"};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String mySol = sol.solution(numbers[i]);
            if (mySol.equals(ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
