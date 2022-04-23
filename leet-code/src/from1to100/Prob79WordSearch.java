package from1to100;

// https://leetcode.com/problems/word-search/
public class Prob79WordSearch {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int row, col, wordLen;
    private char[][] board;
    private String word;

    public boolean exist(char[][] board, String word) {
        if (board.length * board[0].length < word.length()) return false;

        this.board = board;
        this.word = word;

        row = board.length;
        col = board[0].length;
        wordLen = word.length();

        char start = word.charAt(0);
        boolean[][] isVisited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == start) {
                    isVisited[i][j] = true;
                    if (dfs(i, j, 1, isVisited)) return true;
                    isVisited[i][j] = false;
                }
            }
        }

        return false;
    }

    private boolean dfs(int x, int y, int wordIdx, boolean[][] isVisited) {
        if (wordIdx >= wordLen) return true;

        int nextX, nextY;
        for (int[] d : directions) {
            nextX = x + d[0];
            nextY = y + d[1];

            if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || board[nextX][nextY] != word.charAt(wordIdx) || isVisited[nextX][nextY])
                continue;


            isVisited[nextX][nextY] = true;
            if (dfs(nextX, nextY, wordIdx + 1, isVisited)) return true;
            isVisited[nextX][nextY] = false;
        }

        return false;
    }
}
