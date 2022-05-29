package from701to800;

import java.util.*;

// https://leetcode.com/problems/cheapest-flights-within-k-stops/
public class Prob787CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // 인접 정점의 인덱스 및 거리
        List<List<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] flight : flights) {
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // 각 정점의 최단 경로
        int[] dist = new int[n];

        // 최단 경로 탐색하면서 방문했을 때 시작점 사이의 노드 개수
        int[] curSteps = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = -1;
            curSteps[i] = -1;
        }

        // Dijkstra's algorithm
        // int[] - 0 : vertex, 1 : dist, 2 : step from src
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        dist[src] = 0;
        curSteps[src] = 0;
        queue.add(new int[]{src, 0, 0});

        int[] curInfo;
        int curVertex, curDist, curStep;
        int nextVertex, nextDist;
        while (!queue.isEmpty()) {
            curInfo = queue.poll();
            curVertex = curInfo[0];
            curDist = curInfo[1];
            curStep = curInfo[2];
            curSteps[curVertex] = curStep;

            // 도착한 경우에는 탐색 종료 : 우선 순위 힙이기때문에 주어진 스텝 내로 도달 가능한 최단 거리
            if (curVertex == dst) return curDist;

            // 현재 스텝에서 더 진행 못하는 경우는 무시
            if (curStep > k) continue;


            for (int[] next : graph.get(curVertex)) {
                nextVertex = next[0];
                nextDist = curDist + next[1];

                if (dist[nextVertex] == -1 || nextDist < dist[nextVertex]) {
                    // 짧은 경로 발견 시 큐에 저장
                    dist[nextVertex] = nextDist;
                    queue.add(new int[]{nextVertex, nextDist, curStep + 1});
                } else if (curSteps[nextVertex] == -1 || curSteps[nextVertex] > curStep) {
                    // 좀 더 탐색이 가능한 경우 i.e 더 노드를 방문 가능한 경우
                    // : 지나갈 수 있는 노드 개수 제한이 있어서, 반드시 최단 경로로만 탐색하면 안됨
                    // => 현재 노드에서 다음 노드 탐색이 가능한 경우에는 탐색해봄
                    queue.add(new int[]{nextVertex, nextDist, curStep + 1});
                }
            }

        }

        return dist[dst];
    }
}
