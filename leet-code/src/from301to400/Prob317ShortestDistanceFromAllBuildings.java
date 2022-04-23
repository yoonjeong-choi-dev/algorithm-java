package from301to400;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-distance-from-all-buildings/
public class Prob317ShortestDistanceFromAllBuildings {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int shortestDistance(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;

        int[][] dist = new int[rowSize][colSize];
        int curEmptyVal = 0;

        // (row, col) 위치에 있는 빌딩에서 BFS 탐색을 통해 각 empty land 까지의 거리를 누적으로 업데이트
        // => 마지막 빌딩에서의 탐색 결과 이후 dist[i][j] 가 (i,j)에 집을 설치 했을 때 모든 빌딩까지의 거리
        // => 시간 복잡도 : O(M*N*(M*N)) = O(M^2 * N^2) < 50^4
        Queue<int[]> bfs = new LinkedList<>();
        int curDist, nextX, nextY;
        int[] curNode;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (grid[row][col] != 1) {
                    continue;
                }

                bfs.add(new int[]{row, col});
                curDist = 1;

                while (!bfs.isEmpty()) {
                    // 현재 큐에 있는 노드들에 대해서 다음 노드 찾기
                    for (int i = bfs.size(); i > 0; i--) {
                        curNode = bfs.poll();

                        for (int[] d : directions) {
                            nextX = curNode[0] + d[0];
                            nextY = curNode[1] + d[1];
                            if (nextX < 0 || nextX >= rowSize || nextY < 0 || nextY >= colSize || grid[nextX][nextY] != curEmptyVal)
                                continue;

                            // 다음 노드 방문
                            grid[nextX][nextY]--;
                            dist[nextX][nextY] += curDist;
                            bfs.add(new int[]{nextX, nextY});
                        }
                    }

                    // 다음 스텝을 위해 거리 업데이트
                    curDist++;
                }

                // 다음 빌딩에 대한 bfs 탐색을 위해 empty land 값 업데이트
                curEmptyVal--;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                // 모든 빌딩에서 도달 가능한 위치 : 빈 땅의 값이 curEmptyVal 와 같아야 함
                if (grid[row][col] <= 0 && grid[row][col] == curEmptyVal) ans = Math.min(ans, dist[row][col]);
            }
        }


        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
