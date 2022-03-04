package topcoder.ch7_dp;

public class Knapsack {

    public int solution(int[] weights, int[] prices, int maxWeight) {
        int numItems = weights.length;

        // cache[i][w] : i-1번째 아이템들까지 고려하고, 현재 무제가 w 일때의 정답
        int[][] cache = new int[numItems + 1][maxWeight + 1];

        int ans = 0;
        for (int i = 0; i < numItems; i++) {
            // 현재 무게가 w일 때, i번째 아이템 넣을지 결정
            for (int w = 0; w <= maxWeight; w++) {
                if (w + weights[i] <= maxWeight) {
                    // 캐시 업데이트 : max(선택안한 경우, 선택한 경우)
                    cache[i + 1][w + weights[i]] = Math.max(cache[i + 1][w + weights[i]], cache[i][w] + prices[i]);
                    ans = Math.max(cache[i + 1][w + weights[i]], ans);
                }
            }
        }


        return ans;
    }


    public static void main(String[] args) {
        Knapsack sol = new Knapsack();

        int[] weights = {2, 4, 1, 2, 3};
        int[] prices = {2, 3, 2, 3, 6};
        int maxWeight = 10;

        System.out.println(sol.solution(weights, prices, maxWeight));
    }
}
