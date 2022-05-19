package from1101to1200;

import javafx.util.Pair;

import java.util.*;

// https://leetcode.com/problems/critical-connections-in-a-network/
public class Prob1192CriticalConnectionsInNetwork {

    private List<List<Integer>> graph;
    private int[] discoveredOrder;
    private Set<Pair<Integer, Integer>> edges;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Step 1 : Make the graph
        graph = new ArrayList<>(n);
        edges = new HashSet<>();
        discoveredOrder = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            discoveredOrder[i] = -1;
        }
        for (List<Integer> e : connections) {
            graph.get(e.get(0)).add(e.get(1));
            graph.get(e.get(1)).add(e.get(0));

            int start = Math.min(e.get(0), e.get(1));
            int end = Math.max(e.get(0), e.get(1));
            edges.add(new Pair<>(start, end));
        }

        // Step 2 : Recursive Search to find bridges
        dfs(0, 0);

        List<List<Integer>> ans = new ArrayList<>();
        for(Pair<Integer, Integer> conn : edges) {
            ans.add(new ArrayList<Integer>(Arrays.asList(conn.getKey(), conn.getValue())));
        }

        return ans;
    }

    private int dfs(int cur, int curOrder) {
        if (discoveredOrder[cur] != -1) return discoveredOrder[cur];

        // 발견 순서 기록
        discoveredOrder[cur] = curOrder;

        int minOrder = curOrder + 1;
        for (Integer next : graph.get(cur)) {
            if (discoveredOrder[next] != -1 && discoveredOrder[next] == curOrder - 1) {
                continue;
            }

            // 현재 노드와 연결된 노드들의 발견 순서 재귀 탐색
            int recursiveRet = dfs(next, curOrder + 1);

            if (recursiveRet <= curOrder) {
                // 다음 노드 발견 순서가 현재 발견 순서보다 작은 경우 => 먼저 찾아짐
                // i.e 다음 노드 -> 현재 노드 경로가 존재
                // => 해당 엣지를 삭제하여도,  다음 노드 <-> 현재 노드 경로가 존재하므로 정답 x
                int start = Math.min(cur, next);
                int end = Math.max(cur, next);
                edges.remove(new Pair<>(start, end));
            }

            minOrder = Math.min(minOrder, recursiveRet);
        }

        return minOrder;
    }
}
