package topcoder.ch7_dp;

public class ChessMetric {
    // 킹 나이트의 움직임
    // 킹 움직임 8개, 나이트 움직임 8개
    private final int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };

    public long howMany(int size, int[] start, int[] end, int numMoves) {

        // board[i][j] : (i,j) 지점까지 오는 경우의 수
        long[][] board;
        // prev[i][j] : 이전 단계 board[i][j] 정보
        long[][] prev = new long[size][size];

        // 1번째 단계는 prev에 초기화
        int nextX, nextY;
        for (int[] d : directions) {
            nextX = start[0] + d[0];
            nextY = start[1] + d[1];

            // 외부로 나가는 움직임은 무시
            if (isOutOfBoard(nextX, nextY, size)) continue;

            prev[nextX][nextY]++;
        }

        // 각 단계에 대해서 board 업데이트
        for (int step = 1; step < numMoves; step++) {
            // board 초기화
            board = new long[size][size];

            // prev 에서 0이 아닌 모든 점에서 움직임에 대한 정보 업데이트
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (prev[i][j] == 0) continue;

                    for (int[] d : directions) {
                        nextX = i+ d[0];
                        nextY = j + d[1];

                        // 외부로 나가는 움직임은 무시
                        if (isOutOfBoard(nextX, nextY, size)) continue;

                        board[nextX][nextY] += prev[i][j];
                    }
                }
            }

            // 다음 스텝을 위해 업데이트한 board 정보를 prev로 변경
            prev = board;
        }

        return prev[end[0]][end[1]];
    }

    private boolean isOutOfBoard(int x, int y, int size) {
        if (x < 0 || x >= size) return true;
        if (y < 0 || y >= size) return true;
        return false;
    }

    public static void main(String[] args) {
        ChessMetric sol = new ChessMetric();

        int[] sizes = {3, 3, 3, 3, 100};
        int[] start = {0, 0};
        int[][] ends = {{1, 0}, {1, 2}, {2, 2}, {0, 0}, {0, 99}};
        int[] moves = {1, 1, 1, 2, 50};

        long[] ans = {1, 1, 0, 5, 243097320072600L};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            long mySol = sol.howMany(sizes[i], start, ends[i], moves[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }

        sol.howMany(sizes[3], start, ends[3], moves[3]);
    }
}
