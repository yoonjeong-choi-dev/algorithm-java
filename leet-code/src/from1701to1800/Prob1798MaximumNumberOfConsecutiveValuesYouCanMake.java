package from1701to1800;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-number-of-consecutive-values-you-can-make/
public class Prob1798MaximumNumberOfConsecutiveValuesYouCanMake {
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);

        // 0 부터 시작
        int curValues = 0;
        for (int coin : coins) {
            if (coin > curValues + 1) {
                // 현재 코인 전까지 연속적으로 만들 수 없는 경우
                break;
            }

            // You can make some value x if you can choose some of your n coins such that their values sum up to x
            // : x == curValues+ coin
            curValues += coin;
        }

        return curValues + 1;
    }
}
