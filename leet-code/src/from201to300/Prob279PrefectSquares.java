package from201to300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/perfect-squares/
// Ref : Problem 322 => 동전 종류가 제곱수
public class Prob279PrefectSquares {
    public int numSquares(int n) {
        // n 이하의 제곱수 저장
        int sqrt = (int) Math.sqrt(n);

        // 작은 제곱수부터 고려
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i <= sqrt; i++) {
            squares.add(i * i);
        }

        int[] dp = new int[n + 1];
        for (int square : squares) dp[square] = 1;

        // 각 숫자에 대해 해당 제곱수를 더하는 경우의 개수
        for (int curNum = 1; curNum <= n; curNum++) {
            if (dp[curNum] != 0) continue;

            for (int square : squares) {
                if (curNum < square) break;

                // square 추가하기
                if (dp[curNum - square] != 0) {
                    if (dp[curNum] == 0) {
                        // 처음 찾은 정답인 경우
                        dp[curNum] = dp[curNum - square] + 1;
                    } else {
                        // 두번째 이후 찾은 정답
                        dp[curNum] = Math.min(dp[curNum], dp[curNum - square] + 1);
                    }
                }
            }
        }

        return dp[n];
    }
}
