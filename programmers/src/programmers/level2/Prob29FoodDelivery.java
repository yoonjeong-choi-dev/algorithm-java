package programmers.level2;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/12978
// TODO : SPFA vs Dijkstra 복습
public class Prob29FoodDelivery {
    public int solution(int N, int[][] road, int K) {

        // 그래프 그리기
        int[][] graph = new int[N][N];
        for (int[] r : road) {
            // 두 정점 사이에 다수의 엣지 존재할 수 있음
            if (graph[r[0] - 1][r[1] - 1] != 0) {
                graph[r[0] - 1][r[1] - 1] = Math.min(graph[r[0] - 1][r[1] - 1], r[2]);
            } else {
                graph[r[0] - 1][r[1] - 1] = r[2];
            }
            graph[r[1] - 1][r[0] - 1] = graph[r[0] - 1][r[1] - 1];

        }

        // 0번 마을에서의 거리
        //int[] dist = spfaAlgorithm(graph, 0);
        int[] dist = dijkstra(graph, 0);

        int answer = 0;
        for (int d : dist) {
            if (d <= K) answer++;
        }

        return answer;
    }

    // SPFA(The Shortest Path Faster Algorithm) 알고리즘
    private int[] spfaAlgorithm(int[][] graph, int start) {
        int numVertex = graph.length;

        // start에서 i 정점까지의 거리
        int[] ret = new int[numVertex];

        // 초기값은 충분히 큰 값을 넣는다
        for (int i = 0; i < numVertex; i++) {
            ret[i] = (int) 10e9;
        }

        // spfa
        Queue<Integer> queue = new ArrayDeque<>(numVertex);
        boolean[] isInQueue = new boolean[numVertex];

        // 시작점 초기화
        ret[start] = 0;
        isInQueue[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            isInQueue[cur] = false;

            // 연결된 정점 찾기
            for (int i = 0; i < numVertex; i++) {
                if (graph[cur][i] == 0) continue;

                // 현재 정점에서 다음 정점으로 갈때, 다음 정점(i)의 거리
                int nextDist = graph[cur][i] + ret[cur];

                // 더 나은 조건인 경우 업데이트 i.e 더 빠른 거리
                // 일반적인 bfs에서는 아래와 같이 이미 방문한 정점에 대해서 다시 계산하지 않음
                if (nextDist < ret[i]) {
                    ret[i] = nextDist;

                    // 큐에 해당 정점이 없는 경우 추가
                    // => 정점 정보가 업데이트되었으므로 다시 탐색해야 함
                    if (!isInQueue[i]) {
                        isInQueue[i] = true;
                        queue.add(i);
                    }
                }
            }
        }

        return ret;
    }

    // 다익스트라 알고리즘
    private int[] dijkstra(int[][] graph, int start) {
        int numVertex = graph.length;

        // start에서 i 정점까지의 거리
        int[] ret = new int[numVertex];
        boolean[] isVisited = new boolean[numVertex];

        // 초기값은 충분히 큰 값을 넣는다
        for (int i = 0; i < numVertex; i++) {
            ret[i] = (int) 10e9;
        }

        // 우선 순위 큐를 이용하여 가장 짧은 거리의 노드부터 탐색 시작 => bfs 방식으로 한번에 최단 거리 계산 가능
        PriorityQueue<Node> bfs = new PriorityQueue<>();
        ret[start] = 0;
        bfs.add(new Node(start, 0));

        while (!bfs.isEmpty()) {
            // 가장 거리가 짧은 노드 방문
            Node cur = bfs.poll();
            isVisited[start] = true;

            // 연결된 정점 찾기
            for (int i = 0; i < numVertex; i++) {
                if (graph[cur.vertex][i] == 0 || isVisited[i]) continue;

                // 현재 정점에서 다음 정점으로 갈때, 다음 정점(i)의 거리 계산 및 업데이트
                if (ret[i] > cur.dist + graph[cur.vertex][i]) {
                    ret[i] = cur.dist + graph[cur.vertex][i];
                    bfs.add(new Node(i, ret[i]));
                }
            }
        }
        return ret;
    }

    private class Node implements Comparable<Node> {
        public int vertex;
        public int dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;
            return vertex == node.vertex && dist == node.dist;
        }

        @Override
        public int compareTo(Node node) {
            // 거리가 짧은 노드가 더 먼저 와야함
            return dist - node.dist;
        }

    }

    public static void main(String[] args) {
        Prob29FoodDelivery sol = new Prob29FoodDelivery();

        int[] n = {5, 6};
        int[][][] roads = {
                {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}},
                {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}
        };
        int[] k = {3, 4};

        int[] ans = {4, 4};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int mySol = sol.solution(n[i], roads[i], k[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
