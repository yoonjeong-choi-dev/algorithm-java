package from1101to1200;

import java.util.*;

// https://leetcode.com/problems/parallel-courses/
public class Prob1136ParallelCourses {
    public int minimumSemesters(int n, int[][] relations) {
        // Make the graph
        int[] inDegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] r : relations) {
            r[0]--;
            r[1]--;

            inDegree[r[1]]++;
            graph.get(r[0]).add(r[1]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                visited[i] = true;
            }
        }

        int ans = 0;
        int cur;
        while (!queue.isEmpty()) {
            // 현재 학기
            ans++;

            for (int i = queue.size() - 1; i >= 0; i--) {
                cur = queue.poll();

                for (int next : graph.get(cur)) {
                    inDegree[next]--;

                    if (inDegree[next] == 0) {
                        if (visited[next]) return -1;
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
        }

        // check all courses is taken
        for (boolean v : visited) if (!v) return -1;

        return ans;
    }
}
