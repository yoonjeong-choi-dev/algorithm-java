package from101to200;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class Prob122BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        // 저점에서 사서 고점에서 판다
        // => local min 에서 사서 local max 에서 파는 것을 반복
        int numDays = prices.length - 1;
        int ans = 0;

        int localMin, localMax;
        int curDay = 0;
        while (curDay < numDays) {
            // 저점 찾기
            while (curDay < numDays && prices[curDay] >= prices[curDay + 1]) curDay++;
            localMin = prices[curDay];

            // 고점 찾기
            while (curDay < numDays && prices[curDay] <= prices[curDay + 1]) curDay++;
            localMax = prices[curDay];
            ans += localMax - localMin;
        }

        return ans;
    }

    private int anotherSolution(int[] prices) {
        // 다음날이 상승장이면, 현재 사서 다음날 파는 것을 반복
        // => 계속 상승장이면 팔고 사는것을 반복
        // i.e [i,i+k] 상승장 => p[i+k]-p[i] = (p[i+1]-p[i]) + (p[i+2]-p[i+1]) + .. + (p[i+k]-p[i+k-1])
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) ans += prices[i] - prices[i - 1];
        }
        return ans;
    }
}
