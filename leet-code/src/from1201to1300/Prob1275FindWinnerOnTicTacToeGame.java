package from1201to1300;

// https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
public class Prob1275FindWinnerOnTicTacToeGame {
    public String tictactoe(int[][] moves) {

        int[][] board = new int[3][3];
        int[] rowCount = new int[3];
        int[] colCount = new int[3];
        int[] diagCount = new int[3];

        int curPlayer = 1;
        boolean isEnd;
        for (int[] move : moves) {
            board[move[0]][move[1]] = curPlayer;
            rowCount[move[0]]++;
            colCount[move[1]]++;

            // Check row
            if (rowCount[move[0]] == 3) {
                isEnd = true;

                for(int i=0;i<3;i++){
                    if(board[move[0]][i] != curPlayer) {
                        isEnd = false;
                        break;
                    }
                }

                if(isEnd) return curPlayer == 1 ? "A" : "B";
            }

            // Check col
            if(colCount[move[1]] == 3) {
                isEnd = true;

                for(int i=0;i<3;i++){
                    if(board[i][move[1]] != curPlayer) {
                        isEnd = false;
                        break;
                    }
                }

                if(isEnd) return curPlayer == 1 ? "A" : "B";
            }

            if(move[0] == move[1]) {
                diagCount[0]++;
                if(diagCount[0] == 3) {
                    isEnd = true;

                    for(int i=0;i<3;i++){
                        if(board[i][i] != curPlayer) {
                            isEnd = false;
                            break;
                        }
                    }

                    if(isEnd) return curPlayer == 1 ? "A" : "B";
                }
            }

            if(move[0] + move[1] == 2) {
                diagCount[1]++;
                if(diagCount[1] == 3) {
                    isEnd = true;

                    for(int i=0;i<3;i++){
                        if(board[i][2-i] != curPlayer) {
                            isEnd = false;
                            break;
                        }
                    }

                    if(isEnd) return curPlayer == 1 ? "A" : "B";
                }
            }

            curPlayer *= -1;
        }

        return moves.length == 9 ? "Draw" : "Pending";
    }
}
