package from1501to1600;

// https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
public class Prob1578MinimumTimeToMakeRopeColorful {
    public int minCost(String colors, int[] neededTime) {
        int numBalloons = colors.length();
        int ans = 0;

        int idx = 0, maxCost, curSum;
        char curColor;
        while (idx < numBalloons) {
            maxCost = curSum = neededTime[idx];
            curColor = colors.charAt(idx++);

            // 현재 풍선과 같은 색상인 풍선들 탐색
            while (idx < numBalloons && colors.charAt(idx) == curColor) {
                maxCost = Math.max(maxCost, neededTime[idx]);
                curSum += neededTime[idx];
                idx++;
            }

            // 가장 큰 비용인 풍선을 제외하고 모두 제거
            ans += curSum - maxCost;
        }
        return ans;
    }
}
