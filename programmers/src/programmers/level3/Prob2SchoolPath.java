package programmers.level3;

// https://programmers.co.kr/learn/courses/30/lessons/42898
public class Prob2SchoolPath {
    private static final int mod = 1000000007;

    public int solution(int m, int n, int[][] puddles) {

        // dp[i][j] : (i,j) 까지 오는데 경우의 수
        int[][] dp = new int[m][n];
        dp[0][0] = 1;


        // 물웅덩이가 있는 곳은 -1로 초기화한다
        for (int[] p : puddles) {
            dp[p[0]-1][p[1]-1] = -1;
        }

        // 상단 및 좌측을 1로 초기화
        for (int i = 0; i < m; i++) {
            if (dp[i][0] == -1) {
                // 물웅덩이가 있는 경우 이후는 모두 지나갈 수 없으므로 0
                break;
            }
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            if (dp[0][j] == -1) {
                // 물웅덩이가 있는 경우 이후는 모두 지나갈 수 없으므로 0
                break;
            }
            dp[0][j] = 1;
        }

        // 순차적으로 계산
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 물웅덩이인 경우 무시
                if (dp[i][j] == -1) continue;

                // 위에서 올 수 있는 경우
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
                }

                // 왼쪽에서 올 수 있는 경우
                if (dp[i][j - 1] != -1) {
                    dp[i][j] = (dp[i][j] + dp[i][j - 1]) % mod;
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Prob2SchoolPath sol = new Prob2SchoolPath();

        int m = 4, n = 3;
        int[][] puddles = {{2, 2}};
        int ans = 4;

        int mySol = sol.solution(m, n, puddles);
        if (ans == mySol) {
            System.out.println("PASS");
        } else {
            System.out.println("WRONG");
            System.out.println("Result : " + mySol);
            System.out.println("Expected : " + ans);
        }
    }
}
