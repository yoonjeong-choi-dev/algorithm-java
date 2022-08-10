package from601to700;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/number-of-distinct-islands/
public class Prob694NumberOfDistinctIslands {

    private int[][] grid;
    private boolean[][] visited;
    private int row,col;
    private StringBuilder curPath;

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];

        // 각 섬의 모양을 문자열로 변환하여 저장
        Set<String> shapeSet = new HashSet<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(!visited[i][j] && grid[i][j] == 1) {
                    curPath = new StringBuilder();
                    dfs(i,j, '0');
                    shapeSet.add(curPath.toString());
                }
            }
        }

        return shapeSet.size();
    }

    private void dfs(int x, int y, char dir) {
        // out of bound
        if(x < 0 || x >= row || y < 0 || y >=col) return;

        // not land
        if(grid[x][y] != 1) return;

        // already visited
        if(visited[x][y]) return;

        visited[x][y] = true;
        curPath.append(dir);
        dfs(x-1,y, 'u');
        dfs(x+1, y, 'd');
        dfs(x, y-1,'l');
        dfs(x, y+1, 'r');
        curPath.append('#');
    }

}
