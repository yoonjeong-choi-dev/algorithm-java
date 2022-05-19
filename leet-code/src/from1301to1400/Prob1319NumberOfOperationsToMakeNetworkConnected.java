package from1301to1400;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/number-of-operations-to-make-network-connected/
public class Prob1319NumberOfOperationsToMakeNetworkConnected {

    private List<List<Integer>> graph;
    private boolean[] isVisited;

    public int makeConnected(int n, int[][] connections) {
        // 엣지 개수가 n-1 보다 작으면 불가능
        if (n - 1 > connections.length) return -1;


        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] conn : connections) {
            graph.get(conn[0]).add(conn[1]);
            graph.get(conn[1]).add(conn[0]);
        }

        isVisited = new boolean[n];
        int numComponent = 0;
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                numComponent++;
                dfs(i);
            }
        }

        return numComponent - 1;
    }

    private void dfs(int node) {
        isVisited[node] = true;
        for (int next : graph.get(node)) {
            if (!isVisited[next]) {
                dfs(next);
            }
        }
    }
}
