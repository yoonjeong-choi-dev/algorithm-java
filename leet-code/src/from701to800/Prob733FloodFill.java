package from701to800;

import java.util.Arrays;

// https://leetcode.com/problems/flood-fill/
public class Prob733FloodFill {

    private int curColor;
    private int[][] image;
    private int rowSize, colSize;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        this.image = image;
        curColor = image[sr][sc];
        rowSize = image.length;
        colSize = image[0].length;

        dfs(sr, sc, newColor);

        return image;
    }

    private void dfs(int row, int col, int color) {
        // Base Case 1 : 범위 밖으로 벗어난 경우
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) return;

        // Base Case 2 : 같은 색깔이 아닌 경우
        if (image[row][col] != curColor) return;

        // Base Case 3 : 이미 칠해진 경우
        if(image[row][col] == color) return;

        // 현재 픽셀 색칠
        image[row][col] = color;

        // 인접 픽셀에 대해서 dfs
        dfs(row - 1, col, color);
        dfs(row + 1, col, color);
        dfs(row, col - 1, color);
        dfs(row, col + 1, color);
    }


    public static void main(String[] args) {
        Prob733FloodFill sol = new Prob733FloodFill();

        int[][][] images = {{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, {{0, 0, 0}, {0, 0, 0}}};
        int[] sr = {1, 0};
        int[] sc = {1, 0};
        int[] colors = {2, 2};

        int[][][] ans = {{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}}, {{2, 2, 2}, {2, 2, 2}}};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[][] mySol = sol.floodFill(images[i], sr[i], sc[i], colors[i]);
            if (Arrays.deepEquals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.deepToString(mySol));
                System.out.println("Expected : " + Arrays.deepToString(ans[i]));
            }
        }
    }
}
