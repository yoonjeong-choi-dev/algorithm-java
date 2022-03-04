package topcoder.ch5_bruteforce;


public class CrazyBot {
    // E, W, S, N 방
    private static final int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };
    private boolean[][] isVisited;
    public double totalScore;

    double[] probs = new double[4];

    public double getProbability(int n, int east, int west, int south, int north) {
        totalScore = 0.0;
        probs[0] = (double) east / 100.0;
        probs[1] = (double) west / 100.0;
        probs[2] = (double) south / 100.0;
        probs[3] = (double) north / 100.0;
        isVisited = new boolean[2 * n + 1][2 * n + 1];


        return dfs(n,n,n);

        //return totalScore;
    }

    private double dfs(int x, int y, int move) {
        // 이미 방문한 정점인 경우 더 이상 이동 불가능
        if (isVisited[x][y]) return 0.0;
        // 제대로 이동한 경우 확률은 1
        if (move == 0) return 1.0;

        // 현재 정점 방문
        isVisited[x][y] = true;

        // 다음 이동 가능한 정점들에 대해서 방문 시도
        double ret = 0.0;
        for (int i = 0; i<directions.length;i++) {
            double curRet = probs[i] * dfs(x + directions[i][0], y + directions[i][1], move -1);

            ret += curRet;
        }

        // 현재 정점에 대한 dfs 완료 시, 정점 방문을 롤백
        isVisited[x][y] = false;

        // 현재 정점에서의 확률 계산
        return ret;
    }

    public static void main(String[] args) {
        CrazyBot sol = new CrazyBot();

        int[] maxMoves = new int[]{1, 2, 7, 14};
        int[][] probabilities = new int[][]{
                {25, 25, 25, 25},
                {25, 25, 25, 25, 25},
                {50, 0, 0, 50},
                {50, 50, 0, 0}
        };

        double[] ans = new double[]{1.0, 0.75, 1.0, Math.pow(2, -13)};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            double mySol = sol.getProbability(maxMoves[i], probabilities[i][0], probabilities[i][1], probabilities[i][2], probabilities[i][3]);
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
