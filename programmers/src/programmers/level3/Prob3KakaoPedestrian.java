package programmers.level3;

// https://programmers.co.kr/learn/courses/30/lessons/1832
public class Prob3KakaoPedestrian {
    static final int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {

        // cache[i][j] : (i,j) 까지 이동 가능한 경로 수
        // cache[i][j][0] : (i-1, j)에서 오는 이동 경로 수 - 위에서 아래로 오는 경우
        // cache[i][j][1] : (i, j-1)에서 오는 이동 경로 수 - 왼쪽에서 오른쪽으로 오는 경우
        int[][][] cache = new int[m][n][2];

        // 상단 및 좌측을 1로 초기화
        for (int i = 1; i < m; i++) {
            if (cityMap[i][0] == 1) {
                // 통행 금지의 경우 이후는 모두 지나갈 수 없으므로 0
                break;
            }
            // 아래쪽으로만 이동
            cache[i][0][0] = 1;
            cache[i][0][1] = 0;
        }
        for (int j = 1; j < n; j++) {
            if (cityMap[0][j] == 1) {
                // 통행 금지의 경우 이후는 모두 지나갈 수 없으므로 0
                break;
            }
            // 오른쪽으로만 이동
            cache[0][j][0] = 0;
            cache[0][j][1] = 1;
        }

        // 이전 위치의 정보가 필요하므로 (i,j)는 (1,1)부터 시작
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 현재 위치가 통행 금지인 경우 pass
                if (cityMap[i][j] == 1) continue;

                // 왼쪽 위치 기준
                if (cityMap[i][j - 1] == 0) {
                    // 왼쪽에서 자유롭게 통행 가능
                    cache[i][j][1] = (cache[i][j - 1][0] + cache[i][j - 1][1]) % MOD;
                } else {
                    // 왼쪽에서 우측 이동만 가능
                    cache[i][j][1] = cache[i][j - 1][1];
                }

                // 위쪽 위치 기준
                if (cityMap[i - 1][j] == 0) {
                    // 위쪽에서 자유롭게 통행 가능
                    cache[i][j][0] = (cache[i - 1][j][0] + cache[i - 1][j][1]) % MOD;
                } else {
                    // 위쪽에서 아래 이동만 가능
                    cache[i][j][0] = cache[i - 1][j][0];
                }
            }
        }
        return (cache[m - 1][n - 1][0] + cache[m - 1][n - 1][1]) % MOD;
    }

    public static void main(String[] args) {
        Prob3KakaoPedestrian sol = new Prob3KakaoPedestrian();

        int[] m = {3, 3};
        int[] n = {3, 6};
        int[][][] cityMaps = {
                {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}
        };

        int[] ans = {6, 2};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(m[i], n[i], cityMaps[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
