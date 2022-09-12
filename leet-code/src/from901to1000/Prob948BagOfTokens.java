package from901to1000;

import java.util.Arrays;

// https://leetcode.com/problems/bag-of-tokens/
public class Prob948BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int power) {
        // 작은 것으로 점수를 얻고, 큰 것으로 점수 대신 파워를 얻는다
        Arrays.sort(tokens);

        int score = 0, ans = 0;
        int left = 0, right = tokens.length - 1;
        while (left <= right) {
            if (tokens[left] <= power) {
                // 현재 점수를 얻을 수 있는 경우
                score++;
                power -= tokens[left++];

                // 점수 업데이트
                ans = Math.max(ans, score);
            } else if (score > 0) {
                // 점수를 잃고 파워를 얻을 수 있는 경우
                score--;
                power += tokens[right--];
            } else {
                // game over
                break;
            }
        }
        return ans;
    }
}
