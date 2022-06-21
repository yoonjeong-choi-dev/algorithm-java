package from1601to1700;

import java.util.PriorityQueue;

// https://leetcode.com/problems/furthest-building-you-can-reach/
public class Prob1642FurthestBuildingYouCanReach {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int len = heights.length;

        // 현재 인덱스까지 필요한 벽돌
        int curUsedBlock = 0;

        // 사다리는 현재까지 건물 차이 중에 가장 큰 값들만 : Top-K Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int diff;
        for (int i = 1; i < len; i++) {
            // i-1 -> i 이동
            if (heights[i - 1] >= heights[i]) continue;

            diff = heights[i] - heights[i - 1];
            pq.add(diff);

            if (pq.size() > ladders) {
                curUsedBlock += pq.poll();
            }

            if (curUsedBlock > bricks) return i - 1;
        }

        // 끝까지 탐색을 다한 경우에는 마지막 인덱스
        return len - 1;
    }
}
