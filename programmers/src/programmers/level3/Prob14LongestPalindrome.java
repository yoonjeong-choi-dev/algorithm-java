package programmers.level3;

// https://programmers.co.kr/learn/courses/30/lessons/12904
public class Prob14LongestPalindrome {
    private String string;

    public int solution(String s) {
        string = s;

        // 팰린드롬이 가능한 최대 길이부터 탐색
        int totalLen = s.length();
        for (int len = totalLen; len >= 2; len--) {
            // 시작 지점 설정
            for (int start = 0; start < totalLen; start++) {
                // 시작 지점에서 현재 후부 길이의 팰린드롬을 만들지 못하는 경우 pass
                if (start + len - 1 >= totalLen) continue;

                // [start, start + len -1] 이 팰린드롬인지 확인
                if(isPalindrome(start, len)) return len;
            }
        }

        // 팰린드롬의 최소길이는 1
        return 1;
    }

    private boolean isPalindrome(int start, int len) {
        // 팰린드롬의 마지막 인덱스
        int end = start + len - 1;

        // 검사해야하는 길이
        int testNum = len / 2;

        for (int i = 0; i <= testNum; i++) {
            if (string.charAt(i + start) != string.charAt(end - i)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Prob14LongestPalindrome sol = new Prob14LongestPalindrome();

        String[] strings = {"abcdcba", "abacde"};

        int[] ans = {7, 3};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(strings[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
