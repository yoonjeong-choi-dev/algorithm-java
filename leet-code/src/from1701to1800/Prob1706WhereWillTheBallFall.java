package from1701to1800;

// https://leetcode.com/problems/where-will-the-ball-fall/
public class Prob1706WhereWillTheBallFall {
    public int[] findBall(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;
        int[] ans = new int[colSize];

        int curPos, nextPos;
        for (int ball = 0; ball < colSize; ball++) {
            curPos = ball;
            for (int i = 0; i < rowSize; i++) {
                nextPos = curPos + grid[i][curPos];

                // 불가능한 조건 : 밖으로 나가거나 V-shape 형성
                // V-shape : (curPos, nextPos) 가 V-shape i.e 둘의 경사가 다름
                if (nextPos < 0 || nextPos == colSize || grid[i][curPos] != grid[i][nextPos]) {
                    curPos = -1;
                    break;
                }

                curPos = nextPos;
            }

            ans[ball] = curPos;
        }

        return ans;
    }
}
