package from201to300;

import java.util.*;

// https://leetcode.com/problems/graph-valid-tree/
public class Prob261GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        Map<Integer, Integer> parent = new HashMap<>();
        Stack<Integer> dfs = new Stack<>();
        dfs.add(0);
        parent.put(0, -1);

        int cur;
        while (!dfs.isEmpty()) {
            cur = dfs.pop();

            for (int next : graph.get(cur)) {
                // 부모인 경우는 무시
                if(parent.get(cur) == next) continue;

                // 이미 지나간 노드인 경우 거짓
                if(parent.containsKey(next)) return false;

                parent.put(next, cur);
                dfs.push(next);
            }
        }

        // 모두 연결되어 있어야만 트리
        return parent.size() == n;
    }
}
