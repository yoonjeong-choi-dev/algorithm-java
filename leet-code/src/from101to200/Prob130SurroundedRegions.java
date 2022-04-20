package from101to200;

// https://leetcode.com/problems/surrounded-regions/
public class Prob130SurroundedRegions {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    char[][] board;
    int row, col;
    boolean[][] isVisited;

    public void solve(char[][] board) {
        this.board = board;
        row = board.length;
        col = board[0].length;
        isVisited = new boolean[row][col];

        // 가장자리에 대해서만 dfs 사용하면 됨
        // Surrounded regions should not be on the border
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O' && !isVisited[i][0]) dfs(i, 0);
            if (board[i][col - 1] == 'O' && !isVisited[i][col - 1]) dfs(i, col - 1);
        }
        for (int i = 1; i < col - 1; i++) {
            if (board[0][i] == 'O' && !isVisited[0][i]) dfs(0, i);
            if (board[row - 1][i] == 'O' && !isVisited[row - 1][i]) dfs(row - 1, i);
        }

        // 가장자리 dfs 이후 내부에 있는 'O' 값을 모두 X로 변경하면 됨
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (board[i][j] == 'O' && !isVisited[i][j]) board[i][j] = 'X';
            }
        }
    }

    private void dfs(int x, int y) {
        isVisited[x][y] = true;

        int nextX, nextY;
        for (int[] d : directions) {
            nextX = x + d[0];
            nextY = y + d[1];

            // 가장자리 테스트인 경우에만 범위 검색 필요
            if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;

            if (board[nextX][nextY] == 'O' && !isVisited[nextX][nextY]) dfs(nextX, nextY);
        }
    }
}
