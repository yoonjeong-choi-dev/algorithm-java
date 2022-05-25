package from1to100;

import java.util.*;

// https://leetcode.com/problems/sudoku-solver/
public class Prob37SudokuSolver {

    private List<Set<Integer>> row, col, square;
    private char[][] board;
    private boolean isSolved;

    public void solveSudoku(char[][] board) {
        this.board = board;
        isSolved = false;

        row = new ArrayList<>(9);
        col = new ArrayList<>(9);
        square = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            row.add(new HashSet<>());
            col.add(new HashSet<>());
            square.add(new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int curNum = board[i][j] - '0';
                    row.get(i).add(curNum);
                    col.get(j).add(curNum);
                    square.get(getSquareIndex(i, j)).add(curNum);
                }
            }
        }

        dfs(0);
    }

    private void dfs(int curIdx) {

        if (curIdx == 81) {
            isSolved = true;
            return;
        }

        int x = curIdx / 9;
        int y = curIdx % 9;

        if (board[x][y] != '.') {
            dfs(curIdx + 1);
        } else {
            int sq = getSquareIndex(x, y);

            for (int i = 1; i < 10; i++) {
                if (!row.get(x).contains(i) && !col.get(y).contains(i) && !square.get(sq).contains(i)) {
                    board[x][y] = (char) (i + '0');
                    row.get(x).add(i);
                    col.get(y).add(i);
                    square.get(sq).add(i);
                    dfs(curIdx + 1);

                    if (isSolved) return;
                    else {
                        board[x][y] = '.';
                        row.get(x).remove(i);
                        col.get(y).remove(i);
                        square.get(sq).remove(i);
                    }
                }
            }
        }
    }

    private int getSquareIndex(int x, int y) {
        return (x / 3) * 3 + y / 3;
    }


    public static void main(String[] args) {
        Prob37SudokuSolver sol = new Prob37SudokuSolver();

        sol.solveSudoku(new char[][]
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
        );
    }
}
