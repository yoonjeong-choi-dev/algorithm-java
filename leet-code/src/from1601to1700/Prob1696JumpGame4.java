package from1601to1700;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/jump-game-vi/
public class Prob1696JumpGame4 {
    public int maxResult(int[] nums, int k) {
        int len = nums.length;

        int ans = nums[0];

        // (i-k, i] 까지 점수 저장
        // (nums[0:i] 까지 부분 문제 최대 점수, i)
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        pq.add(new int[]{nums[0], 0});


        for (int i = 1; i < len; i++) {
            // i-k 이전 정보들 삭제
            while (pq.peek()[1] < i - k) pq.poll();

            // [0,i) 까지의 최대 점수에 현재 점수 더하기
            ans = nums[i] + pq.peek()[0];
            pq.add(new int[]{ans, i});
        }

        return ans;
    }
}
