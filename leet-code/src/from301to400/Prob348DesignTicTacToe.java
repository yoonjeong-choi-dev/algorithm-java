package from301to400;

// https://leetcode.com/problems/design-tic-tac-toe/
public class Prob348DesignTicTacToe {
    class TicTacToe {

        int boardSize;
        int[][] rowsCount;
        int[][] colsCount;
        int[] diagonalCount;
        int[] reverseDiagonalCount;
        int gameState;

        public TicTacToe(int n) {
            boardSize = n;
            rowsCount = new int[2][n];
            colsCount = new int[2][n];
            diagonalCount = new int[2];
            reverseDiagonalCount = new int[2];
            gameState = 0;
        }

        public int move(int row, int col, int player) {
            if (gameState != 0) return gameState;

            // modify for 0-index
            int pIndex = player - 1;

            // row or col
            if ((++rowsCount[pIndex][row]) == boardSize
                    || (++colsCount[pIndex][col]) == boardSize) {
                return gameState = player;
            }

            // diagonal
            if (row == col && (++diagonalCount[pIndex]) == boardSize) {
                return gameState = player;
            }

            // reverse diagonal
            if (row + col == boardSize - 1 && (++reverseDiagonalCount[pIndex]) == boardSize) {
                return gameState = player;
            }

            return gameState;
        }
    }
}
