package from1001to1100;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/high-five/
public class Prob1086HighFive {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> scores = new HashMap<>();

        PriorityQueue<Integer> pq;
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            if (!scores.containsKey(id)) scores.put(id, new PriorityQueue<>());

            pq = scores.get(id);
            pq.add(score);
            if (pq.size() > 5) pq.poll();
        }

        int[][] ans = new int[scores.size()][2];
        int idx = 0;
        int sum;
        for (int id :scores.keySet()) {
            pq = scores.get(id);
            sum = 0;
            while(!pq.isEmpty()) sum += pq.poll();

            ans[idx][0] = id;
            ans[idx][1] = sum / 5;
            idx++;
        }

        return ans;
    }
}
