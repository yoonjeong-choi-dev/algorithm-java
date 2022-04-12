package from1301to1400;

// https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
public class Prob1309DecryptStringFromAlphabetToInteger {
    public String freqAlphabets(String s) {
        int len = s.length();
        char digitStart = 'a' - 1;

        StringBuilder ans = new StringBuilder(len);
        int startIdx = 0, curIdx = 0;
        int number;
        while (startIdx < len) {
            // # 문자가 올때까지 탐색
            while (curIdx < len && s.charAt(curIdx) != '#') {
                curIdx++;
            }

            // 마지막이 #이 아닌 경우
            if(curIdx == len) break;

            // 파싱
            while (startIdx < curIdx - 2) {
                ans.append((char) (digitStart + s.charAt(startIdx++) -'0'));
            }

            number = (s.charAt(startIdx++) - '0') * 10;
            number += (s.charAt(startIdx++) - '0');
            ans.append((char) (digitStart + number));

            // 다음 탐색
            curIdx++;
            startIdx++;
        }

        while(startIdx < len) {
            ans.append((char) (digitStart + s.charAt(startIdx++) -'0'));
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        Prob1309DecryptStringFromAlphabetToInteger sol = new Prob1309DecryptStringFromAlphabetToInteger();

        String[] strings = {"10#11#12", "1326#"};

        String[] ans = {"jkab", "acz"};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String mySol = sol.freqAlphabets(strings[i]);
            if (mySol.equals(ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
