package from1to100;

// https://leetcode.com/problems/edit-distance/
// Ref : Problem 1143
public class Prob72EditDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();

        // dp[i][j] : word1[0:i], word2[0:j] 에 대한 부분 문제
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 부분 문제의 각 문자열의 길이 차이만큼 초기화
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int i = 0; i <= len2; i++) dp[0][i] = i;


        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    // 현재 문자가 다른 경우 => 양 하위 문제들의 정답 최소값 + 1
                    dp[i + 1][j + 1] = 1 + Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]));
                }
            }
        }

        for (int[] a : dp) {
            for (int t : a) {
                System.out.printf("%d ", t);
            }
            System.out.println();
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Prob72EditDistance sol = new Prob72EditDistance();
        sol.minDistance("horse", "ros");
    }
}
