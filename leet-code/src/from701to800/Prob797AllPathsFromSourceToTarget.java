package from701to800;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/all-paths-from-source-to-target/
public class Prob797AllPathsFromSourceToTarget {
    private int[][] graph;
    private int target;
    private List<List<Integer>> ans;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        target = graph.length - 1;
        ans = new LinkedList<>();

        LinkedList<Integer> curPath = new LinkedList<>();
        curPath.add(0);

        dfs(0, curPath);
        return ans;
    }

    private void dfs(int curIdx, List<Integer> curPath) {
        if (curIdx == target) {
            ans.add(new ArrayList<>(curPath));
            return;
        }

        // Condition : The input graph is guaranteed to be a DAG
        // => 노드의 방문 여부를 확인 필요 X
        for (int i : graph[curIdx]) {
            curPath.add(i);
            dfs(i, curPath);
            curPath.remove(curPath.size() - 1);
        }
    }
}
