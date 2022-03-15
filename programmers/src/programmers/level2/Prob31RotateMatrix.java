package programmers.level2;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/77485
public class Prob31RotateMatrix {
    public int[] solution(int rows, int columns, int[][] queries) {
        // 인덱스 계산을 위해 쿼리에 있는 값들을 -1 한다
        for (int[] query : queries) {
            for (int i = 0; i < query.length; i++) {
                query[i] = query[i] - 1;
            }
        }


        int numQueries = queries.length;
        int[] answer = new int[numQueries];

        // 행렬 초기화
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = i * columns + j + 1;
            }
        }

        int curMin;
        int prev, temp;
        int minX, minY, maxX, maxY;
        for (int q = 0; q < numQueries; q++) {
            minX = queries[q][0];
            minY = queries[q][1];
            maxX = queries[q][2];
            maxY = queries[q][3];

            prev = matrix[minX][minY];
            curMin = matrix[minX][minY];

            // 상단 이동
            for (int i = minY; i < maxY; i++) {
                curMin = Math.min(curMin, prev);
                temp = matrix[minX][i + 1];
                matrix[minX][i + 1] = prev;
                prev = temp;
            }

            // 우측 이동
            for (int i = minX; i < maxX; i++) {
                curMin = Math.min(curMin, prev);
                temp = matrix[i + 1][maxY];
                matrix[i + 1][maxY] = prev;
                prev = temp;
            }

            // 하단 이동
            for (int i = maxY; i > minY; i--) {
                curMin = Math.min(curMin, prev);
                temp = matrix[maxX][i - 1];
                matrix[maxX][i - 1] = prev;
                prev = temp;
            }

            // 좌측 이동
            for (int i = maxX; i > minX; i--) {
                curMin = Math.min(curMin, prev);
                temp = matrix[i - 1][minY];
                matrix[i - 1][minY] = prev;
                prev = temp;
            }

            answer[q] = curMin;
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob31RotateMatrix sol = new Prob31RotateMatrix();

        int[] rows = {6, 3, 100};
        int[] cols = {6, 3, 97};
        int[][][] queries = {
                {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}},
                {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}},
                {{1, 1, 100, 97}}
        };

        int[][] ans = {
                {8, 10, 25},
                {1, 1, 5, 3},
                {1}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int[] mySol = sol.solution(rows[i], cols[i], queries[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
