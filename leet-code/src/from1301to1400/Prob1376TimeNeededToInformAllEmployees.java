package from1301to1400;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/time-needed-to-inform-all-employees/
public class Prob1376TimeNeededToInformAllEmployees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // graph[i] : i 직원의 직속 부하 리스트
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < n; i++) {
            if (i == headID) continue;
            graph.get(manager[i]).add(i);
        }

        int ans = 0;

        // (index, 루트에서 현재 인덱스까지 통보 시간)
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(new int[]{headID, 0});
        int[] curEmployee;
        while (!bfs.isEmpty()) {
            // 현재 레벨 (헤드에서의 거리가 같은 직원들) 탐색
            for (int i = bfs.size(); i > 0; i--) {
                curEmployee = bfs.poll();
                ans = Math.max(ans, curEmployee[1]);

                for (Integer next : graph.get(curEmployee[0])) {
                    bfs.offer(new int[]{next, curEmployee[1] + informTime[curEmployee[0]]});
                }
            }
        }

        return ans;
    }
}
