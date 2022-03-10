package programmers.level2;

public class Prob19Hopscotch {

    int solution(int[][] land) {
        int rowNum = land.length;
        int colNum = land[0].length;

        int[][] scores = new int[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                scores[i][j] = land[i][j];
            }
        }

        // 맨 마지막부터 선택하여 위로 올라가는 식
        for (int i = rowNum - 1; i > 0; i--) {

            for (int j = 0; j < colNum; j++) {
                // land[i][j]를 밟은 경우 land[i-1][k] 최대값
                for (int k = 0; k < colNum; k++) {
                    // 같은 행은 연속으로 못 밟는다
                    if (j == k) continue;

                    if (scores[i - 1][k] < land[i - 1][k] + scores[i][j]) {
                        scores[i - 1][k] = land[i - 1][k] + scores[i][j];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < colNum; i++) {
            if (answer < scores[0][i]) answer = scores[0][i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob19Hopscotch sol = new Prob19Hopscotch();

        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
        int ans = 16;

        int mySol = sol.solution(land);
        if (ans == mySol) {
            System.out.println("PASS");
        } else {
            System.out.println("Wrong");
            System.out.println("Solution : " + mySol);
            System.out.println("Expected : " + ans);
        }
    }
}
