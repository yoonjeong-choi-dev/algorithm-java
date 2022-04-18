package from1to100;

import java.util.Arrays;

// https://leetcode.com/problems/multiply-strings/
public class Prob43MultiplyStrings {
    public String multiply(String num1, String num2) {
        //return mySolutionWithDigitLoop(num1, num2);
        return solutionDigitWisely(num1, num2);
    }

    // O(min(N,M) * (N+M) ) solution : 정답의 자리수를 기준으로 탐색하는 방법
    private String mySolutionWithDigitLoop(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int len1 = num1.length(), len2 = num2.length();

        // num1 <= pow(10, len1), num2 <= pow(10, len2)
        // => num1 * num2 < pow(10, len1+len2) => 전체 자리수는 len1 + len2  이하
        // ret[i] : pow(10, i) 자리수
        int[] ret = new int[len1 + len2];

        int sum;
        int c1, c2;
        int start, end, idx;
        for (int digit = 0; digit < ret.length; digit++) {
            sum = ret[digit];

            // num1 의 i 및 num2의 (digit-i) 인덱스 곱
            // => i <= digit, digit - i < len2
            start = Math.max(0, digit - len2 + 1);
            end = Math.min(digit, len1 - 1);

            for (idx = start; idx <= end; idx++) {
                c1 = num1.charAt(len1 - 1 - idx) - '0';
                c2 = num2.charAt(len2 - 1 - digit + idx) - '0';

                sum += c1 * c2;
            }

            // 받아 올림 처리
            idx = digit;
            ret[idx] = 0;
            while (sum != 0) {
                // 받아 올림 시, 이전 자리수에서 처리한 받아올림 정보에 현재 받아올림 정보를 더해야 함
                ret[idx++] += sum % 10;
                sum /= 10;
            }

        }

        int lastIdx = ret.length - 1;
        while (lastIdx >= 0 && ret[lastIdx] == 0) lastIdx--;
        if (lastIdx < 0) return "0";

        StringBuilder ans = new StringBuilder(lastIdx + 1);
        for (int i = lastIdx; i >= 0; i--) {
            ans.append((char) ('0' + ret[i]));
        }
        return ans.toString();
    }

    // O(N*M) : 두 문자열의 각 요소별 탐색
    private String solutionDigitWisely(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int len1 = num1.length(), len2 = num2.length();

        // num1 <= pow(10, len1), num2 <= pow(10, len2)
        // => num1 * num2 < pow(10, len1+len2) => 전체 자리수는 len1 + len2  이하
        // ret[i] : pow(10, i) 자리수
        int[] ret = new int[len1 + len2];

        int curPos;
        int c1, c2;
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                c1 = num1.charAt(i) - '0';
                c2 = num2.charAt(j) - '0';

                curPos = len1 - 1 - i + len2 - 1 - j;

                // ret[curPos] <= 90(9+9*9)
                // curPos 는 반복문에 의해서 0 -> 1 -> 2 -> 3 .. 순서로 반복하기 때문에 받아올려진 숫자는 최대 9
                ret[curPos] += c1 * c2;
                if (ret[curPos] > 9) {
                    ret[curPos + 1] += ret[curPos] / 10;
                    ret[curPos] %= 10;
                }
            }
        }

        int lastIdx = ret.length - 1;
        while (lastIdx >= 0 && ret[lastIdx] == 0) lastIdx--;
        if (lastIdx < 0) return "0";

        StringBuilder ans = new StringBuilder(lastIdx + 1);
        for (int i = lastIdx; i >= 0; i--) {
            ans.append((char) ('0' + ret[i]));
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        Prob43MultiplyStrings sol = new Prob43MultiplyStrings();
        String[] nums1 = {"2", "123", "9999", "1111", "1"};
        String[] nums2 = {"3", "456", "999", "1234", "1234"};

        String[] ans = {"6", "56088", "9989001", "1370974", "1234"};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String mySol = sol.multiply(nums1[i], nums2[i]);
            if (mySol.equals(ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }

    }
}