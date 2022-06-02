package from301to400;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
public class Prob323NumberOfComponentsInGraph {

    private List<List<Integer>> graph;
    private boolean[] isVisited;

    public int countComponents(int n, int[][] edges) {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        isVisited = new boolean[n];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                ans++;
                dfs(i);
            }
        }

        return ans;
    }

    private void dfs(int node) {
        isVisited[node] = true;
        for (int next : graph.get(node)) {
            if (!isVisited[next]) dfs(next);
        }
    }
}
