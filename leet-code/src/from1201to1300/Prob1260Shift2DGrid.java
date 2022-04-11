package from1201to1300;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/shift-2d-grid/
public class Prob1260Shift2DGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length;
        int totalSize = row * col;
        k = k % totalSize;

        List<List<Integer>> ans = new ArrayList<>(row);

        List<Integer> curRow;
        int curIdx = totalSize - k;
        for (int i = 0; i < row; i++) {
            curRow = new ArrayList<>(col);

            for (int j = 0; j < col; j++) {
                if (curIdx == totalSize) curIdx = 0;
                curRow.add(grid[curIdx / col][curIdx % col]);
                curIdx++;
            }
            ans.add(curRow);
        }

        return ans;
    }
}
