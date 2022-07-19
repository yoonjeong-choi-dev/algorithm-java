package from1to100;

// https://leetcode.com/problems/zigzag-conversion/
public class Prob6ZigzagConversion {
    public String convert(String s, int numRows) {
        // Edge Case
        if (numRows == 1 || s.length() <= numRows) return s;

        int len = s.length();

        // 아래 -> 우상향 규칙에 대한 크기 : numRows(아래) + (numRows-2)(우상향)
        int pieceSize = 2 * numRows - 2;

        StringBuilder ans = new StringBuilder(s.length());

        /* numRows : 6, pieceSize = 10
         0         10
         1       9 11
         2     8   12
         3   7     13
         4 6       14
         5         15
         */
        for (int row = 0; row < numRows; row++) {
            // 오른쪽 인덱스와 대각선 인덱스 차이 : row 부터 시작할 때의 pieceSize <=> 2*(numRows - row) -2
            // => pieceSize - 2*row
            int diagonalDiff = pieceSize - 2 * row;

            // 각 열에 대한 반복 규칙
            for (int i = 0; i + row < len; i += pieceSize) {
                // 아래 방향
                int downIndex = i + row;
                ans.append(s.charAt(downIndex));

                // 양끝 열이 아니고, 문자열이 남아 있는 경우 대각선 방향 문자 저장
                int diagonalIndex = downIndex + diagonalDiff;
                if (row != 0 && row != numRows - 1 && diagonalIndex < len)
                    ans.append(s.charAt(diagonalIndex));
            }
        }

        return ans.toString();
    }
}
