package from1to100;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Prob3LongestUniqueSubstring {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();

        if (len == 0) return 0;

        // 부분 문자열들의 (문자, 인덱스) 저장
        // 문자열의 구성요소들은 아스키 문자이므로 7비트(128)로 표현 가능
        int[] charAt = new int[128];
        for (int i = 0; i < 128; i++) {
            charAt[i] = -1;
        }

        // 문자열의 길이는 최소 1이므로 정답은 최소 1
        int ans = 1;

        // 후보 부분 문자열의 시작 인덱스 저장
        int startIdx = 0;
        char curChar;
        for (int i = 0; i < len; i++) {
            curChar = s.charAt(i);

            // 현재 문자가 현재 문자열 내에 이미 저장되어 있는 경우
            if (charAt[curChar] != -1 && charAt[curChar] >= startIdx) {
                // 현재 문자 이전까지의 문자열 길이 계산
                ans = Math.max(ans, i - startIdx);

                // 현재 문자가 이전에 저장된 위치의 다음 문자부터 시작하는 부분 문자열 탐색
                startIdx = charAt[curChar] + 1;
            }

            // 현재 문자 정보 저장
            charAt[curChar] = i;
        }

        // 마지막 문자를 포함하는 문자열 길이 계산
        ans = Math.max(ans, len - startIdx);

        return ans;
    }

    public static void main(String[] args) {
        Prob3LongestUniqueSubstring sol = new Prob3LongestUniqueSubstring();

        String[] strings = {"abcabcbb", "bbbbb", "pwwkew", "au", "tmmzuxt"};

        int[] ans = {3, 1, 3, 2, 5};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.lengthOfLongestSubstring(strings[i]);
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
