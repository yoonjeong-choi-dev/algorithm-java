package from401to500;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/pacific-atlantic-water-flow/
public class Prob417PacificAtlanticWaterFlow {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int[][] visited;
    private int[][] height;
    private int rowSize, colSize;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.height = heights;
        rowSize = heights.length;
        colSize = heights[0].length;
        visited = new int[rowSize][colSize];

        // Pacific ocean
        final int pacific = 1;
        for (int i = 0; i < rowSize; i++) {
            if(visited[i][0] < pacific)
                dfs(i, 0, pacific);
        }
        for (int i = 1; i < colSize; i++) {
            if(visited[0][i] < pacific)
                dfs(0, i, pacific);
        }

        // Atlantic ocean
        final int atlantic = 2;
        for (int i = 0; i < rowSize; i++) {
            if(visited[i][colSize-1] < atlantic)
                dfs(i, colSize - 1, atlantic);
        }
        for (int i = 0; i < colSize - 1; i++) {
            if(visited[rowSize-1][i] < atlantic)
                dfs(rowSize - 1, i, atlantic);
        }

        final int both = pacific + atlantic;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if(visited[i][j] == both) {
                    List<Integer> curAns = new ArrayList<>();
                    curAns.add(i);
                    curAns.add(j);
                    ans.add(curAns);
                }
            }
        }

        return ans;
    }

    private void dfs(int x, int y, int ocean) {
        visited[x][y] += ocean;

        int nextX, nextY;
        for (int[] d : directions) {
            nextX = x + d[0];
            nextY = y + d[1];

            if (nextX < 0 || nextX >= rowSize || nextY < 0 || nextY >= colSize) continue;

            // cell's height is less than or equal to the current cell's height
            // => 거꾸로 탐색하기 때문에 현재 높이보다 높은 셀에 대해서만 탐색
            if (visited[nextX][nextY] < ocean && height[x][y] <= height[nextX][nextY])
                dfs(nextX, nextY, ocean);
        }
    }
}
