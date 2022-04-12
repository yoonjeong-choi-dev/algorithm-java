package from201to300;

import java.util.Arrays;

// https://leetcode.com/problems/game-of-life/
public class Prob289GameOfLife {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public void gameOfLife(int[][] board) {
        //useAnotherArraySolution(board);
        inMemorySolution(board);
    }

    private void useAnotherArraySolution(int[][] board) {
        int row = board.length, col = board[0].length;
        int[][] ans = new int[row][col];

        int nextX, nextY, numLive;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                numLive = 0;

                // Find all live neighbors
                for (int[] d : directions) {
                    nextX = i + d[0];
                    nextY = j + d[1];
                    if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;
                    if (board[nextX][nextY] == 1) numLive++;
                }

                // 배열이 0으로 초기화 되어 있기 때문에 생존하는 경우만 따지면 된다
                if (board[i][j] == 1) {
                    if (numLive == 2 || numLive == 3) ans[i][j] = 1;
                } else {
                    if (numLive == 3) ans[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ans[i][j];
            }
        }
    }

    // TODO : In-Memory
    private void inMemorySolution(int[][] board) {
        // 현재 및 다음 상태를 고려한 새로운 상태들을 추가하여 계산
        // 0(0->0), 1(1->1), 2(0->1), -1(1->0)

        int row = board.length, col = board[0].length;
        int nextX, nextY, numLive;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                numLive = 0;

                // Find all live neighbors
                for (int[] d : directions) {
                    nextX = i + d[0];
                    nextY = j + d[1];
                    if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;
                    if (board[nextX][nextY] == 1 || board[nextX][nextY] == -1) numLive++;
                }

                // 상태가 변경되는 경우만 고려
                if (board[i][j] == 1) {
                    if (numLive < 2 || numLive > 3) board[i][j] = -1;
                } else {
                    if (numLive == 3) board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = board[i][j] > 0 ? 1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        Prob289GameOfLife sol = new Prob289GameOfLife();

        int[][][] boards = {
                {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}},
                {{1, 1}, {1, 0}}
        };

        int[][][] ans = {
                {{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}},
                {{1, 1}, {1, 1}}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            sol.gameOfLife(boards[i]);
            if (Arrays.deepEquals(boards[i], ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.deepToString(boards[i]));
                System.out.println("Expected : " + Arrays.deepToString(ans[i]));
            }
        }
    }
}
