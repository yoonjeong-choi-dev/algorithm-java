package from1801to1900;

// https://leetcode.com/problems/determine-color-of-a-chessboard-square/
public class Prob1812DetermineColorOfAChessboardSquare {
    public boolean squareIsWhite(String coordinates) {
        int row = coordinates.charAt(1) - '1';
        int col = coordinates.charAt(0) - 'a';

        // row,col 이 모두 짝수거나 홀수면 검정(false) => XOR
        return (row % 2 == 0) ^ (col % 2 == 0);
    }
}
