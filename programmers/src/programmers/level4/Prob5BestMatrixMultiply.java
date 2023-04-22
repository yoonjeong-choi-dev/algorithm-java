package programmers.level4;

// https://programmers.co.kr/learn/courses/30/lessons/12942
// TODO : 다시 풀기!
public class Prob5BestMatrixMultiply {
    private int[][] matrixSize;
    private long[][] cache;

    public int solution(int[][] matrix_sizes) {
        matrixSize = matrix_sizes;
        int numMatrix = matrix_sizes.length;

        // cache[i][j] : i 번째 행렬부터 j-1 번째 행렬까지의 곱 중 최소값
        cache = new long[numMatrix][numMatrix];
        for (int i = 0; i < numMatrix; i++) {
            for (int j = 0; j < numMatrix; j++) {
                if(i==j) cache[i][j] = 0;
                else cache[i][j] = -1;
            }
        }

        return (int) dp(0, numMatrix-1);
    }

    private long dp(int start, int end) {
        if(end - start == 1) {
            return cache[start][end]= (long) matrixSize[start][0] * matrixSize[start][1] * matrixSize[end][1];
        }

        if (cache[start][end] != -1) return cache[start][end];

        // 최대값으로 초기화
        cache[start][end] = Integer.MAX_VALUE;

        // start 행렬과 end 행렬이 마지막으로 곱할 중간값 구하기
        int left = matrixSize[start][0];
        int right = matrixSize[end][1];
        long midValue;
        for (int i = start + 1; i <= end; i++) {
            // 최종 행렬의 크기는 (left, mid) * (mid, right) 형태 where mid = matrixSize[i][0]
            // 즉, [start, end] 행렬 리스트는 [start, i-1] * [i,end] 형태로 쪼개진다
            midValue = (long) left * right * matrixSize[i][0];
            midValue += dp(start, i-1);
            midValue += dp(i, end);

            cache[start][end] = Math.min(cache[start][end], midValue);
        }

        return cache[start][end];
    }

    public static void main(String[] args) {
        Prob5BestMatrixMultiply sol = new Prob5BestMatrixMultiply();

        int[][] matrixSize = {{5, 3}, {3, 10}, {10, 6}};
        int ans = 270;

        int mySol = sol.solution(matrixSize);
        if (ans == mySol) {
            System.out.println("PASS");
        } else {
            System.out.println("Result : " + mySol);
            System.out.println("Expected : " + ans);
        }
    }
}
