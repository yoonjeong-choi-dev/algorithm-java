package from801to900;

import java.util.*;

// https://leetcode.com/problems/find-eventual-safe-states/
public class Prob802FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int numNodes = graph.length;

        List<Set<Integer>> adj = new ArrayList<>(numNodes);
        List<Set<Integer>> reverseAdj = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adj.add(new HashSet<>());
            reverseAdj.add(new HashSet<>());
        }

        // 리프 노드 저장
        Queue<Integer> roots = new LinkedList<>();

        // 정방향 및 역방향 그래프 생성
        for (int i = 0; i < numNodes; i++) {
            if (graph[i].length == 0) roots.add(i);

            for (int neighbor : graph[i]) {
                adj.get(i).add(neighbor);
                reverseAdj.get(neighbor).add(i);
            }
        }

        boolean[] isSafeNode = new boolean[numNodes];

        // Topological Sort with reverse graph
        // => 역방향 그래프의 루트 노드가 정방향 그래프가 리프 노드
        int root;
        while (!roots.isEmpty()) {
            root = roots.poll();
            isSafeNode[root] = true;

            // 역방향 그래프의 인접 노드 : 현재 노드로 들어오는 노드들
            // i.e 정방향에서 prev -> root
            for (int prev : reverseAdj.get(root)) {
                // 정방향 그래프에서 root 노드 삭제
                adj.get(prev).remove(root);

                // root 노드 삭제 시, 리프 노드가 되는 노드를 큐에 추가
                if (adj.get(prev).isEmpty()) {
                    roots.add(prev);
                }
            }
        }


        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            if (isSafeNode[i]) ans.add(i);
        }
        return ans;
    }
}
