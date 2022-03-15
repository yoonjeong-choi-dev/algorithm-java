package programmers.level2;

public class Prob33KakaoStringCompression {
    public int solution(String s) {
        int strLen = s.length();
        int answer = strLen;

        // 압축할 길이에 대해서 압축 진행
        for (int len = 1; len <= strLen / 2; len++) {
            answer = Math.min(answer, compress(s, len, 0));
        }

        return answer;
    }

    private int compress(String s, int len, int curIdx) {
        // 남은 문자에 대해서 압축을 못하는 경우 그대로 반환
        if (curIdx + len >= s.length()) {
            return s.length() - curIdx;
        }

        int num = 1;

        for (int i = curIdx + len; i <= s.length() - len; i += len) {
            boolean isContained = true;
            for (int j = 0; j < len; j++) {
                if (s.charAt(i + j) != s.charAt(curIdx + j)) {
                    isContained = false;
                    break;
                }
            }

            if (isContained) num++;
            else break;
        }


        // 압축을 못하는 경우는 숫자를 붙이지 않는다
        int ans = num == 1 ? len : String.valueOf(num).length() + len;
        return ans + compress(s, len, curIdx + len * num);
    }


    public static void main(String[] args) {
        Prob33KakaoStringCompression sol = new Prob33KakaoStringCompression();

        String[] s = {
                "aabbaccc",
                "ababcdcdababcdcd",
                "abcabcdede",
                "abcabcabcabcdededededede",
                "xababcdcdababcdcd"
        };

        int[] ans = {7, 9, 8, 14, 17};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(s[i]);
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
