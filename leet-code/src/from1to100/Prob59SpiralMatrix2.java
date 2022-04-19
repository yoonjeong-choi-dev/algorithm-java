package from1to100;

// https://leetcode.com/problems/spiral-matrix-ii/
public class Prob59SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        // return myFourLoopSolution(n);
        return autoDirectionSolution(n);
    }

    private int[][] myFourLoopSolution(int n) {
        int[][] ans = new int[n][n];
        int cur = 1;
        int start = 0, end;

        int curSize = n - 1;
        while (curSize >= 0) {
            if (curSize == 0) {
                ans[start][start] = cur;
                return ans;
            }

            end = start + curSize;

            // top
            for (int i = 0; i < curSize; i++) {
                ans[start][start + i] = cur++;
            }

            // right
            for (int i = 0; i < curSize; i++) {
                ans[start + i][end] = cur++;
            }

            // bottom
            for (int i = 0; i < curSize; i++) {
                ans[end][end - i] = cur++;
            }

            // left
            for (int i = 0; i < curSize; i++) {
                ans[end - i][start] = cur++;
            }

            curSize -= 2;
            start++;
        }


        return ans;
    }

    // 다음에 채워야 하는 픽셀을 코드를 이용하여 자동으로 계산하는 방식
    private int[][] autoDirectionSolution(int n) {
        // 방향 순서 : top -> right -> bottom -> left
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int[][] ans = new int[n][n];
        int cur = 1;

        int d = 0, x = 0, y = 0;
        int nextX, nextY;
        while (cur <= n * n) {
            // 현재 픽셀 채우기
            ans[x][y] = cur++;

            // 현재 방향에 대한 다음 픽셀 계산 : 범위를 벗어났을 때 현재 범위 내로 변환하기 위해 floorMod 사용
            nextX = Math.floorMod(x + dir[d][0], n);
            nextY = Math.floorMod(y + dir[d][1], n);

            // 예상한 다음 픽셀이 이미 채워진 경우 다음 방향으로 전환
            if (ans[nextX][nextY] != 0) d = (d + 1) % 4;

            // 다음 픽셀로 이동
            x += dir[d][0];
            y += dir[d][1];
        }


        return ans;
    }

    public static void main(String[] args) {
        Prob59SpiralMatrix2 sol = new Prob59SpiralMatrix2();

        int[] cases = {1, 2, 3, 4, 5};
        for (int n : cases) {
            System.out.printf("Example : n == %d\n", n);
            int[][] ans = sol.generateMatrix(n);
            for (int[] row : ans) {
                for (int i = 0; i < n; i++) System.out.printf("%2d ", row[i]);
                System.out.println();
            }
        }
    }
}
