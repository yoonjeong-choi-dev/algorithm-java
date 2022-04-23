package from701to800;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/is-graph-bipartite/
// Bipartite Test : https://gmlwjd9405.github.io/2018/08/23/algorithm-bipartite-graph.html
public class Prob785IsGraphBipartite {

    // Caution : 그래프가 연결되지 않았을 수도 있음
    public boolean isBipartite(int[][] graph) {
        int numVertex = graph.length;

        // 0 : 탐색하지 않은 정점
        // -1, 1 : 이분 그래프에서 독립된 두 개의 영역 표현
        int[] coloring = new int[numVertex];

        Queue<Integer> bfs = new LinkedList<>();

        // 그래프의 모든 컴포넌트에 대해서 탐색
        int curV;
        for(int i=0;i<numVertex;i++){
            if(coloring[i] != 0) continue;

            bfs.offer(i);
            coloring[i] = -1;

            while(!bfs.isEmpty()) {
                curV = bfs.poll();

                // 방문하지 않은 노드들을 현재 노드와 반대 진영으로 색칠
                // 방문한 노드(현재 노드 이웃 노드)가 현재 노드와 같은 진영인 경우 not bipartite
                for(int next : graph[curV]) {
                    if(coloring[next] == coloring[curV]) return false;

                    if(coloring[next] == 0) {
                        coloring[next] = -coloring[curV];
                        bfs.add(next);
                    }
                }
            }
        }

        return true;
    }
}
