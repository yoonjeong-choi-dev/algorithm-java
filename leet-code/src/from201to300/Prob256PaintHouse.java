package from201to300;

// https://leetcode.com/problems/paint-house/
public class Prob256PaintHouse {
    public int minCost(int[][] costs) {
        int numHouse = costs.length;

        // prevCost[i] : 현재 집을 i 색상으로 칠했을 때의 비용
        int[] prevCost = costs[0];

        int[] curCost;
        for (int i = 1; i < numHouse; i++) {
            curCost = costs[i].clone();

            // 0 번째 색상 색칠 : 이전 집은 1 or 2
            curCost[0] += Math.min(prevCost[1], prevCost[2]);

            // 1 번째 색상 색칠 : 이전 집은 0 or 2
            curCost[1] += Math.min(prevCost[0], prevCost[2]);

            // 2 번째 색상 색칠 : 이전 집은 0 or 1
            curCost[2] += Math.min(prevCost[0], prevCost[1]);

            prevCost = curCost;
        }

        return Math.min(prevCost[0], Math.min(prevCost[1], prevCost[2]));
    }
}
