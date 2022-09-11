package from1301to1400;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/maximum-performance-of-a-team/
public class Prob1383MaximumPerformanceOfATeam {

    private static int MOD = 1000000007;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) index[i] = i;

        // 우선 효율이 높은 순으로 정렬
        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(efficiency[o2], efficiency[o1]);
            }
        });

        // Top-k for speed heap
        PriorityQueue<Integer> topSpeedHeap = new PriorityQueue<>();

        long ans = 0, curSpeedSum = 0;
        int curSpeed, curEfficiency;

        // 효율이 높은 순서대로 탐색
        for (Integer idx : index) {
            curSpeed = speed[idx];
            curEfficiency = efficiency[idx];

            // 높은 스피드들만 저장 => 가득 찬 경우 낮은 스피드는 뺀다
            if (topSpeedHeap.size() == k) {
                curSpeedSum -= topSpeedHeap.poll();
            }
            topSpeedHeap.add(curSpeed);
            curSpeedSum += curSpeed;

            // 퍼포먼스 계산 : 효율에 대한 내림차순이므로 현재 효율이 가장 낮은 효율
            ans = Math.max(ans, curSpeedSum * curEfficiency);
        }

        return (int) (ans % MOD);
    }
}
