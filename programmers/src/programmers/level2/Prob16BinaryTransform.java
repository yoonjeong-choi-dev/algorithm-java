package programmers.level2;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/70129
public class Prob16BinaryTransform {
    public int[] solution(String s) {
        StringBuilder binary = new StringBuilder();

        int numDelete = 0;
        int numStep = 0;
        int prevLen;
        while (!s.equals("1")) {
            prevLen = s.length();
            s = s.replaceAll("0", "");
            numDelete += prevLen - s.length();

            int len = s.length();
            binary.setLength(0);
            while (len != 0) {
                // 오른쪽부터 2진수로 표현된 문자열 저장
                binary.insert(0, len % 2);
                len /= 2;
            }
            s = binary.toString();
            numStep++;
        }

        return new int[] {numStep, numDelete};
    }


    public static void main(String[] args) {
        Prob16BinaryTransform sol = new Prob16BinaryTransform();

        String[] s = {"110010101001", "01110", "1111111"};

        int[][] ans = {{3, 8}, {3, 3}, {4, 1}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int[] mySol = sol.solution(s[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
