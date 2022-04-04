package from101to200;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class Prob121BestTimeBuyAndSellStock {
    public int maxProfit(int[] prices) {
        // return myLinearSolution(prices);
        return improvedSolution(prices);
    }

    // Trial 1 : Linear
    // Runtime: 6 ms, faster than 7.03% of Java online submissions
    private int myLinearSolution(int[] prices) {
        int len = prices.length;

        // 배열의 길이가 1인 경우에는 판매 불가능
        if (len == 1) return 0;

        // prices[0:i-1] 부분 배열에 대한 최소값
        // => i 번째 날에 판매할 때, 구매 가격 의미
        int[] bestBuy = new int[len];
        bestBuy[1] = prices[0];

        for (int i = 2; i < len; i++) {
            bestBuy[i] = Math.min(bestBuy[i - 1], prices[i - 1]);
        }

        int ans = 0;

        // 첫날에는 판매가 불가능하므로 1부터 시작
        for (int i = 1; i < len; i++) {
            // i 번째 날에 판매
            ans = Math.max(ans, prices[i] - bestBuy[i]);
        }

        return ans;
    }

    // TODO : Improve Runtime
    private int improvedSolution(int[] prices) {
        int ans = 0;
        int bestBuy = prices[0];

        // 판매는 두번째 날부터 가능하므로 인덱스는 1부터 탐색
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < bestBuy) {
                // 현재 가격이 기존 구매 가격보다 작은 경우 i번째 구매
                bestBuy = prices[i];
            } else if(prices[i] - bestBuy > ans) {
                // 현재 가격으로 판매하면 수익이 커지는 경우 판매
                ans = prices[i] - bestBuy;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob121BestTimeBuyAndSellStock sol = new Prob121BestTimeBuyAndSellStock();

        int[][] prices = {{7, 1, 5, 3, 6, 4}, {7, 6, 4, 3, 1}};

        int[] ans = {5, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.maxProfit(prices[i]);
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
