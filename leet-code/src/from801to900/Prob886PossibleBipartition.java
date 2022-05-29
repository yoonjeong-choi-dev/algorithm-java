package from801to900;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/possible-bipartition/
public class Prob886PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] dis : dislikes) {
            graph.get(dis[0] - 1).add(dis[1] - 1);
            graph.get(dis[1] - 1).add(dis[0] - 1);
        }

        int[] coloring = new int[n];

        Queue<Integer> bfs = new LinkedList<>();
        int curNode;
        for (int i = 0; i < n; i++) {
            if (coloring[i] != 0) continue;

            bfs.add(i);
            coloring[i] = 1;

            while (!bfs.isEmpty()) {
                curNode = bfs.poll();
                for (int next : graph.get(curNode)) {
                    if (coloring[next] == coloring[curNode]) return false;

                    if (coloring[next] == 0) {
                        coloring[next] = -coloring[curNode];
                        bfs.add(next);
                    }
                }
            }
        }

        return true;
    }
}
